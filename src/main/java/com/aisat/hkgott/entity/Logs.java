package com.aisat.hkgott.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日志
 * </p>
 *
 * @author cc
 * @since 2018-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志编号（日志表，基础核心框架功能）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题[来源]
     */
    private String title;

    /**
     * 内容[所操作并变量的事情]
     */
    private String content;

    /**
     * 日志类型
     */
    private Integer logType;

    /**
     * 操作者
     */
    private String handler;

    /**
     * 操作的机器IP
     */
    private String hostIp;

    private LocalDateTime createTime;


}
