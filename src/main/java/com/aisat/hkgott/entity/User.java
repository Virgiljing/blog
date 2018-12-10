package com.aisat.hkgott.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author cc
 * @since 2018-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名（昵称）
     */
    private String name;

    /**
     * 账号
     */
    @NotEmpty(message = "{position.name.null}")
    @Length(min = 2, max = 5, message = "{position.name.length}")
    private String userName;

    /**
     * 密码
     */
    @NotEmpty(message = "{position.name.null}")
    @Length(min = 5, max = 13, message = "{position.name.length}")
    private String password;

    /**
     * 邮箱
     */
    @Email(message = "{email.error}")
    private String email;

    /**
     * 手机号
     */

    private String phone;

    /**
     * 是否为超级管理员1：true
     */
    private Boolean isAdmin;

    /**
     * 区域编号
     */
    private String areaCode;

    /**
     * 登录次数
     */
    private Long loginCount;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTime;

    /**
     * 创建时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 运营商ID
     */
    private String operatorId;

    /**
     * 地址
     */
    private String address;


}
