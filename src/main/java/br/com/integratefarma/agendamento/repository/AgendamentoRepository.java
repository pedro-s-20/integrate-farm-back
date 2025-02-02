package br.com.integratefarma.agendamento.repository;

import br.com.integratefarma.agendamento.entity.AgendamentoEntity;
import br.com.integratefarma.cliente.entity.ClienteEntity;
import br.com.integratefarma.prestadorservico.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Integer> {

    List<AgendamentoEntity> findAllByIdCliente(Integer id);

    List<AgendamentoEntity> findAllByIdMedico(Integer id);

    
    void deleteByMedicoEntity(MedicoEntity medicoEntity);

    void deleteByClienteEntity(ClienteEntity clienteEntity);
}
