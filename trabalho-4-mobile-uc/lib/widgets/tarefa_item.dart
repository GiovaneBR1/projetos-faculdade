import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

// Importamos o modelo e o provider separados graças à nova arquitetura!
import '../models/tarefa_model.dart';
import '../providers/tarefas_provider.dart';

class TarefaItem extends ConsumerWidget {
  final Tarefa tarefa;

  const TarefaItem({super.key, required this.tarefa});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Card(
      color: const Color(0xFF1E1E2E),
      margin: const EdgeInsets.symmetric(vertical: 6, horizontal: 4),
      child: ListTile(
        // Checkbox para alternar o status da tarefa
        leading: Checkbox(
          value: tarefa.concluida,
          activeColor: Colors.greenAccent,
          onChanged: (_) {
            // Chamamos a ação do Riverpod diretamente do item!
            ref.read(tarefasProvider.notifier).alternarConclusao(tarefa.id);
          },
        ),
        
        // O Título com estilo dinâmico (riscado se concluído)
        title: Text(
          tarefa.titulo,
          style: TextStyle(
            color: tarefa.concluida ? Colors.white38 : Colors.white,
            decoration: tarefa.concluida ? TextDecoration.lineThrough : null,
            fontSize: 16,
          ),
        ),
        
        // Botão para deletar a tarefa
        trailing: IconButton(
          icon: const Icon(Icons.delete, color: Colors.redAccent),
          onPressed: () {
            // Chamamos a ação de remover do Riverpod
            ref.read(tarefasProvider.notifier).removerTarefa(tarefa.id);
          },
        ),
      ),
    );
  }
}