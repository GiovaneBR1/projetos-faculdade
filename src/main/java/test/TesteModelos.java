 package test;

import model.Usuario;
import model.Acesso;
import java.time.LocalDate;

public class TesteModelos {
    public static void main(String[] args) {
        System.out.println("TesteModelos iniciado");

        Usuario u = new Usuario("usuario@exemplo.com", "hash-fake", "Fulano", LocalDate.of(1995, 5, 10));
        u.setId(42);
        System.out.println("Usuario:");
        System.out.println("  id = " + u.getId());
        System.out.println("  nome = " + u.getNome());
        System.out.println("  email = " + u.getEmail());
        System.out.println("  dataNascimento = " + u.getDataNascimento());
        System.out.println("  senhaHash = " + u.getSenhaHash());

        Acesso a = new Acesso(true, 5, u.getId());
        a.setId(7);
        System.out.println("Acesso:");
        System.out.println("  id = " + a.getId());
        System.out.println("  idUsr = " + a.getIdUsr());
        System.out.println("  ativo = " + a.isAtivo());
        System.out.println("  tentativasRest = " + a.getTentativasRest());

        // Teste de setters
        a.setTentativasRest(3);
        a.setAtivo(false);
        System.out.println("Acesso após alteração:");
        System.out.println("  ativo = " + a.isAtivo());
        System.out.println("  tentativasRest = " + a.getTentativasRest());

        System.out.println("TesteModelos finalizado com sucesso.");
    }
}
