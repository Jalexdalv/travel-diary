package com.huanshi.traveldiary.pojo.dto;

import com.huanshi.traveldiary.common.annotation.Avatar;
import com.huanshi.traveldiary.common.annotation.Nickname;
import com.huanshi.traveldiary.common.annotation.Profile;
import com.huanshi.traveldiary.common.annotation.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDetailDto {
    private Long id;
    @Nickname
    private String nickname;
    @Sex
    private Integer sex;
    @Avatar
    private String avatar;
    @Profile
    private String profile;
}