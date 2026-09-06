package br.com.cybercorp.model;

public class Credencial {
    private String codigoHex;
    private boolean ativo;
    private Funcionario titular;

    //construtor
    public Credencial(String codigoHex, boolean ativo, Funcionario titular) {
        this.codigoHex = codigoHex;
        this.ativo = ativo;
        this.titular = titular;
    }

    //to string
    @Override
    public String toString() {
        return "Credencial [codigoHex=" + codigoHex + ", ativo=" + ativo + ", titular=" + titular + "]";
    }
    
    //Hashcode e Equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoHex == null) ? 0 : codigoHex.hashCode());
        result = prime * result + (ativo ? 1231 : 1237);
        result = prime * result + ((titular == null) ? 0 : titular.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Credencial other = (Credencial) obj;
        if (codigoHex == null) {
            if (other.codigoHex != null)
                return false;
        } else if (!codigoHex.equals(other.codigoHex))
            return false;
        if (ativo != other.ativo)
            return false;
        if (titular == null) {
            if (other.titular != null)
                return false;
        } else if (!titular.equals(other.titular))
            return false;
        return true;
    }

    //getters e setters
    public String getCodigoHex() {
        return codigoHex;
    }
    public void setCodigoHex(String codigoHex) {
        this.codigoHex = codigoHex;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public Funcionario getTitular() {
        return titular;
    }
    public void setTitular(Funcionario titular) {
        this.titular = titular;
    }
}
