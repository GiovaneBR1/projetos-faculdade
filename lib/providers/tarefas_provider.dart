import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../models/tarefa_model.dart';


// Gerenciador de estado da lista de tarefas
class TarefasNotifier extends StateNotifier<List<Tarefa>> {
  TarefasNotifier() : super([]);

  // Adiciona uma nova tarefa
  void adicionarTarefa(String titulo) {
    final nova = Tarefa(
      id: DateTime.now().millisecondsSinceEpoch.toString(),
      titulo: titulo,
    );
    state = [...state, nova];
  }

  // Marca/desmarca como concluída
  void alternarConclusao(String id) {
    state = state.map((t) {
      return t.id == id ? t.copyWith(concluida: !t.concluida) : t;
    }).toList();
  }

  // Remove a tarefa
  void removerTarefa(String id) {
    state = state.where((t) => t.id != id).toList();
  }
}

// Provider global acessível em toda a aplicação
final tarefasProvider = StateNotifierProvider<TarefasNotifier, List<Tarefa>>(
  (ref) => TarefasNotifier(),
);
