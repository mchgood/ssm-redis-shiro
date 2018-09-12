package com.zhidisoft.system.dao;

import com.zhidisoft.system.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleDao {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int insert(UserRole record);
    
    int insertList(@Param("records")List<UserRole> record);

    List<UserRole> selectAll();
    
    int deleteByUserIds(@Param("userIds")String[] userIdsArr);
}