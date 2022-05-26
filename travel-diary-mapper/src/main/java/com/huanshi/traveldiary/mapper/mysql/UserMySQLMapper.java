package com.huanshi.traveldiary.mapper.mysql;

import com.huanshi.traveldiary.pojo.bo.UpdateAvatarBo;
import com.huanshi.traveldiary.pojo.bo.UpdateNicknameBo;
import com.huanshi.traveldiary.pojo.bo.UpdatePasswordBo;
import com.huanshi.traveldiary.pojo.bo.UpdateProfileBo;
import com.huanshi.traveldiary.pojo.bo.UpdateSexBo;
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
    int updateNickname(@NotNull UpdateNicknameBo updateNicknameBo);
    int updateSex(@NotNull UpdateSexBo updateSexBo);
    int updateAvatar(@NotNull UpdateAvatarBo updateAvatarBo);
    int updateProfile(@NotNull UpdateProfileBo updateProfileBo);
    int existByPhone(long phone);
    @Nullable
    User selectByPhone(long phone);
    @Nullable
    Long selectVersionById(long id);
    @Nullable
    Long selectVersionByPhone(long phone);
}