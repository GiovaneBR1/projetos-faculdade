import 'package:flutter/material.dart';
import '../controller/calculadora_controller.dart';
import '../widgets/display_widget.dart';
import '../widgets/teclado_widget.dart';
import '../theme/app_theme.dart';

class CalculadoraPage extends StatefulWidget {
  const CalculadoraPage({super.key});

  @override
  State<CalculadoraPage> createState() => _CalculadoraPageState();
}

class _CalculadoraPageState extends State<CalculadoraPage> {
  final CalculadoraController _controller = CalculadoraController();

  void _atualizarEstado(Function acao) {
    setState(() {
      acao();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppTheme.corFundo,
      appBar: AppBar(title: const Text('Calculadora')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            DisplayWidget(
              expressao: _controller.expressao,
              resultado: _controller.resultado,
              aoApagar: () => _atualizarEstado(
                _controller.apagar,
              ), // Passando a função de apagar
            ),

            const SizedBox(height: 20),

            Expanded(
              child: TecladoWidget(
                controller: _controller,
                onAction: _atualizarEstado,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
