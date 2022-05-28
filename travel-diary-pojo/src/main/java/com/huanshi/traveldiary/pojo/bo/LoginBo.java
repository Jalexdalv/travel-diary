package com.huanshi.traveldiary.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginBo {
    private Long id;
    private Long imei;
    private String token;
}