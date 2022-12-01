package com.hz.shrioUser.mapper;

import com.hz.shrioUser.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author： pt
 * @date： 2022/12/1 10:07
 * @discription
 */
@Mapper
@Repository
public interface UserMapper {

    User findByAccount(@Param("account") String account);

}