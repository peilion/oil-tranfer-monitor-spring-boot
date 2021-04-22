package cn.peilion.model.mappers;

import cn.peilion.model.pojos.log.MaintenanceRecordStat;

import java.util.List;

public interface MaintenanceRecordMapper {

    List<MaintenanceRecordStat> loadStatByStatu();
}
