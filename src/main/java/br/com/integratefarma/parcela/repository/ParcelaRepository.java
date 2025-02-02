package br.com.integratefarma.parcela.repository;

import br.com.integratefarma.parcela.entity.ParcelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaRepository extends JpaRepository<ParcelaEntity, Long> {

}
