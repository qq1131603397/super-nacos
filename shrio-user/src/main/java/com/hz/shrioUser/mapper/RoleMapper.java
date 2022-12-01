package com.hz.shrioUser.mapper;

import com.hz.shrioUser.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author： pt
 * @date： 2022/12/1 10:00
 * @discription
 */
@Mapper
@Repository
public interface RoleMapper {

    List<Role> findRoleByUserId(@Param("userId") Integer userId);

}
