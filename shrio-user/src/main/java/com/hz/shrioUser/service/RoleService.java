package com.hz.shrioUser.service;

import com.hz.shrioUser.entity.Role;
import com.hz.shrioUser.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author： pt
 * @date： 2022/12/1 10:21
 * @discription
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findRoleByUserId(Integer id) {
        return roleMapper.findRoleByUserId(id);
    }
}