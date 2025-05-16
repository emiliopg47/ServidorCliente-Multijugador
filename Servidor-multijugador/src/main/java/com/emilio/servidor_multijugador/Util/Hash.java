package com.emilio.servidor_multijugador.Util;

import org.mindrot.jbcrypt.BCrypt;

public class Hash {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verificarPassword(String passwordIngresada, String hashGuardado) {
        return BCrypt.checkpw(passwordIngresada, hashGuardado);
    }
}
