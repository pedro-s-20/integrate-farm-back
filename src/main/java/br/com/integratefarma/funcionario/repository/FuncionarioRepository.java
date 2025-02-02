package br.com.integratefarma.funcionario.repository;

import br.com.integratefarma.funcionario.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

}
