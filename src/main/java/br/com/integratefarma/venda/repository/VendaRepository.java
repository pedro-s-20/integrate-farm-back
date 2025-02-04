package br.com.integratefarma.venda.repository;

import br.com.integratefarma.venda.entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Integer> {

}
