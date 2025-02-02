package br.com.integratefarma.prestadorservico.repository;

import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO;
import br.com.integratefarma.prestadorservico.entity.PrestadorServicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestadorServicoRepository extends JpaRepository<PrestadorServicoEntity, Integer> {

    @Query("select new br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO(" +
            " m.idPrestadorServico, " +
            " m.crm, " +
            " m.idEspecialidade, " +
            " m.idUsuario, " +
            " es.valor, " +
            " es.nomeEspecialidade, " +
            " u.cpf, " +
            " u.email, " +
            " u.nome ," +
            " ca.nomeCargo, " +
            " u.contatos, " +
            " u.cep, " +
            " u.numero) " +
            " from PRESTADOR_SERVICO m" +
            " left join m.usuarioEntity u" +
            " left join m.especialidadeEntity es" +
            " left join u.cargoEntity ca" +
            " where m.idPrestadorServico = :id" +
            " and m.usuarioEntity.ativo = 1"
    )
    Optional<PrestadorServicoCompletoDTO> getByIdPersonalizado(Integer id);

    @Query("select new br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO(" +
            " m.idPrestadorServico, " +
            " m.crm, " +
            " m.idEspecialidade, " +
            " m.idUsuario, " +
            " es.valor, " +
            " es.nomeEspecialidade, " +
            " u.cpf, " +
            " u.email, " +
            " u.nome," +
            " ca.nomeCargo, " +
            " u.contatos, " +
            " u.cep, " +
            " u.numero) " +
            " from PRESTADOR_SERVICO m" +
            " left join m.usuarioEntity u" +
            " left join u.cargoEntity ca" +
            " left join m.especialidadeEntity es" +
            " where m.usuarioEntity.ativo = 1"
    )
    Page<PrestadorServicoCompletoDTO> listarFull(Pageable pageable);


    @Query("SELECT m from PRESTADOR_SERVICO m where m.idPrestadorServico = :id and m.usuarioEntity.ativo = 1")
    Optional<PrestadorServicoEntity> findById(Integer id);

    PrestadorServicoEntity getMedicoEntityByIdUsuario(Integer idLoggedUser);
}
