package br.com.sistema.rmi;

import br.com.sistema.model.Fornecedores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FornecedoresService extends Remote {
    void SalvarFornecedoresDao(Fornecedores fornecedor) throws RemoteException;
    void EditarFornecedoreDao(Fornecedores fornecedor) throws RemoteException;
    void ExcluirFornecedoresDao(Fornecedores fornecedor) throws RemoteException;
    Fornecedores BuscarFornecedoresDao(String nome) throws RemoteException;
    List<Fornecedores> ListarFornecedoreDao() throws RemoteException;
    List<Fornecedores> FiltarFornecedoresDao() throws RemoteException;
}
