package cn.peilion.info.service.impl;

import cn.peilion.info.service.ServerService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.mappers.InformationSchemaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.util.HashMap;
import java.util.List;

@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private InformationSchemaMapper informationSchemaMapper;

    @Override
    public ResponseResult load() {
        SystemInfo si = new SystemInfo();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        GlobalMemory memory = hal.getMemory();
        CentralProcessor processor = hal.getProcessor();
        double systemCpuLoad = processor.getSystemCpuLoadBetweenTicks(processor.getSystemCpuLoadTicks());
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fileStores = fileSystem.getFileStores();
        long total = 0;
        long used = 0;
        for (OSFileStore osFileStore : fileStores) {
            total += osFileStore.getTotalSpace();
            used += osFileStore.getUsableSpace();
        }

        HashMap<String, Object> res = new HashMap<>();
        res.put("table_volume", ((double) informationSchemaMapper.getTableVolume() / 1024 / 1024) + "mb");
        res.put("memory_statu", (double) (memory.getTotal() - memory.getAvailable()) / memory.getTotal());
        res.put("cpu_statu", systemCpuLoad);
        res.put("disk_usage", (double) used / total);

        return ResponseResult.okResult(res);
    }

}
