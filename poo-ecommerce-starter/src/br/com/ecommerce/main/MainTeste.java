package br.com.ecommerce.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.ecommerce.model.Produto;

public class MainTeste {
    public static void main(String[] args) {
        Produto[] carrinho = new Produto[3];

        carrinho[0] = new Produto("Filtro de Óleo Yamaha R3", 85.00);
        carrinho[1] = new Produto("Camisa do Corinthians", 299.90);
        carrinho[2] = new Produto("Bola de Basquete", 150.00);

        System.out.println("Carrinho cheio!");

        List<Produto> carrinhoList = new ArrayList<>();// vazio a partir do Java 7

        carrinhoList.add(new Produto("Filtro de Óleo Yamaha R3", 85.00));
        carrinhoList.add(new Produto("Camisa do Corinthians", 299.90));
        carrinhoList.add(new Produto("Bola de Basquete", 150.00));
        carrinhoList.add(new Produto("Bolha Esportiva R3", 350.00)); // Funciona!

        // -------------------------------------------------------

        List<Produto> favoritos = new ArrayList<>();

        Produto bola = new Produto("Bola de Basquete", 150.00);

        favoritos.add(bola);
        favoritos.add(bola); // O clique acidental

        System.out.println("Tamanho dos favoritos: " + favoritos.size());
        // Saída será 2! Duplicou!

            Set<Produto> favoritos2 = new HashSet<>();

            favoritos2.add(bola);
            favoritos2.add(bola); // Será solenemente ignorado
            System.out.println("Tamanho: " + favoritos2.size()); // Imprime 1
       // -------------------------------------------------------
       
       Set<Produto> favoritos3 = new HashSet<>();

// Dois objetos físicos diferentes na memória RAM, mas com a mesma semântica
        Produto p1 = new Produto("Capacete", 500.00);
        Produto p2 = new Produto("Capacete", 500.00);

        favoritos3.add(p1);
        favoritos3.add(p2);

        System.out.println("Tamanho: " + favoritos3.size());
        // ABSURDO: Imprime 2!
       
       
       
        }
}
