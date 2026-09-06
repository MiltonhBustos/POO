package br.com.cybercorp.model;

public class Departamento {
    private String sigla;
    private String nomeSetor;
    private int andar;

    //construtor
    public Departamento(String sigla, String nomeSetor, int andar) {
        this.sigla = sigla;
        this.nomeSetor = nomeSetor;
        this.andar = andar;
    }

    //to string
    @Override
    public String toString() {
        return "Departamento [sigla=" + sigla + ", nomeSetor=" + nomeSetor + ", andar=" + andar + "]";
    }
    //getters e setters
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public String getNomeSetor() {
        return nomeSetor;
    }
    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
    public int getAndar() {
        return andar;
    }
    public void setAndar(int andar) {
        this.andar = andar;
    }

    
}
