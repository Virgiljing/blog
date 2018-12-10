package com.aisat.hkgott.controller;


import com.aisat.hkgott.entity.User;
import com.aisat.hkgott.service.IUserService;
import com.aisat.hkgott.utils.LocaleMessageSourceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cc
 * @since 2018-12-03
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    LocaleMessageSourceUtil localeMessageSourceUtil;

    @Autowired
    private IUserService userService;


    @RequestMapping("/list")
    public List<User> list(){
        return userService.list(null);
    }

    @RequestMapping("/get/{id}")
    public User get(@PathVariable(value = "id") int id){
        return userService.getById(id);
    }

    @PostMapping("/add")
    public String add(@Validated User user){
        user.setCreateTime(LocalDateTime.now());
        user.setName(user.getUserName());
        userService.save(user);
        return "success";
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request){
        String welcome = localeMessageSourceUtil.getMessage("welcome");
        System.out.println(welcome);
        return welcome;
    }

    @GetMapping("/date")
    public String dateHandler(@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") LocalDateTime time){
        LocalTime localTime = time.toLocalTime();
        System.out.println(localTime);

        return "success";
    }
    @GetMapping("/dates")
    public String dateHandlers(Date time){

        System.out.println(time);

        return "success";
    }
}
