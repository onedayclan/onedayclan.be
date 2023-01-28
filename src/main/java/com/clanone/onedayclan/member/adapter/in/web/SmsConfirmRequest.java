package com.clanone.onedayclan.member.adapter.in.web;

//import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SmsConfirmRequest {
//    @NotBlank
    private String phoneNumber;
//    @NotBlank
    private String authorizationNumber;

}
