package br.com.enigma;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FiltroVermelhoMagico implements FiltroImagem {

    @Override
    public void aplicarFiltro(String caminhoEntrada, String caminhoSaida) {

        try {

            // Lê a imagem
            BufferedImage imagem = ImageIO.read(new File("src/ruido.bmp"));

            // Percorre todos os pixels
            for (int y = 0; y < imagem.getHeight(); y++) {

                for (int x = 0; x < imagem.getWidth(); x++) {

                    // Pega a cor do pixel
                    int rgb = imagem.getRGB(x, y);

                    // Extrai o canal RED
                    int vermelho = (rgb >> 16) & 0xFF;

                    // Se RED for ímpar -> PRETO
                    // Se RED for par -> BRANCO
                    if (vermelho % 2 != 0) {
                        imagem.setRGB(x, y, 0xFF000000);
                    } else {
                        imagem.setRGB(x, y, 0xFFFFFFFF);
                    }
                }
            }

            // Salva a imagem modificada
            ImageIO.write(
                imagem,
                "jpg",
                new File(caminhoSaida)
            );

            System.out.println("Filtro aplicado com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro ao aplicar filtro: " + e.getMessage());
        }
    }
}