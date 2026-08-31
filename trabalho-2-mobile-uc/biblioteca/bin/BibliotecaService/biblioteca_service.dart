import '../Livro/livro.dart';

//Alexandre responsável por este código
class Biblioteca {
  List<Livro> livros = [];
  int _proximoId = 1;

  void cadastrar(String titulo, String autor, int ano) {
    livros.add(Livro(_proximoId++, titulo, autor, ano));
    print('Livro cadastrado com sucesso!');
  }

  //Alexandre responsável por este código
  void listar() {
    if (livros.isEmpty) {
      print('Nenhum livro cadastrado.');
      return;
    }
    for (var livro in livros) {
      print(livro);
    }
  }

  //Higor responsável por este código
  void atualizar(int id, String titulo, String autor, int ano) {
    for (var livro in livros) {
      if (livro.id == id) {
        livro.titulo = titulo;
        livro.autor = autor;
        livro.ano = ano;
        print('Livro atualizado com sucesso!');
        return;
      }
    }
    print('Livro não encontrado.');
  }

  //Higor responsável por este código
  void remover(int id) {
    int antes = livros.length;
    livros.removeWhere((livro) => livro.id == id);
    if (livros.length < antes) {
      print('Livro removido com sucesso!');
    } else {
      print('Livro não encontrado.');
    }
  }
}
