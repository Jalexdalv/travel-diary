package com.huanshi.traveldiary.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDetailBo {
    private Long id;
    private String nickname;
    private Integer sex;
    private String avatar;
    private String profile;
    private Long version;
}