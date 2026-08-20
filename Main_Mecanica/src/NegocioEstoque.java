import java.util.HashMap;
import java.util.Map;

public class NegocioEstoque {
    private Map<String, Integer> itens = new HashMap<>();

    void darEntrada(String sku, int quantidade){

        if(itens.containsKey(sku)){
            System.out.println("Peca repetida");

            //itens.put(1);
        }else{
            itens.put(sku, quantidade);
            System.out.println("Peca adicionada com sucesso!");
        }
    }
//-------------------------------------------------------------------------------

    boolean darBaixa(String sku, int quantidade){

        if(itens.containsKey(sku)){
            itens.remove(quantidade);
            return true;
        }else{
            System.out.println("Erro, não há estoque o suficiente!");
            return false;
        }
    }
//--------------------------------------------------------------------------------

    int consultarQuantidade(String sku){

        return 0;
    }
}
