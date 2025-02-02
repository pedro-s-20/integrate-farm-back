package br.com.integratefarma.email.enums;

public enum TipoEmail {

    USUARIO_CADASTRO("Cadastro no sistema"),
    USUARIO_REDEFINIR_SENHA("Redefinição de senha"),
    USUARIO_SENHA_REDEFINIDA("Senha redefinida"),
    AGENDAMENTO_CRIADO_CLIENTE("Novo agendamento"),
    AGENDAMENTO_EDITADO_CLIENTE("Agendamento alterado"),
    AGENDAMENTO_CANCELADO_CLIENTE("Agendamento removido"),
    AGENDAMENTO_CRIADO_PRESTADOR("Novo agendamento"),
    AGENDAMENTO_EDITADO_PRESTADOR("Agendamento alterado"),
    AGENDAMENTO_CANCELADO_PRESTADOR("Agendamento removido");


    private String assunto;

    TipoEmail(String assunto){
        this.assunto = assunto;
    }

    public String getAssunto(){
        return assunto;
    }

}
