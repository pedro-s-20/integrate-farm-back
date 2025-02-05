package br.com.sistema.model;


import java.io.Serializable;

/**
 *
 * @author cleyton
 */
public class Fornecedores extends Clientes implements Serializable{
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString(){
        return this.getNome();
    }


}
