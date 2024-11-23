//package com.hms.Util;
//
//import org.springframework.stereotype.Component;
//
//import java.security.SecureRandom;
//
//@Component
//public class OTPUtil {
//    private static final SecureRandom random = new SecureRandom();
//    private static final int OTP_LENGTH = 6;
//
//    public static String generateOTP() {
//        StringBuilder otp = new StringBuilder();
//        for (int i = 0; i < OTP_LENGTH; i++) {
//            otp.append(random.nextInt(10)); // Append a random digit
//        }
//        return otp.toString();
//    }
//}
//
