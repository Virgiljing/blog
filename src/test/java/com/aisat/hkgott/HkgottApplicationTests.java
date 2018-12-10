package com.aisat.hkgott;

import com.aisat.hkgott.entity.User;
import com.aisat.hkgott.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HkgottApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    public void contextLoads() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name","admin");
        queryWrapper.eq("password","123");
        //密码进行加密处理  明文为  password+name
        //String paw = password+name;

        // 从数据库获取对应用户名密码的用户
        User user = userService.getOne(queryWrapper);
        System.out.println(user);
    }

}
