import 'package:flutter/material.dart';
import 'botao_widget.dart';
import '../controller/calculadora_controller.dart';
import '../theme/app_theme.dart';

class TecladoWidget extends StatelessWidget {
  final CalculadoraController controller;
  final Function(Function) onAction;

  const TecladoWidget({
    super.key,
    required this.controller,
    required this.onAction,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        // Primeira Linha
        Expanded(
          child: Row(
            crossAxisAlignment:
                CrossAxisAlignment.stretch, // Estica verticalmente
            children: [
              BotaoWidget(
                texto: 'C',
                cor: AppTheme.corLimpar,
                aoPressed: () => onAction(controller.limpar),
              ),
              BotaoWidget(
                texto: '%',
                cor: AppTheme.corOperador,
                aoPressed: () => onAction(controller.adicionarPorcentagem),
              ),
              BotaoWidget(
                texto: '( )',
                cor: AppTheme.corOperador,
                aoPressed: () => onAction(controller.adicionarParenteses),
              ),
              BotaoWidget(
                texto: '÷',
                cor: AppTheme.corOperador,
                aoPressed: () =>
                    onAction(() => controller.adicionarOperador('/')),
              ),
            ],
          ),
        ),
        // Segunda Linha 
        Expanded(
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              BotaoWidget(
                texto: '7',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('7')),
              ),
              BotaoWidget(
                texto: '8',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('8')),
              ),
              BotaoWidget(
                texto: '9',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('9')),
              ),
              BotaoWidget(
                texto: 'X',
                cor: AppTheme.corOperador,
                aoPressed: () =>
                    onAction(() => controller.adicionarOperador('*')),
              ),
            ],
          ),
        ),
        // Terceira Linha 
        Expanded(
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              BotaoWidget(
                texto: '4',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('4')),
              ),
              BotaoWidget(
                texto: '5',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('5')),
              ),
              BotaoWidget(
                texto: '6',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('6')),
              ),
              BotaoWidget(
                texto: '-',
                cor: AppTheme.corOperador,
                aoPressed: () =>
                    onAction(() => controller.adicionarOperador('-')),
              ),
            ],
          ),
        ),
        // Quarta Linha 
        Expanded(
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              BotaoWidget(
                texto: '1',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('1')),
              ),
              BotaoWidget(
                texto: '2',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('2')),
              ),
              BotaoWidget(
                texto: '3',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('3')),
              ),
              BotaoWidget(
                texto: '+',
                cor: AppTheme.corOperador,
                aoPressed: () =>
                    onAction(() => controller.adicionarOperador('+')),
              ),
            ],
          ),
        ),
        // Quinta Linha
        Expanded(
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              BotaoWidget(
                texto: '+/-',
                cor: const Color.fromARGB(255, 64, 57, 109),
                aoPressed: () => onAction(() => controller.inverterSinal()),
              ),
              
             
              BotaoWidget(
                texto: '0',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('0')),
              ),

               BotaoWidget(
                texto: '.',
                aoPressed: () =>
                    onAction(() => controller.adicionarNumero('.')),
              ),

              BotaoWidget(
                texto: '=',
                cor: AppTheme.corIgual,
                aoPressed: () => onAction(controller.calcular),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
