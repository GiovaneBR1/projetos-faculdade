import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart'; // Import do Riverpod
import '../providers/tarefas_provider.dart'; // Import do Provider do Iuri

// Trocamos StatefulWidget por ConsumerStatefulWidget
class AddTarefaWidget extends ConsumerStatefulWidget {
  const AddTarefaWidget({super.key});

  @override
  ConsumerState<AddTarefaWidget> createState() => _AddTarefaWidgetState();
}

// Trocamos State por ConsumerState
class _AddTarefaWidgetState extends ConsumerState<AddTarefaWidget> {
  late TextEditingController _controller;

  @override
  void initState() {
    super.initState();
    _controller = TextEditingController();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

   void _adicionarTarefa() {
    final texto = _controller.text.trim();
    if (texto.isEmpty) return;
    ref.read(tarefasProvider.notifier).adicionarTarefa(texto);
    _controller.clear();
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Expanded(
          child: TextField(
            controller: _controller,
            style: const TextStyle(color: Colors.white),
            decoration: InputDecoration(
              hintText: 'Nova tarefa...',
              hintStyle: const TextStyle(color: Colors.white38),
              filled: true,
              fillColor: const Color(0xFF1E1E2E),
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(12),
                borderSide: BorderSide.none,
              ),
            ),
            onSubmitted: (_) => _adicionarTarefa(),
          ),
        ),
        const SizedBox(width: 10),
        ElevatedButton(
          style: ElevatedButton.styleFrom(
            backgroundColor: const Color(0xFF5E4AE3),
            foregroundColor: Colors.white,
            padding: const EdgeInsets.symmetric(vertical: 16, horizontal: 20),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(12),
            ),
          ),
          onPressed: _adicionarTarefa,
          child: const Icon(Icons.add),
        ),
      ],
    );
  }
}