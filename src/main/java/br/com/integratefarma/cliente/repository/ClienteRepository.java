package br.com.integratefarma.cliente.repository;

import br.com.integratefarma.cliente.dto.ClienteCompletoDTO;
import br.com.integratefarma.cliente.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("select new br.com.integratefarma.cliente.dto.ClienteCompletoDTO(" +
            "c.id, " +
            "c.idUsuario, " +
            "con.cadastroOrgaoRegulador, " +
            "con.taxaAbatimento, " +
            "u.cpf, " +
            "u.email, " +
            "u.nome, " +
            "ca.nomeCargo, " +
            "u.contatos, " +
            "u.cep, " +
            "u.numero) " +
            "from tb_clientes c" +
            " left join c.usuarioEntity u " +
            " left join c.usuarioEntity.cargoEntity ca" +
            " where c.id = :id" +
            " and c.usuarioEntity.ativo = 1"
    )
    Optional<ClienteCompletoDTO> getByIdPersonalizado(Integer id);

    @Query("select new br.com.integratefarma.cliente.dto.ClienteCompletoDTO(" +
            "c.id, " +
            "c.idUsuario, " +
            "con.cadastroOrgaoRegulador, " +
            "con.taxaAbatimento, " +
            "u.cpf, " +
            "u.email, " +
            "u.nome, " +
            "ca.nomeCargo, " +
            "u.contatos, " +
            "u.cep, " +
            "u.numero) " +
            "from tb_clientes c" +
            " left join c.usuarioEntity u " +
            " left join c.usuarioEntity.cargoEntity ca" +
            " where c.usuarioEntity.ativo = 1"
    )
    Page<ClienteCompletoDTO> listarFull(Pageable pageable);

    @Query("SELECT c from tb_clientes c where c.id = :id and c.usuarioEntity.ativo = 1")
    Optional<ClienteEntity> findById(Integer id);

    ClienteEntity getClienteEntityByIdUsuario(Integer idLoggedUser);
}
