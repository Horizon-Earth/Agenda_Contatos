package br.edu.principal;

public class contato {
    private String nome;
    private String celular;
    private String email;

    public contato(String nome, String celular, String email) {
        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getcelular() {
        return celular;
    }

    public void setcelular(String celular) {
        this.celular = celular;
    }

    public String getemail() {
        return email;
    }

    public void  setemail(String email) {
        this.email = email;
    }
}
