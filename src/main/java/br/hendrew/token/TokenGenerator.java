package br.hendrew.token;


import java.util.Arrays;
import java.util.HashSet;

import io.smallrye.jwt.build.Jwt;

public class TokenGenerator {

    public static String generate(String userName, String roles) {
        String token =
           Jwt.upn(userName) 
             .groups(new HashSet<>(Arrays.asList(roles.split(","))))
           .sign();
        return token;
    }
}
