package com.huanshi.traveldiary.pojo.dto;

import com.huanshi.traveldiary.common.annotation.Password;
import com.huanshi.traveldiary.common.annotation.Phone;
import com.huanshi.traveldiary.common.annotation.SmsVerifyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePasswordDto {
    @Phone
    private Long phone;
    @SmsVerifyCode
    private Integer smsVerifyCode;
    @Password
    private String password;
}