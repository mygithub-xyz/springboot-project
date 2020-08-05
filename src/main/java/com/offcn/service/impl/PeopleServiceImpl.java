package com.offcn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.dao.PeopleDao;
import com.offcn.entity.PageResult;
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
    /**
     * 要使用分页的对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageResult findPage(int pageNo, int pageSize) {

        PageHelper.startPage( pageNo, pageSize );
        Page<People> page = (Page<People>) peopleDao.getAll();
        return new PageResult( page.getTotal(), page.getResult() );
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
