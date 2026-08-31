# To-do App

Aplicativo de lista de tarefas desenvolvido em Flutter como parte do Trabalho 4 da disciplina de Mobile — ADSIS5S (Unicesumar).

## Descrição

Gerenciador de tarefas com interface dark e gestão de estado via Riverpod. O projeto aplica o conceito de **gestão de estado reativa**, separando modelo de dados, lógica de estado e interface em camadas independentes.

## Funcionalidades

- Adicionar nova tarefa
- Visualizar todas as tarefas em lista
- Marcar tarefa como concluída (com indicação visual: texto riscado e esmaecido)
- Remover tarefa
- Campo de texto com suporte a submissão pelo teclado (Enter)

## Estrutura do projeto

```
lib/
├── main.dart                        
├── models/
│   └── tarefa_model.dart             
├── providers/
│   └── tarefas_provider.dart        
├── pages/
│   └── home_page.dart                
└── widgets/
    ├── add_tarefa_widget.dart          
    ├── lista_tarefas_widget.dart      
    └── tarefa_item.dart              
```

## Gestão de estado com Riverpod


O `tarefasProvider` é um `StateNotifierProvider` global acessado por qualquer widget via:

- `ref.watch(tarefasProvider)` — lê o estado e reconstrói o widget ao mudar
- `ref.read(tarefasProvider.notifier)` — aciona métodos sem causar rebuild
- 
## Requisitos

- Flutter SDK `>=3.0.0 <4.0.0`
- Dart SDK `>=3.0.0 <4.0.0`
- [flutter_riverpod](https://pub.dev/packages/flutter_riverpod) `^2.5.1`

## Como executar

**1. Clone o repositório**
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd lib
```

**2. Instale as dependências**
```bash
flutter pub get
```

**3. Execute o app**
```bash
flutter run
```

> Use `flutter run -d chrome` para web ou `flutter devices` para listar dispositivos disponíveis.

## Integrantes

| Nome | Responsabilidade |
|---|---|
| Higor | `main.dart` 
| Matheus Ramos | `models/tarefa_model.dart`
| Matheus Alexander | `providers/tarefas_provider.dart` 
| Alexandre | `pages/home_page.dart` 
| Iuri | `widgets/add_tarefa_widget.dart` 
| Giovane | `widgets/lista_tarefas_widget.dart` + `widgets/tarefa_item.dart` 
