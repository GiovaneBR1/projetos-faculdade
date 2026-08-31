// lib/widgets/lista_tarefas_widget.dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../providers/tarefas_provider.dart';
import 'tarefa_item.dart';

class ListaTarefasWidget extends ConsumerWidget {
  const ListaTarefasWidget({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final tarefas = ref.watch(tarefasProvider);

    if (tarefas.isEmpty) {
      return const Center(
        child: Text('Nenhuma tarefa ainda.', style: TextStyle(color: Colors.white38)));
    }

    return ListView.builder(
      itemCount: tarefas.length,
      itemBuilder: (context, index) => TarefaItem(tarefa: tarefas[index]),
    );
  }
}