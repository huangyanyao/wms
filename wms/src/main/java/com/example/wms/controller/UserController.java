package com.example.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms.common.QueryPageParam;
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
    public List<User> listPageUser(@RequestBody QueryPageParam param){
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(User::getName,user.getName());
        HashMap param1 = param.getParam();
        String name = (String)param1.get("name");

        Page<User> page = new Page();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageSize());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName,name);

        IPage userPage = userService.page(page, wrapper);
        return userPage.getRecords();

    }

}
