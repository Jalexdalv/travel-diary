package com.huanshi.traveldiary.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateNicknameBo {
    private Long id;
    private String nickname;
    private Long version;
}