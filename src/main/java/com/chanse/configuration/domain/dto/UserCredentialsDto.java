package com.chanse.configuration.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDto{

    @NotNull
    public String userName;

    //TODO obfuscate with a password server using JWT? I forget how netflix and amazons api does it but look it up
    @NotNull
    public String password;

}