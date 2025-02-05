
package br.com.sistema.rmi;
import br.com.sistema.model.Produtos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ProdutosService extends Remote{
    void SalvarProdutoDao(Produtos produtos)throws RemoteException;
    void EditarProdutoDao(Produtos produtos)throws RemoteException;
    void ExcluirProdutoDao(Produtos produtos)throws RemoteException;
    Produtos BuscarProdutoDao(String nome)throws RemoteException;
    Produtos BuscarProdutoCodDao(int id)throws RemoteException;
    List<Produtos> ListarProdutoDao()throws RemoteException;
    List<Produtos> FiltarProdutoDao(String nome)throws RemoteException;
    void adicionarEstoque(int id, int qtd_nova)throws RemoteException;
    void baixaEstoque(int id, int qtd_nova)throws RemoteException;
    int retornaQtdAtualEstoque(int id)throws RemoteException;
}
