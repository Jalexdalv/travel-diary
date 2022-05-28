package com.huanshi.traveldiary.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private Long phone;
    private String nickname;
    private String password;
    private Integer sex;
    private String avatar;
    private String profile;
    private Integer follows;
    private Integer fans;
    private Integer likes;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Integer deleted;
    private Long version;
}