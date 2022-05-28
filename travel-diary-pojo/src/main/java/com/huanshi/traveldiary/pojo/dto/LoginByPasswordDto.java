package com.huanshi.traveldiary.pojo.dto;

import com.huanshi.traveldiary.common.annotation.Password;
import com.huanshi.traveldiary.common.annotation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginByPasswordDto {
    private Long imei;
    @Phone
    private Long phone;
    @Password
    private String password;
}