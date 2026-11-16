package br.com.starlog.controller;

import br.com.starlog.model.CriptoMensagem;
import java.util.ArrayList;
import java.util.List;

public class TransmissaoController {

    private final List<String> bufferTransmissoes = new ArrayList<>();

    public String processarTransmissao(String remetenteRaw, String msgRaw, String chaveStr) {
        int chave;

        try {
            if (chaveStr == null) {
                throw new NumberFormatException();
            }
            chave = Integer.parseInt(chaveStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Chave deve ser um número inteiro válido.");
        }

        CriptoMensagem cripto = new CriptoMensagem(remetenteRaw, msgRaw, chave);

        String textoCifrado = cripto.cifrar();
        int checksum = cripto.calcularChecksum();

        String pacote = String.format("[%s] #%d -> %s", cripto.getRemetente(), checksum, textoCifrado);

        bufferTransmissoes.add(pacote);

        return pacote;
    }

    public int getTotalTransmissoes() {
        return bufferTransmissoes.size();
    }
}
