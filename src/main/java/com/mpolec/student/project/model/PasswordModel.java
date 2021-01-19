package com.mpolec.student.project.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordModel {

    @NotBlank(message = "Must not be blank")
    String oldPassword;

    @NotBlank(message = "Must not be blank")
    String newPassword;

    @NotBlank(message = "Must not be blank")
    String repeatedPassword;

}
