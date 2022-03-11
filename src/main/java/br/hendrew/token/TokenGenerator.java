package br.hendrew.token;


import java.util.Arrays;
import java.util.HashSet;

import io.smallrye.jwt.build.Jwt;

public class TokenGenerator {

    public static String generate(String email, String roles) {
        String token =
           Jwt.upn(email) 
             .groups(new HashSet<>(Arrays.asList(roles.split(","))))
           .sign();
        return token;
    }
}
