import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'pages/home_page.dart'; // Importação da nova página isolada

void main() {
  runApp(
    // O ProviderScope armazena o estado de todos os providers
    const ProviderScope(
      child: MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Lista de Tarefas',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        brightness: Brightness.dark, // Define o tema escuro globalmente
      ),
      home: const HomePage(), // Direciona para a página do Matheus Alexander
    );
  }
}