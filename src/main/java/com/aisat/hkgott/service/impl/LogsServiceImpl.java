package com.aisat.hkgott.service.impl;

import com.aisat.hkgott.entity.Logs;
import com.aisat.hkgott.mapper.LogsMapper;
import com.aisat.hkgott.service.ILogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志 服务实现类
 * </p>
 *
 * @author cc
 * @since 2018-12-03
 */
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs> implements ILogsService {

}
