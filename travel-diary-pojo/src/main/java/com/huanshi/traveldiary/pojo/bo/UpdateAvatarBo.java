package com.huanshi.traveldiary.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateAvatarBo {
    private Long id;
    private String avatar;
    private Long version;
}