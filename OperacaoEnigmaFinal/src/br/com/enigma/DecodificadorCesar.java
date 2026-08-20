package br.com.enigma;

public class DecodificadorCesar implements Decodificador{
    
    private int chave;

    public DecodificadorCesar(int chave){
        this.chave = chave;
    }


    @Override
    public String decodificar(String texto) {

        StringBuilder resultado = new StringBuilder();

        for (char caractere : texto.toCharArray()) {

            if (Character.isLetter(caractere)) {

                char base = Character.isUpperCase(caractere) ? 'A' : 'a';

                caractere = (char) (
                    (caractere - base - chave + 26) % 26 + base
                );
            }

            resultado.append(caractere);
        }

        return resultado.toString();
    }


}
