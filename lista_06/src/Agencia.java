public class Agencia{

    public static final String nomeBanco = "JavaBank";
    public static final double TAXA_SAQUE = 5.00;
    
    private static int totalContasAbertas = 0;

    public static void registrarNovaConta(){
        totalContasAbertas++;
    }

    public static int getTotalContasAbertas(){
        return totalContasAbertas;
    }

}