package br.com.cybercorp.model;

public class Funcionario {
    private String matricula;
    private String nome;
    private Departamento departamento;

    //construtor
     public Funcionario(String matricula, String nome, Departamento departamento) {
        this.matricula = matricula;
        this.nome = nome;
        this.departamento = departamento;
    }
    
    //to string
    @Override
    public String toString() {
        return "Funcionario [matricula=" + matricula + ", nome=" + nome + ", departamento=" + departamento + "]";
    }

    //getters e setters
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Departamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
