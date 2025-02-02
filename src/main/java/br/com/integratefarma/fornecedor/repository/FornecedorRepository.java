package br.com.integratefarma.fornecedor.repository;

import br.com.integratefarma.fornecedor.entity.FornecedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long> {

}
