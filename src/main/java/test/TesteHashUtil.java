package test;

import util.HashUtil;

public class TesteHashUtil {
    public static void main(String[] args) {
        System.out.println("TesteHashUtil iniciado");

        String senha = "MinhaSenha123";
        String hash = HashUtil.gerarHash(senha);

        System.out.println("Senha: " + senha);
        System.out.println("Hash gerado: " + hash);

        boolean okCorreto = HashUtil.verificarSenha(senha, hash);
        boolean okErrado = HashUtil.verificarSenha("outraSenha", hash);

        System.out.println("Verificacao com senha correta: " + okCorreto);
        System.out.println("Verificacao com senha errada: " + okErrado);

        if (okCorreto && !okErrado) {
            System.out.println("TesteHashUtil finalizado com sucesso.");
        } else {
            System.out.println("TesteHashUtil encontrou problemas.");
        }
    }
}
