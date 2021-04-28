package cn.peilion.log.service.impl;

import cn.peilion.log.service.ThresholdService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.mappers.ThresholdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThresholdServiceImpl implements ThresholdService {

    @Autowired
    private ThresholdMapper thresholdMapper;

    @Override
    public ResponseResult loadList() {
        return ResponseResult.okResult(thresholdMapper.loadList());
    }
}
