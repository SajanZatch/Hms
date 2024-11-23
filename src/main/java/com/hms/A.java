package com.hms;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {

    //below code is for admin password that we should insert manually
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("testing",BCrypt.gensalt(5)));
    }
}
