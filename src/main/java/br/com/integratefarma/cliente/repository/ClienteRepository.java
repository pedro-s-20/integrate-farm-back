package br.com.integratefarma.cliente.repository;

import br.com.integratefarma.cliente.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
