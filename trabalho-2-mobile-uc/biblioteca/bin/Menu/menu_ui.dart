import 'dart:io';
import '../BibliotecaService/biblioteca_service.dart';
import '../Utils/validador.dart';

class MenuUi {
  var biblioteca = Biblioteca();

  void exibir() {
    while (true) {
      print('\n=== BIBLIOTECA ===');
      print('1 - Cadastrar livro');
      print('2 - Listar livros');
      print('3 - Atualizar livro');
      print('4 - Remover livro');
      print('5 - Sair');
      stdout.write('Escolha: ');

      String? opcao = stdin.readLineSync();

      switch (opcao) {
        case '1':
          String titulo = Validador.lerTextoNaoVazio('Título: ');
          String autor = Validador.lerApenasLetras('Autor: ');
          int ano = Validador.lerNumeroInteiro('Ano de publicação: ');

          biblioteca.cadastrar(titulo, autor, ano);
          break;

        case '2':
          biblioteca.listar();
          break;

        case '3':
          int id = Validador.lerNumeroInteiro('ID do livro a atualizar: ');
          String titulo = Validador.lerTextoNaoVazio('Novo título: ');
          String autor = Validador.lerTextoNaoVazio('Novo autor: ');
          int ano = Validador.lerNumeroInteiro('Novo ano: ');
          biblioteca.atualizar(id, titulo, autor, ano);
          break;

        case '4':
          int id = Validador.lerNumeroInteiro('ID do livro a remover: ');
          biblioteca.remover(id);
          break;

        case '5':
          print('Saindo...');
          return;

        default:
          print('Opção inválida.');
      }
    }
  }
}
