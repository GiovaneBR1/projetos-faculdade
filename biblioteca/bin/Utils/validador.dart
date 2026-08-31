import 'dart:io';

class Validador {
  // Pede um texto e não deixa o usuário avançar enquanto estiver vazio
  static String lerTextoNaoVazio(String mensagem) {
    String texto = '';
    while (texto.trim().isEmpty) {
      stdout.write(mensagem);
      texto = stdin.readLineSync() ?? '';
      if (texto.trim().isEmpty) {
        print('Erro: Este campo não pode ficar vazio. Tente novamente.');
      }
    }
    return texto;
  }

  static String lerApenasLetras(String mensagem) {
  String texto = '';
  // Expressão regular que permite apenas letras e espaços
  RegExp regex = RegExp(r'^[a-zA-Z\s]+$'); 

  while (true) {
    stdout.write(mensagem);
    texto = stdin.readLineSync() ?? '';
    
    if (texto.trim().isEmpty) {
      print('Erro: O campo não pode estar vazio.');
    } else if (!regex.hasMatch(texto)) {
      print('Erro: O autor não pode conter números ou caracteres especiais.');
    } else {
      return texto;
    }
  }
}

  // Pede um número e repete até o usuário digitar um número válido
  static int lerNumeroInteiro(String mensagem) {
    int? numero;
    while (numero == null) {
      stdout.write(mensagem);
      String entrada = stdin.readLineSync() ?? '';
      numero = int.tryParse(entrada);
      
      if (numero == null) {
        print('Erro: Digite apenas números válidos. Tente novamente.');
      } else if (numero <= 0) {
        print('Erro: O número deve ser maior que zero.');
        numero = null; // Força o laço a repetir
      }
    }
    return numero;
  }
}