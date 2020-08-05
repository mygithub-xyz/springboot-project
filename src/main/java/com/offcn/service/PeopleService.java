package com.offcn.service;

import com.offcn.pojo.People;

import java.util.List;

public interface PeopleService {

    //获取全部用户数据
    public List<People> getUserList();

    //新增用户数据
    public void createUser(People people);

    //获取指定id用户信息
    public People getUser(Long pid);

    //更新指定id用户信息
    public void updateUser(Long pid, People people);

    //删除指定id用户
    public void deleteUser(Long pid);

    //名字模糊
    public List<People> selectName(String name);
}
