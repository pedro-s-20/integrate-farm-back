package br.com.integratefarma.venda.service;

import br.com.integratefarma.cliente.entity.ClienteEntity;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.itemvenda.entity.ItemVendaEntity;
import br.com.integratefarma.itemvenda.repository.ItemVendaRepository;
import br.com.integratefarma.parcela.entity.ParcelaEntity;
import br.com.integratefarma.parcela.repository.ParcelaRepository;
import br.com.integratefarma.produto.entity.ProdutoEntity;
import br.com.integratefarma.produto.repository.ProdutoRepository;
import br.com.integratefarma.venda.dto.ProdutoQuantidadeDTO;
import br.com.integratefarma.venda.dto.ProdutosVendaOutputDTO;
import br.com.integratefarma.venda.dto.VendaCreateDTO;
import br.com.integratefarma.venda.dto.VendaDTO;
import br.com.integratefarma.venda.entity.VendaEntity;
import br.com.integratefarma.venda.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final ParcelaRepository parcelaRepository;
    private final ProdutoRepository produtoRepository;

    public VendaDTO createClienteVenda(VendaCreateDTO input, ClienteEntity cliente) throws RegraDeNegocioException {
        VendaEntity newVenda = new VendaEntity();
        newVenda.setClienteEntity(cliente);
        newVenda.setDataVenda(input.getDataVenda());
        newVenda.setTotalVenda(input.getTotalVenda());
        newVenda.setObservacoes(input.getObservacoes());
        newVenda.setQuantidadeParcelas(input.getQuantidadeParcelas());

        Set<ItemVendaEntity> itemVendaToAdd = new HashSet<>();
        for(ProdutoQuantidadeDTO produtoQuantidade: input.getProdutos()) {
            ItemVendaEntity itemVenda = new ItemVendaEntity();
            ProdutoEntity produto = produtoRepository.findById(produtoQuantidade.getIdProduto())
                    .orElseThrow(() -> new RegraDeNegocioException("Esta compra contém produto que não existe!"));
            itemVenda.setProdutoEntity(produto);
            itemVenda.setQuantidade(produtoQuantidade.getQuantidade());
            Double valorCadaProduto = BigDecimal.valueOf(produtoQuantidade.getSubtotal())
                    .divide(BigDecimal.valueOf(produtoQuantidade.getQuantidade()), 2, RoundingMode.HALF_UP).doubleValue();
            itemVenda.setSubtotal(valorCadaProduto);
            itemVendaToAdd.add(itemVenda);
        }

        newVenda.setItemVendaEntities(itemVendaToAdd);
        VendaEntity vendaAdicionada = vendaRepository.save(newVenda);

        if (input.getQuantidadeParcelas() > 0) {
            Double valorCadaParcela = BigDecimal.valueOf(vendaAdicionada.getTotalVenda())
                    .divide(BigDecimal.valueOf(vendaAdicionada.getQuantidadeParcelas()), 2, RoundingMode.HALF_UP).doubleValue();

            for (int i = 0; i < input.getQuantidadeParcelas(); i++) {
                ParcelaEntity newParcela = new ParcelaEntity();
                newParcela.setDataVenda(LocalDateTime.now());
                newParcela.setTotal(vendaAdicionada.getTotalVenda());
                newParcela.setObservacao(input.getObservacoesParcela());
                newParcela.setNumeroParcelas(input.getQuantidadeParcelas());
                newParcela.setClienteEntity(cliente);
                newParcela.setParcela(valorCadaParcela);
                parcelaRepository.save(newParcela);
            }
        }

        List<ProdutosVendaOutputDTO> produtosList = new ArrayList<>();
        vendaAdicionada.getItemVendaEntities().forEach(itemVenda -> {
            ProdutoEntity produtoEntity = itemVenda.getProdutoEntity();
            ProdutosVendaOutputDTO produtoVendaDTO = ProdutosVendaOutputDTO.builder()
                    .idProduto(produtoEntity.getId())
                    .descricaoProduto(produtoEntity.getDescricao())
                    .quantidade(itemVenda.getQuantidade())
                    .build();
            produtosList.add(produtoVendaDTO);
        });

        return VendaDTO.builder()
                .id(vendaAdicionada.getId())
                .dataVenda(vendaAdicionada.getDataVenda())
                .totalVenda(vendaAdicionada.getTotalVenda())
                .observacoes(vendaAdicionada.getObservacoes())
                .clienteId(cliente.getId())
                .quantidadeParcelas(vendaAdicionada.getQuantidadeParcelas())
                .produtos(produtosList)
                .build();
    }

}
