package com.offcn.controller;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.People;
import com.offcn.service.PeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "管理相关接口")
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    /***
     * 获取全部用户信息
     * @return
     */
    @GetMapping("/findAll")
    @ApiOperation("查询所有用户的接口")
    public List<People> getPeopleList() {
        return peopleService.getUserList();
    }

    /**
     * 分页查询
     */
   /* @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return peopleService.findPage( page, rows );
    }
*/
    @PostMapping("/search")
    @ApiOperation("分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "int",defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "条数", dataType = "int",defaultValue = "10"),
    })
    public PageResult search(@RequestBody People people, int page, int rows) {
        return peopleService.findPage(people, page, rows );
    }

    /***
     * 新增用户
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加用户的接口")
    public Result createPeople(@RequestBody People people) {
        try {
            peopleService.createUser(people);
            return new Result(true,"添加成功！！！");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败！，请重试！！");
        }
    }

    /***
     * 获取指定id用户信息
     * @param pid
     * @return
     */

    @GetMapping("/getOne")
    @ApiOperation("查询用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "用户id")
    })
    public People getPeople(Long pid) {

        return peopleService.getUser(pid);
    }

    /**
     * 更新指定id用户信息
     *
     * @param pid
     */
    @PutMapping("/edit")
    @ApiOperation("修改用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "用户id")
    })
    public Boolean updatePeople( Long pid, @RequestBody People people) {
      try{
          peopleService.updateUser(pid, people);
        return true;
    }catch (Exception e){
        return false;
    }
    }

    /***
     * 删除指定id用户
     * @param selectIds
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "selectIds", value = "用户id集合",dataType = "Integer",allowMultiple = true, required = true )

    })
    public Boolean deletePeople( Long [] selectIds) {
        try {
            for (Long selectId : selectIds) {
                peopleService.deleteUser(selectId);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}