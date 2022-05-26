package com.huanshi.traveldiary.pojo.dto;

import com.huanshi.traveldiary.common.annotation.Nickname;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateNicknameDto {
    private Long id;
    @Nickname
    private String nickname;
}