package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.DTO.OTP;

import java.net.http.HttpTimeoutException;

public interface OTPInterface {
    OTP GenerateOTp( );
    Boolean VerifOTP ( String identification )  ;
    void userstatus(String emailuser, Boolean result);
    OTP ResendOTP(String email);
    void  DeleteALLOTP();
}
