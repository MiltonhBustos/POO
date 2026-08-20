package br.com.streaming.main;

import br.com.streaming.financeiro.Boleto;
import br.com.streaming.financeiro.CartaoDeCredito;
import br.com.streaming.financeiro.IPagamento;
import br.com.streaming.financeiro.Pix;
import br.com.streaming.financeiro.ProcessarPagamento;
import br.com.streaming.model.Usuario;
// TODO: Fazer imports das classes dos pacotes model e financeiro

public class MainTeste {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE STREAMING ===");
        // TODO: Testes da Aula 2 e 3
        Usuario u1 = new Usuario(null, null);
        u1.setNome("Joao Silva");
        u1.setEmail("joao@email.com");

        Usuario u2 = new Usuario(null, null);
        u2.setNome("Maria Silva");
        u2.setEmail("maria@email.com");

        Usuario u3 = new Usuario(null, null);
        u3.setNome("Jose Silva");
        u3.setEmail("jose@email.com");

        if (u1.equals(u2)) {
            System.out.println("Erro: Usuário já existe!");
        }

        System.out.println("Nome Plataforma: " + Usuario.NOME_PLATAFORMA);
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        System.err.println(Usuario.contadorUsuarios);

        ProcessarPagamento processador = new ProcessarPagamento();

        IPagamento cartao = new CartaoDeCredito();
        IPagamento pix = new Pix();
        IPagamento boleto = new Boleto();

        processador.finalizarCompra(cartao, 150.50);
        processador.finalizarCompra(pix, 300.00);
        processador.finalizarCompra(boleto, 50.99);

    }
}
