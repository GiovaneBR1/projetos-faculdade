package util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import config.AppConfig;

public class HashUtil {

    public static String gerarHash(String senha) {
        String combinado = senha + AppConfig.PALAVRA_PASSE_SISTEMA;
        return BCrypt.withDefaults().hashToString(12, combinado.toCharArray());
    }

    public static boolean verificarSenha(String senha, String hash) {
        String combinado = senha + AppConfig.PALAVRA_PASSE_SISTEMA;
        BCrypt.Result resultado = BCrypt.verifyer().verify(combinado.toCharArray(), hash);
        return resultado.verified;
    }
}
