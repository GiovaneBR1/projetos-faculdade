import 'package:flutter/material.dart';
import '../theme/app_theme.dart'; // Importando as cores centralizadas

class BotaoWidget extends StatelessWidget {
  final String texto;
  final Color cor;
  final Color corTexto;
  final VoidCallback aoPressed;

  const BotaoWidget({
    super.key,
    required this.texto,
    required this.aoPressed,
    this.cor = AppTheme.corNumero, // Valor padrão vindo do tema
    this.corTexto = Colors.white,
  });

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Padding(
        padding: const EdgeInsets.all(6.0),
        child: ElevatedButton(
          style: ElevatedButton.styleFrom(
            backgroundColor: cor,
            foregroundColor: corTexto,
            padding: const EdgeInsets.symmetric(vertical: 20),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(12),
            ),
            elevation: 3,
          ),
          onPressed: aoPressed,
          child: Text(
            texto,
            style: const TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
          ),
        ),
      ),
    );
  }
}
