package cn.peilion.data.service.impl;

import cn.peilion.data.component.MeasurePointMapHolder;
import cn.peilion.data.service.ElectricDataService;
import cn.peilion.data.threadTask.ComputeSpectrumTask;
import cn.peilion.data.threadTask.ComputeSpectrumTaskResult;
import cn.peilion.model.common.dtos.DataDto;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.enums.AppHttpCodeEnum;
import cn.peilion.model.mappers.ElectricDataMapper;
import cn.peilion.model.pojos.electric.ElectricData;
import cn.peilion.model.pojos.measurePoint.MeasurePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class ElectricDataServiceImpl implements ElectricDataService {

    @Autowired
    private ElectricDataMapper electricDataMapper;
    @Autowired
    private MeasurePointMapHolder measurePointMapHolder;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public ResponseResult getById(DataDto dto, Integer measurePointId) throws InterruptedException, ExecutionException {
        MeasurePoint measurePoint = measurePointMapHolder.getCachedMeasurePoint(measurePointId);
        if (measurePoint == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "测点不存在");
        }
        if (measurePoint.getType().equals(0)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "测点类型与接口不匹配");
        }
        ElectricData electricData = electricDataMapper.loadElectricData(dto, measurePoint.getStationId(), measurePoint.getInnerStationId());

        TreeMap<String, Object> res = new TreeMap<>();
        res.put("electric_data", electricData);

        List<Future<ComputeSpectrumTaskResult>> taskList = new ArrayList<>();
        taskList.add(threadPoolTaskExecutor.submit(new ComputeSpectrumTask(electricData.getUcur(), "U")));
        taskList.add(threadPoolTaskExecutor.submit(new ComputeSpectrumTask(electricData.getVcur(), "V")));
        taskList.add(threadPoolTaskExecutor.submit(new ComputeSpectrumTask(electricData.getWcur(), "W")));
        for (Future<ComputeSpectrumTaskResult> future : taskList) {
            ComputeSpectrumTaskResult taskResult = future.get();
            res.put(taskResult.getPhase(), taskResult.getResult());
        }

        return ResponseResult.okResult(res);

    }

}
