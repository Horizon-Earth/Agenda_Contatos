package br.edu.principal;
public class Contato {

    // ATRIBUTOS
    private String nome;
    private String celular;
    private String email;

    public Contato(String nome, String celular, String email) {


        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }


    // GETTERS
    // Servem para PEGAR as informações do objeto.

    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }


    // SETTERS
    // Servem para ALTERAR as informações do objeto.

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}