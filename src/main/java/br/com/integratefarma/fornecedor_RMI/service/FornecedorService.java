package br.com.integratefarma.fornecedor_RMI.service;

import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.rmi.FornecedoresService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FornecedorService {

    @Value("${rmi.host}")
    private String RMI_HOST;

    public boolean salvarFornecedoresDao(Fornecedores input) throws RegraDeNegocioException {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry(RMI_HOST, 1099);

            // Procurar o serviço remoto
            FornecedoresService service = (FornecedoresService) registry.lookup("FornecedoresService");

            service.SalvarFornecedoresDao(input);
            return true;
        } catch (Exception e) {
            throw new RegraDeNegocioException("Erro ao salvar o fornecedor: " + e.getMessage());
        }
    }

    public boolean editarFornecedoreDao(Fornecedores input) throws RegraDeNegocioException {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry(RMI_HOST, 1099);

            // Procurar o serviço remoto
            FornecedoresService service = (FornecedoresService) registry.lookup("FornecedoresService");

            service.EditarFornecedoreDao(input);
            return true;
        } catch (Exception e) {
            throw new RegraDeNegocioException("Erro ao editar o fornecedor: " + e.getMessage());
        }
    }

    public boolean ecluirFornecedoreDao(Fornecedores input) throws RegraDeNegocioException {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry(RMI_HOST, 1099);

            // Procurar o serviço remoto
            FornecedoresService service = (FornecedoresService) registry.lookup("FornecedoresService");

            service.ExcluirFornecedoresDao(input);
            return true;
        } catch (Exception e) {
            throw new RegraDeNegocioException("Erro ao excluir o fornecedor: " + e.getMessage());
        }
    }

    public Fornecedores buscarFornecedoresDao(String nome) throws RegraDeNegocioException {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry(RMI_HOST, 1099);

            // Procurar o serviço remoto
            FornecedoresService service = (FornecedoresService) registry.lookup("FornecedoresService");

            return service.BuscarFornecedoresDao(nome);
        } catch (Exception e) {
            throw new RegraDeNegocioException("Erro ao buscar o fornecedor: " + e.getMessage());
        }
    }

    public List<Fornecedores> listarFornecedoreDao() throws RegraDeNegocioException {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry(RMI_HOST, 1099);

            // Procurar o serviço remoto
            FornecedoresService service = (FornecedoresService) registry.lookup("FornecedoresService");

            return service.ListarFornecedoreDao();
        } catch (Exception e) {
            throw new RegraDeNegocioException("Erro ao buscar o fornecedor: " + e.getMessage());
        }
    }
}
