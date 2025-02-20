package br.com.integratefarma.agendamento.repository;

import br.com.integratefarma.agendamento.entity.AgendamentoEntity;
import br.com.integratefarma.cliente.entity.ClienteEntity;
import br.com.integratefarma.prestadorservico.entity.PrestadorServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Integer> {

    List<AgendamentoEntity> findAllByIdCliente(Integer id);

    List<AgendamentoEntity> findAllByIdPrestadorServico(Integer id);

    
    void deleteByPrestadorServicoEntity(PrestadorServicoEntity prestadorServicoEntity);

    void deleteByClienteEntity(ClienteEntity clienteEntity);
}
