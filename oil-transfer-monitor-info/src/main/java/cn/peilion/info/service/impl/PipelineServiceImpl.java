package cn.peilion.info.service.impl;

import cn.peilion.info.service.PipelineService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.mappers.PipelineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PipelineServiceImpl implements PipelineService {

    @Autowired
    private PipelineMapper pipelineMapper;

    @Override
    public ResponseResult getTotal() {
        return ResponseResult.okResult(pipelineMapper.loadTotal());
    }
}
