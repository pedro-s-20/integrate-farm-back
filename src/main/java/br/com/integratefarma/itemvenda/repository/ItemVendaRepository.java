package br.com.integratefarma.itemvenda.repository;

import br.com.integratefarma.itemvenda.entity.ItemVendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVendaEntity, Long> {

}
