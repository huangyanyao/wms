package com.example.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms.common.QueryPageParam;
import com.example.wms.common.Result;
import com.example.wms.entity.User;
import com.example.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yy
 * @since 2023-05-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
        System.out.println("1111");
        return userService.list();

    }
    //新增
    @PostMapping("/save")
    public boolean saveUser(@RequestBody User user){
        return userService.save(user);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    //修改
    @PostMapping("/mod")
    public boolean modUser(@RequestBody User user){
        return userService.updateById(user);
    }
    //删除
    @GetMapping("/delete")
    public boolean deleteUser(Integer id){
        return userService.removeById(id);
    }
    //查询(模糊 匹配)
    @PostMapping("/listP")
    public List<User> listUser(@RequestBody User user){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName,user.getName());
        return userService.list(wrapper);

    }

    //查询(模糊 匹配)
    @PostMapping("/listPage")
    public Result listPageUser(@RequestBody QueryPageParam param){
//        if(param.getPageSize() == 0 && param.getPageNum() == 0){
//            return Result.fail();
//        }
        if(param.getParam() != null){
            //获取请求参数
            HashMap param1 = param.getParam();
            String name = (String)param1.get("name");
            //获取每页显示多少条数和分页页数
            Page<User> page = new Page();
            page.setCurrent(param.getPageNum());
            page.setSize(param.getPageSize());
            //查询条件
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(User::getName,name);
            IPage userPage = userService.page(page, wrapper);
            return Result.suc(userPage.getTotal(),userPage.getRecords());
        }
        //获取每页显示多少条数和分页页数
        Page<User> page = new Page();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageSize());
        IPage userPage = userService.page(page, null);
        return Result.suc(userPage.getTotal(),userPage.getRecords());
    }
}
