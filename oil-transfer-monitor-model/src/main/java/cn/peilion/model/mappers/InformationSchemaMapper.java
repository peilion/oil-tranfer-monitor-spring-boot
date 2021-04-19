package cn.peilion.model.mappers;

import org.apache.ibatis.annotations.Select;

public interface InformationSchemaMapper {

    @Select("select sum(data_length) as volume from information_schema.TABLES")
    float getTableVolume();

}