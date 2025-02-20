package br.com.integratefarma.especialidade.service;

import br.com.integratefarma.especialidade.dto.EspecialidadeCreateDTO;
import br.com.integratefarma.especialidade.dto.EspecialidadeDTO;
import br.com.integratefarma.especialidade.entity.EspecialidadeEntity;
import br.com.integratefarma.especialidade.repository.EspecialidadeRepository;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.utils.dto.PageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EspecialidadeService {
    private final EspecialidadeRepository especialidadeRepository;
    private final ObjectMapper objectMapper;


    public PageDTO<EspecialidadeDTO> list(Integer pagina, Integer tamanho){
        Pageable solicitacaoPagina = PageRequest.of(pagina,tamanho);
        Page<EspecialidadeEntity> especialidade = especialidadeRepository.findAll(solicitacaoPagina);
        List<EspecialidadeDTO> especialidadeDTO = especialidade.getContent().stream()
                .map(x -> objectMapper.convertValue(x, EspecialidadeDTO.class))
                .toList();

        return new PageDTO<>(especialidade.getTotalElements(),
                especialidade.getTotalPages(),
                pagina,
                tamanho,
                especialidadeDTO);
    }

    public EspecialidadeDTO getById(Integer id) throws RegraDeNegocioException {
        return objectMapper.convertValue(getEspecialidade(id), EspecialidadeDTO.class);
    }

    public EspecialidadeDTO adicionar(EspecialidadeCreateDTO especialidade) throws RegraDeNegocioException {
        checarSeTemNumero(especialidade.getNomeEspecialidade());

        EspecialidadeEntity especialidadeEntity = objectMapper.convertValue(especialidade, EspecialidadeEntity.class);
        especialidadeRepository.save(especialidadeEntity);
        EspecialidadeDTO especialidadeDTO = objectMapper.convertValue(especialidadeEntity, EspecialidadeDTO.class);

        return especialidadeDTO;
    }

    public EspecialidadeDTO editar(Integer id, EspecialidadeCreateDTO especialidade) throws RegraDeNegocioException {
        checarSeTemNumero(especialidade.getNomeEspecialidade());

        EspecialidadeEntity especialidadeEntityRecuperada = getEspecialidade(id);

        especialidadeEntityRecuperada.setNomeEspecialidade(especialidade.getNomeEspecialidade());
        especialidadeEntityRecuperada.setValor(especialidade.getValor());

        especialidadeRepository.save(especialidadeEntityRecuperada);

        return objectMapper.convertValue(especialidadeEntityRecuperada, EspecialidadeDTO.class);
    }

    public void remover(Integer id) throws RegraDeNegocioException {
        if(getEspecialidade(id).getPrestadorServicoEntities().isEmpty()){
            especialidadeRepository.deleteById(id);
        }else{
            throw new RegraDeNegocioException("A especialidade que deseja excluir está sendo usado por algum(ns) prestador(es) de serviço(s), associe-o(s) a outra antes.");
        }
    }

    public EspecialidadeEntity getEspecialidade(Integer id) throws RegraDeNegocioException {
        return especialidadeRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Especialidade não existe!"));
    }

    public void checarSeTemNumero(String string) throws RegraDeNegocioException {
        if (string.matches(".*[0-9].*")) { // checa se tem número no nome
            throw new RegraDeNegocioException("O nome da especialidade não pode conter número");
        }
    }
}
