package com.offcn.service.impl;

import com.offcn.dao.PeopleDao;
import com.offcn.pojo.People;
import com.offcn.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleDao peopleDao;

    @Override
    public List<People> getUserList() {
        return peopleDao.getAll();
    }

    @Override
    public void createUser(People people) {
        peopleDao.save(people);
    }

    @Override
    public People getUser(Long pid) {
        return peopleDao.findOne(pid);
    }

    @Override
    public void updateUser(Long pid, People people) {
        people.setPid(Math.toIntExact(pid));
        peopleDao.update(people);
    }

    @Override
    public void deleteUser(Long pid) {
        peopleDao.delete(pid);
    }

    @Override
    public List<People> selectName(String name) {
        return peopleDao.findByPnameLike(name);
    }
}
