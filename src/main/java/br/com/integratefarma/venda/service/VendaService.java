package br.com.integratefarma.venda.service;

import br.com.integratefarma.itemvenda.entity.ItemVendaEntity;
import br.com.integratefarma.itemvenda.repository.ItemVendaRepository;
import br.com.integratefarma.parcela.entity.ParcelaEntity;
import br.com.integratefarma.parcela.repository.ParcelaRepository;
import br.com.integratefarma.produto.entity.ProdutoEntity;
import br.com.integratefarma.venda.dto.*;
import br.com.integratefarma.venda.entity.VendaEntity;
import br.com.integratefarma.venda.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final ParcelaRepository parcelaRepository;

    public VendaDTO createClienteVenda(VendaCreateDTO input, Integer idCliente) {
        VendaEntity newVenda = new VendaEntity();
        newVenda.setClienteId(idCliente);
        newVenda.setDataVenda(input.getDataVenda());
        newVenda.setTotalVenda(input.getTotalVenda());
        newVenda.setObservacoes(input.getObservacoes());
        newVenda.setQuantidadeParcelas(input.getQuantidadeParcelas());

        VendaEntity vendaAdicionada = vendaRepository.save(newVenda);

        for(ProdutoQuantidadeDTO produtoQuantidade: input.getProdutos()) {
            ItemVendaEntity itemVenda = new ItemVendaEntity();
            itemVenda.setIdVenda(vendaAdicionada.getId());
            itemVenda.setIdProduto(produtoQuantidade.getIdProduto());
            itemVenda.setQuantidade(produtoQuantidade.getQuantidade());
            itemVenda.setSubtotal(produtoQuantidade.getSubtotal());
            itemVendaRepository.save(itemVenda);
        }

        if (input.getQuantidadeParcelas() > 0) {
            Double valorCadaParcela = Math.round(input.getTotalVenda() / input.getQuantidadeParcelas()) / 100.0;

            for (int i = 0; i < input.getQuantidadeParcelas(); i++) {
                ParcelaEntity newParcela = new ParcelaEntity();
                newParcela.setDataVenda(LocalDateTime.now());
                newParcela.setTotal(valorCadaParcela);
                newParcela.setObservacao(input.getObservacoesParcela());
                newParcela.setNumeroParcelas(input.getQuantidadeParcelas());
                newParcela.setClienteId(idCliente);
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
                .clienteId(vendaAdicionada.getClienteId())
                .quantidadeParcelas(vendaAdicionada.getQuantidadeParcelas())
                .produtos(produtosList)
                .build();
    }

}
