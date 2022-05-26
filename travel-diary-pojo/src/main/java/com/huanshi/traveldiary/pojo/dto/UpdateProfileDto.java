package com.huanshi.traveldiary.pojo.dto;

import com.huanshi.traveldiary.common.annotation.Avatar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProfileDto {
    private Long id;
    @Avatar
    private String profile;
}