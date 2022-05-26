package com.huanshi.traveldiary.pojo.dto;

import com.huanshi.traveldiary.common.annotation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendSmsVerifyCodeDto {
    @Phone
    private Long phone;
}