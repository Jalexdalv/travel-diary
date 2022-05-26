package com.huanshi.traveldiary.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePasswordBo {
    private Long phone;
    private String password;
    private Long version;
}