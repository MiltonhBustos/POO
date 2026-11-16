# Cartões CRC - StarLog Logistics (Aula 24)

## Cartão CRC 01: Carga
* **Classe:** `br.com.starlog.model.Carga`
* **Camada:** Model (Modelo de Domínio)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Armazenar código de rastreio, categoria, peso e valor declarado de forma imutável (final). | *Nenhum* (Camada Model isolada e independente de UI). |
| - Validar atributos na construção via barreira fail-fast (código e categoria não nulos/vazios, peso e valor > 0). | |
| - Normalizar código de rastreio e categoria (.trim().toUpperCase()). | |
| - Calcular o valor do seguro da carga (2% do valor declarado + taxa fixa de R$ 150 para cargas da categoria PERIGOSA ou com peso > 100 kg). | |
| - Fornecer representação textual formatada da carga via `toString()`. | |

---

## Cartão CRC 02: CargaController
* **Classe:** `br.com.starlog.controller.CargaController`
* **Camada:** Controller (Controlador)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Orquestrar o fluxo de cadastro e despacho de cargas recebendo dados brutos da interface gráfica (View). | - `Carga` (Model) |
| - Realizar conversão segura de tipos (parse de String para double, tratando vírgula e ponto). | - `TelaCadastroCarga` (View) |
| - Tratar `NumberFormatException` impedindo travamento da aplicação caso valores inválidos sejam informados. | |
| - Instanciar o modelo `Carga` e capturar `IllegalArgumentException` disparadas pela barreira fail-fast. | |
| - Manter o repositório em memória (`List<Carga>`) das cargas cadastradas na estação. | |
| - Gerar relatório oficial de despacho formatado com total de cargas, peso total e seguro acumulado. | |
| - Notificar a View com mensagens amigáveis de sucesso ou erro e solicitar atualização do relatório. | |

---

## Cartão CRC 03: TelaCadastroCarga
* **Classe:** `br.com.starlog.view.TelaCadastroCarga`
* **Camada:** View (Interface Gráfica Swing)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Renderizar os componentes da interface gráfica (campos de texto, combobox, botões e área de log). | - `CargaController` (Controller) |
| - Capturar os eventos de ação dos botões ("Cadastrar no Sistema" e "Limpar Campos"). | |
| - Coletar os dados digitados e repassar diretamente para o Controller (delegação pura, sem regras de negócio na View). | |
| - Exibir caixas de diálogo amigáveis (`JOptionPane`) de sucesso ou erro informadas pelo Controller. | |
| - Atualizar a área de texto com o relatório de despacho emitido pelo Controller. | |
| - Limpar campos do formulário e resetar foco. | |

---

## Cartão CRC 04: MainApp
* **Classe:** `br.com.starlog.main.MainApp`
* **Camada:** Main / Bootstrap (Ponto de Entrada)

| Responsabilidades | Colaboradores |
| :--- | :--- |
| - Inicializar a execução da aplicação de forma segura na Event Dispatch Thread (EDT) via `SwingUtilities.invokeLater`. | - `CargaController` (Controller) |
| - Instanciar o Controller (`CargaController`). | - `TelaCadastroCarga` (View) |
| - Injetar o Controller na View (`TelaCadastroCarga`) e torná-la visível. | |
