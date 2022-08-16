package br.com.trackertemplateback.util.crypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Implementacao do PasswordEncoder que usa a funcao hash BCrypt.
 *
 * @author Cedric Christian on 30/04/2021
 */
public class PasswordEncoder {

    /**
     * Metodo para criptografar utilizando como base BCryptPasswordEncoder.
     *
     * @param text Texto a ser criptografado
     * @return Parametro criptografado
     * sample PasswordEncoder.encode(" myPassword123 ")
     */
    public static String encode(String text) {
        return new BCryptPasswordEncoder().encode(text);
    }
}
