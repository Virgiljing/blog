package com.aisat.hkgott.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 功能表
 * </p>
 *
 * @author cc
 * @since 2018-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Function implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能ID（功能表，基础核心框架功能）
     */
    @TableId(value = "function_id", type = IdType.AUTO)
    private Integer functionId;

    /**
     * 功能编号
     */
    private String functionNo;

    /**
     * 功能名称
     */
    private String functionName;

    /**
     * 功能英文名称
     */
    private String functionNameEn;

    /**
     * 功能名称缩写
     */
    private String functionAbbr;

    /**
     * 功能描述
     */
    private String description;

    /**
     * 功能链接
     */
    private String functionLink;

    /**
     * 功能级别
     */
    private Integer level;

    /**
     * 上级功能编号
     */
    private String upFunctionNo;

    /**
     * 检测功能是否可用，1：true；0：false
     */
    private Boolean isValid;

    /**
     * 排序
     */
    private Long displayOrder;

    /**
     * 功能图标
     */
    private String functionIcon;

    /**
     * 多个访问路径名
     */
    private String accessPaths;

    /**
     * 页面显示编号
     */
    private Integer showNo;


}
