import 'package:flutter/material.dart';

class AppTheme {
  static const Color corFundo = Color(0xFF12121F);
  static const Color corNumero = Color(0xFF3A3A4C);
  static const Color corOperador = Color(0xFF5E4AE3);
  static const Color corIgual = Color(0xFF7C3AED);
  static const Color corLimpar = Color(0xFFDC2626);
  static const Color corDisplay = Color(0xFF1E1E2E);

  static ThemeData get darkTheme {
    return ThemeData(
      useMaterial3: true,
      scaffoldBackgroundColor: corFundo,

      appBarTheme: const AppBarTheme(
        backgroundColor: corFundo,
        centerTitle: true,
        elevation: 0,
        titleTextStyle: TextStyle(
          color: Colors.white,
          fontWeight: FontWeight.bold,
          fontSize: 20,
        ),
      ),
    );
  }
}
