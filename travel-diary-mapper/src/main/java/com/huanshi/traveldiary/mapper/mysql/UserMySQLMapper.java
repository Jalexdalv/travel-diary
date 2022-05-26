package com.huanshi.traveldiary.mapper.mysql;

import com.huanshi.traveldiary.pojo.bo.UpdateDetailBo;
import com.huanshi.traveldiary.pojo.bo.UpdatePasswordBo;
import com.huanshi.traveldiary.pojo.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMySQLMapper {
    int insert(@NotNull User user);
    int updatePassword(@NotNull UpdatePasswordBo updatePasswordBo);
    int updateDetail(@NotNull UpdateDetailBo updateDetailBo);
    int existByPhone(long phone);
    @Nullable
    User selectByPhone(long phone);
    @Nullable
    Long selectVersionById(long id);
    @Nullable
    Long selectVersionByPhone(long phone);
}