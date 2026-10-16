package br.com.NexusCloud.model;

public class Container {
    
    private String id;
    private String nomeApp;
    private double ramNecessaria;

    
    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }


    public String getId() {
        return id;
    }


    public double getRamNecessaria() {
        return ramNecessaria;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nomeApp == null) ? 0 : nomeApp.hashCode());
        long temp;
        temp = Double.doubleToLongBits(ramNecessaria);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Container other = (Container) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nomeApp == null) {
            if (other.nomeApp != null)
                return false;
        } else if (!nomeApp.equals(other.nomeApp))
            return false;
        if (Double.doubleToLongBits(ramNecessaria) != Double.doubleToLongBits(other.ramNecessaria))
            return false;
        return true;
    }

    
}
