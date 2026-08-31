import 'package:flutter/material.dart';
import '../widgets/add_tarefa_widget.dart';
import '../widgets/lista_tarefas_widget.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF12121F), // Cor de fundo centralizada
      appBar: AppBar(
        backgroundColor: const Color(0xFF12121F),
        elevation: 0,
        title: const Text(
          'Minhas Tarefas',
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
        ),
        centerTitle: true,
      ),
      body: const Padding(
        padding: EdgeInsets.all(16),
        child: Column(
          children: [
            // Componente do Alexandre: Trata de receber novas tarefas
            AddTarefaWidget(),
            
            SizedBox(height: 24),
            
            // Componente do Matheus Ramos: Gere o ListView.builder e o estado global
            Expanded(
              child: ListaTarefasWidget(),
            ),
          ],
        ),
      ),
    );
  }
}