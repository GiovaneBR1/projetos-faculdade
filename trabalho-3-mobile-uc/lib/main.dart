import 'package:flutter/material.dart';
import 'pages/calculadora_page.dart';
import 'theme/app_theme.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Calculadora',
      debugShowCheckedModeBanner: false,
      // O tema agora é gerido de forma centralizada pela classe do Giovane
      theme: AppTheme.darkTheme, 
      // A interface principal foi isolada no componente do Matheus Alexander
      home: const CalculadoraPage(), 
    );
  }
}