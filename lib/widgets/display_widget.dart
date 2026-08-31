import 'package:flutter/material.dart';
import '../theme/app_theme.dart';

class DisplayWidget extends StatelessWidget {
  final String expressao;
  final String resultado;
  final VoidCallback aoApagar;

  const DisplayWidget({
    super.key,
    required this.expressao,
    required this.resultado,
    required this.aoApagar,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.only(left: 20, right: 20, top: 24, bottom: 10),
      decoration: BoxDecoration(
        color: AppTheme.corDisplay,
        borderRadius: BorderRadius.circular(16),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.end,
        children: [
          Text(
            expressao.isEmpty ? '0' : expressao,
            style: const TextStyle(color: Colors.white54, fontSize: 20),
            maxLines: 2,
            overflow: TextOverflow.ellipsis,
          ),
          const SizedBox(height: 8),
          Text(
            resultado,
            style: const TextStyle(
              color: Colors.white,
              fontSize: 40,
              fontWeight: FontWeight.bold,
            ),
          ),
          InkWell(
            onTap: aoApagar,
            child: Padding(
              padding: const EdgeInsets.only(top: 8.0, bottom: 4.0, left: 20.0),
              child: Icon(
                Icons.backspace_outlined,
                color: AppTheme.corLimpar,
                size: 24,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
