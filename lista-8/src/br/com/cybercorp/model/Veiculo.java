package br.com.cybercorp.model;

public class Veiculo {
    private String placa;
    private String modelo;
    private Funcionario dono;
    
    //Construtor
    public Veiculo(String placa, String modelo, Funcionario dono) {
        this.placa = placa;
        this.modelo = modelo;
        this.dono = dono;
    }

    //to string
    @Override
    public String toString() {
        return "Veiculo [placa=" + placa + ", modelo=" + modelo + ", dono=" + dono + "]";
    }

    //Getters e setters
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public Funcionario getDono() {
        return dono;
    }
    public void setDono(Funcionario dono) {
        this.dono = dono;
    }
}
