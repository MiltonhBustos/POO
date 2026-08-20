import br.com.enigma.Decodificador;
import br.com.enigma.DecodificadorBase64;
import br.com.enigma.DecodificadorCesar;
import br.com.enigma.DecodificadorReverso;
import br.com.enigma.FiltroImagem;
import br.com.enigma.FiltroVermelhoMagico;

public class Main {

    public static void main(String[] args) {

        // =========================
        // TESTE BASE64
        // =========================

        String texto64 = "Q2hhdmVfQ2VzYXI6IDcgfCBBbHZvOiBtZW5zYWdlbS50eHQ=";

        Decodificador decodificador = new DecodificadorBase64();

        String resultado = decodificador.decodificar(texto64);

        System.out.println("Base64: " + resultado);

        // =========================
        // TESTE CÉSAR
        // =========================

        String mensagem = "Mpsayl v jhuhs CLYTLSOV kh pthnlt ybpkv.itw. Ptwhy = Wylav, Why = Iyhujv";

        Decodificador decodificadorCesar = new DecodificadorCesar(7);

        String resultadoCesar = decodificadorCesar.decodificar(mensagem);

        System.out.println("César: " + resultadoCesar);

        // =========================
        // TESTE REVERSO
        // =========================

        Decodificador decodificadorReverso = new DecodificadorReverso();

        String mensagemReverso = "odnum olleH";

        String resultadoReverso = decodificadorReverso.decodificar(mensagemReverso);

        System.out.println("Reverso: " + resultadoReverso);

        // =========================
        // TESTE FILTROS
        // =========================

        FiltroImagem filtro = new FiltroVermelhoMagico();

        filtro.aplicarFiltro(
            "ruido.bmp",
            "imagem_filtrada.jpg"
        );
    }
}