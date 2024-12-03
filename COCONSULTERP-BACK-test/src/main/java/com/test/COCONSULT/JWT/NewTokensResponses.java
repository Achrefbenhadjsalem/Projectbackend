package com.test.COCONSULT.JWT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTokensResponses {
    private String refreshToken;
    private String newAccessToken;
}
