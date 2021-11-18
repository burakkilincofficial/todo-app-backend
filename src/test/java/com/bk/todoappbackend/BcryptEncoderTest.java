package com.bk.todoappbackend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * TODO Describe this class.
 * @since 2021-11-17
 * @author burak kilinc
 */
public class BcryptEncoderTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for(int i=0;i<3;i++){
            String encodeString = encoder.encode("123");
            System.out.println(encodeString);
        }
    }
}
