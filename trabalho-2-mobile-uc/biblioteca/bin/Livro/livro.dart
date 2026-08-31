

class Livro {
  int id;
  String titulo;
  String autor;
  int ano;

  Livro(this.id, this.titulo, this.autor, this.ano);

  @override
  String toString() {
    return 'ID: $id | Título: $titulo | Autor: $autor | Ano: $ano';
  }
}