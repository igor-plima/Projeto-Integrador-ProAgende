package model;

public class Cliente {

    //Atributos
    private int idCliente;
    private String nome;
    private String email;
    private String telefone;
    
    //Construtor
    public Cliente(int idCliente, String nome, String email, String telefone) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Cliente() {
    }
    

    //Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
