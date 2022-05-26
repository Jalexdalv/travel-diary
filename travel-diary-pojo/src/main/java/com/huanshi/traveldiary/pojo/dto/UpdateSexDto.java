package com.huanshi.traveldiary.pojo.dto;

import com.huanshi.traveldiary.common.annotation.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateSexDto {
    private Long id;
    @Sex
    private Integer sex;
}