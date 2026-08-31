class CalculadoraController {
  
  String expressao = '';
  String resultado = '0';

  // Método para adicionar números
  void adicionarNumero(String valor) {
    expressao += valor;
    resultado = expressao;
  }

  // Método para adicionar operadores matemáticas
  void adicionarOperador(String operador) {
    if (expressao.isNotEmpty) {
      String ultimo = expressao[expressao.length - 1];
      // Se o último caractere já for um operador, substitui-o
      if (['+', '-', '*', '/'].contains(ultimo)) {
        expressao = expressao.substring(0, expressao.length - 1);
      }
    }
    expressao += operador;
    resultado = expressao;
  }

  // Método para o botão "C" (Limpar tudo)
  void limpar() {
    expressao = '';
    resultado = '0';
  }

  // Método para o botão de apagar (backspace)
  void apagar() {
    if (expressao.isNotEmpty) {
      expressao = expressao.substring(0, expressao.length - 1);
      resultado = expressao.isEmpty ? '0' : expressao;
    }
  }

  // Lógica de um único botão para ( )
  void adicionarParenteses() {
    int abertos = expressao.split('(').length - 1;
    int fechados = expressao.split(')').length - 1;

    if (expressao.isEmpty) {
      expressao += '(';
    } else {
      String ultimo = expressao[expressao.length - 1];
      if (['+', '-', '*', '/', '('].contains(ultimo)) {
        expressao += '(';
      } else if (abertos > fechados) {
        expressao += ')';
      } else {
        // Se colocar parêntese depois de número (ex: 5()), ele entende como vezes: 5*(
        expressao += '*(';
      }
    }
    resultado = expressao;
  }

  // Lógica da porcentagem
  void adicionarPorcentagem() {
    if (expressao.isNotEmpty) {
      String ultimo = expressao[expressao.length - 1];
      if (!['+', '-', '*', '/', '(', '%'].contains(ultimo)) {
        expressao += '%';
        resultado = expressao;
      }
    }
  }

  // método para inverter o sinal 
  void inverterSinal() {
    if (expressao.isEmpty) {
      expressao = '-';
    } else if (double.tryParse(expressao) != null) {
    
      if (expressao.startsWith('-')) {
        expressao = expressao.substring(1); 
      } else {
        expressao = '-$expressao';
      }
    } else {
      
      expressao += '*(-1)';
    }
    resultado = expressao;
  }

  void calcular() {
    if (expressao.isEmpty) return;

    try {
      String expr = expressao;

      while (expr.isNotEmpty &&
          ['+', '-', '*', '/'].contains(expr[expr.length - 1])) {
        expr = expr.substring(0, expr.length - 1);
      }

      int abertos = expr.split('(').length - 1;
      int fechados = expr.split(')').length - 1;
      expr += ')' * (abertos - fechados);

      double res = _avaliarComRegex(expr);

      if (res.isInfinite || res.isNaN) {
        resultado = 'Erro: div/0';
        expressao = '';
      } else {
        resultado = res == res.truncateToDouble()
            ? res.toInt().toString()
            : res
                  .toStringAsFixed(6)
                  .replaceAll(RegExp(r'0+$'), '')
                  .replaceAll(RegExp(r'\.$'), '');
        expressao = resultado;
      }
    } catch (e) {
      resultado = 'Erro';
      expressao = '';
    }
  }

  double _avaliarComRegex(String expr) {
    expr = expr.replaceAll('%', '/100');

    while (expr.contains('(')) {
      final match = RegExp(r'\(([^()]+)\)').firstMatch(expr);
      if (match != null) {
        String expressaoInterna = match.group(1)!;
        double resultadoInterno = _avaliarMatematicaSimples(expressaoInterna);

        expr = expr.replaceRange(
          match.start,
          match.end,
          resultadoInterno.toString(),
        );
      } else {
        break;
      }
    }

    return _avaliarMatematicaSimples(expr);
  }

  double _avaliarMatematicaSimples(String expr) {
    List<String> tokens = [];
    String atual = '';

    for (int i = 0; i < expr.length; i++) {
      String c = expr[i];
      if (c == '+' || c == '-' || c == '*' || c == '/') {
        if (atual.isNotEmpty) {
          tokens.add(atual);
          atual = '';
        }
        if (c == '-' &&
            (tokens.isEmpty ||
                tokens.last == '+' ||
                tokens.last == '-' ||
                tokens.last == '*' ||
                tokens.last == '/')) {
          atual = '-';
        } else {
          tokens.add(c);
        }
      } else {
        atual += c;
      }
    }
    if (atual.isNotEmpty) tokens.add(atual);
    if (tokens.isEmpty) return 0;

    List<dynamic> lista = [];
    for (String t in tokens) {
      double? n = double.tryParse(t);
      lista.add(n ?? t);
    }

    int i = 0;
    while (i < lista.length) {
      if (lista[i] == '*') {
        double r = (lista[i - 1] as double) * (lista[i + 1] as double);
        lista.replaceRange(i - 1, i + 2, [r]);
        i = 0;
      } else if (lista[i] == '/') {
        double r = (lista[i - 1] as double) / (lista[i + 1] as double);
        lista.replaceRange(i - 1, i + 2, [r]);
        i = 0;
      } else {
        i++;
      }
    }

    double total = lista[0] as double;
    i = 1;
    while (i < lista.length) {
      String op = lista[i] as String;
      double num = lista[i + 1] as double;
      if (op == '+') total += num;
      if (op == '-') total -= num;
      i += 2;
    }

    return total;
  }
}
