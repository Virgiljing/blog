package com.aisat.hkgott.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Result
 *
 * @author virgilin
 * @date 2018/12/4
 */
@Getter
@Setter
@Data
public class Result<T> {
    private String messages;
    private int code;
    private T data;
    private Long count;

}
