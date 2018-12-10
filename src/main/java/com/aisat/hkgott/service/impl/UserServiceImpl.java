package com.aisat.hkgott.service.impl;

import com.aisat.hkgott.entity.User;
import com.aisat.hkgott.mapper.UserMapper;
import com.aisat.hkgott.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cc
 * @since 2018-12-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
