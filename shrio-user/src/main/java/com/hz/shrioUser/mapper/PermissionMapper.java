package com.hz.shrioUser.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author： pt
 * @date： 2022/12/1 9:58
 * @discription
 */
@Mapper
@Repository
public interface PermissionMapper {

    List<String> findByRoleId(@Param("roleIds") List<Integer> roleIds);

}
