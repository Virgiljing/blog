package com.aisat.hkgott.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色功能表
 * </p>
 *
 * @author cc
 * @since 2018-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色功能ID（角色功能表，基础核心框架功能）
     */
    @TableId(value = "role_function_id", type = IdType.AUTO)
    private Integer roleFunctionId;

    /**
     * 角色ID（角色表（role）的ID）
     */
    private Integer roleId;

    /**
     * 功能编号（功能表（function）的function_no）
     */
    private String functionNo;


}
