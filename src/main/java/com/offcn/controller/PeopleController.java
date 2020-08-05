package com.offcn.controller;
import com.offcn.entity.PageResult;
import com.offcn.pojo.People;
import com.offcn.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    /***
     * 获取全部用户信息
     * @return
     */
    @RequestMapping("/findAll")
    public List<People> getPeopleList() {
        return peopleService.getUserList();
    }

    /**
     * 分页查询
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return peopleService.findPage( page, rows );
    }

   /* @RequestMapping("/search")
    public PageResult search(@RequestBody People people, int page, int rows) {
        return peopleService.findPage(people, page, rows );
    }*/

    /***
     * 新增用户
     * @return
     */
    @PostMapping("/add")
    public String createPeople(@RequestBody People people) {
        try {
            peopleService.createUser(people);
            return "success";
        }catch (Exception e){
            return "error";
        }
    }

    /***
     * 获取指定id用户信息
     * @param pid
     * @return
     */
    @RequestMapping("/getOne")
    public People getPeople(Long pid) {

        return peopleService.getUser(pid);
    }

    /**
     * 更新指定id用户信息
     *
     * @param pid
     */
    @RequestMapping("/edit")
    public String updatePeople( Long pid, @RequestBody People people) {
      try{
          peopleService.updateUser(pid, people);
        return "success";
    }catch (Exception e){
        return "error";
    }
    }

    /***
     * 删除指定id用户
     * @param selectIds
     * @return
     */
    @RequestMapping("/delete")
    public String deletePeople( Long [] selectIds) {
        try {
            for (Long selectId : selectIds) {
                peopleService.deleteUser(selectId);
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

    }

    /**
     * 姓名模糊查询
     *
     * @param pname
     * @return
     */
    @RequestMapping("/findByPnameLike")
    public List<People> findByPnameLike(String pname) {
        return peopleService.selectName("%" + pname + "%");
    }
}