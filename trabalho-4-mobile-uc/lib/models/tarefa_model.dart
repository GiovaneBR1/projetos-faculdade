// lib/models/tarefa_model.dart
class Tarefa {
  final String id;
  final String titulo;
  final bool concluida;

  Tarefa({
    required this.id,
    required this.titulo,
    this.concluida = false,
  });

  // Essencial para o Riverpod atualizar o estado de forma imutável
  Tarefa copyWith({String? titulo, bool? concluida}) {
    return Tarefa(
      id: id,
      titulo: titulo ?? this.titulo,
      concluida: concluida ?? this.concluida,
    );
  }
}