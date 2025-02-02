package br.com.integratefarma.especialidade.repository;

import br.com.integratefarma.especialidade.entity.EspecialidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadeRepository extends JpaRepository<EspecialidadeEntity, Integer> {

}
