# Calculadora App

Aplicativo de calculadora desenvolvido em Flutter como parte do Trabalho 3 da disciplina de Mobile — ADSIS5S (Unicesumar).

## Descrição

Calculadora com interface dark e operações matemáticas completas. O projeto aplica o conceito de **componentização de widgets**, separando cada parte da interface e da lógica em arquivos independentes e reutilizáveis.

## Funcionalidades

- Adição, subtração, multiplicação e divisão
- Porcentagem (`%`)
- Parênteses inteligentes com botão único `( )`
- Inversão de sinal (`+/-`)
- Ponto decimal
- Backspace (apagar último caractere)
- Limpar tudo (`C`)
- Tratamento de erro para divisão por zero

## Estrutura de componentes

```
lib/
├── main.dart                       
├── theme/
│   └── app_theme.dart                 
├── controller/
│   └── calculadora_controller.dart    
├── pages/
│   └── calculadora_page.dart         
└── widgets/
    ├── botao_widget.dart              
    ├── display_widget.dart            
    └── teclado_widget.dart            
```

### Explicação dos componentes

**`app_theme.dart`**
Centraliza todas as cores da aplicação em constantes estáticas com nomes semânticos (`corNumero`, `corOperador`, `corIgual`, `corLimpar`). Qualquer alteração visual é feita em um único lugar.

**`calculadora_controller.dart`**
Contém toda a lógica matemática em Dart puro. Gerencia a expressão atual, valida operadores duplicados, resolve parênteses e executa o cálculo com precedência correta (multiplicação e divisão antes de adição e subtração).

**`calculadora_page.dart`**
Único `StatefulWidget` do projeto. Instancia o controller e expõe o método `_atualizarEstado`, que encapsula o `setState` e é passado para o teclado. Isso mantém o controle de redesenho centralizado na página.

**`botao_widget.dart`**
Componente reutilizável instanciado ~20 vezes no teclado. Recebe `texto`, `aoPressed`, `cor` (padrão: `AppTheme.corNumero`) e `corTexto` como parâmetros, adaptando o visual conforme o tipo de botão.

**`display_widget.dart`**
Exibe a expressão sendo digitada (fonte menor) e o resultado em destaque (fonte maior). Inclui o botão de backspace. Alinhamento à direita com `CrossAxisAlignment.end`.

**`teclado_widget.dart`**
Grade de 5 linhas com `Column` de `Expanded` + `Row` de 4 `BotaoWidget`. Cada botão aciona o método correspondente do controller via `onAction`, que chama o `setState` da página.

## Requisitos

- Flutter SDK `^3.11.1`
- Dart SDK `^3.11.1`

## Como executar

**1. Clone o repositório**
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd calculadora_app
```

**2. Instale as dependências**
```bash
flutter pub get
```

**3. Execute o app**
```bash
flutter run
```

> Para rodar em um dispositivo específico, use `flutter run -d chrome` para web ou `flutter run -d <device-id>` para um dispositivo conectado. Use `flutter devices` para listar os disponíveis.

## Integrantes

| Nome | Responsabilidade |
|---|---|
| Higor | `main.dart` 
| Matheus Ramos | `theme/app_theme.dart` 
| Matheus Alexander | `pages/calculadora_page.dart` 
| Alexandre | `controller/calculadora_controller.dart` 
| Iuri | `widgets/botao_widget.dart` 
| Giovane | `widgets/display_widget.dart` + `widgets/teclado_widget.dart`
