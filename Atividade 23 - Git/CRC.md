# Cartões CRC - StarLog-Zero (Aula 23)

## Cartão CRC 01: CriptoMensagem
* **Classe:** `br.com.starlog.model.CriptoMensagem`
* **Camada:** Model (Modelo de Domínio)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Armazenar o remetente, mensagem e chave de deslocamento de forma imutável (final). | *Nenhum* (Camada Model isolada e independente). |
| - Validar parâmetros na construção via barreira fail-fast (remetente e mensagem não vazios, chave entre 1 e 25). | |
| - Normalizar remetente e mensagem (.trim().toUpperCase()). | |
| - Cifrar a mensagem usando o algoritmo da Cifra de César com rotação circular (wrap-around de 'A' a 'Z') preservando espaços e pontuações. | |
| - Calcular o checksum de integridade somando o código ASCII de cada caractere da mensagem. | |

---

## Cartão CRC 02: TransmissaoController
* **Classe:** `br.com.starlog.controller.TransmissaoController`
* **Camada:** Controller (Controlador)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Orquestrar o fluxo de transmissão recebendo os dados brutos da interface gráfica (View). | - `CriptoMensagem` (Model) |
| - Fazer o parse seguro da chave de texto para número inteiro, tratando `NumberFormatException` e disparando mensagem limpa. | |
| - Instanciar o modelo `CriptoMensagem` com os dados sanitizados. | |
| - Recuperar o texto cifrado e o checksum gerados pelo Model. | |
| - Montar e formatar o pacote de saída no padrão `[REMETENTE] #CHECKSUM -> TEXTO_CIFRADO`. | |
| - Armazenar o histórico de pacotes na lista buffer e fornecer o total de transmissões. | |

---

## Cartão CRC 03: TelaCaoticaLegada
* **Classe:** `br.com.starlog.amador.TelaCaoticaLegada`
* **Camada:** View (Interface Visual)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Apresentar a interface gráfica com campos para remetente, mensagem, chave e área de log. | - `TransmissaoController` (Controller) |
| - Capturar o evento de clique do botão "Cifrar e Transmitir". | |
| - Coletar os valores digitados nos JTextFields e repassar diretamente para o Controller. | |
| - Exibir pop-up amigável (JOptionPane) com mensagens de sucesso ou de erro capturadas. | |
| - Atualizar a área de texto com o pacote cifrado retornado pelo Controller. | |
