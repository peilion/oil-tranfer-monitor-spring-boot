package cn.peilion.common.restTemplate;

import cn.peilion.model.common.dtos.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

public class Map2ObjectUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static <T> Type constructType(Class<T> cls) {
        return mapper.getTypeFactory().constructParametricType(
                ResponseResult.class, cls);
    }

    public static <T> Type constructTypeList(Class<T> cls) {
        return mapper.getTypeFactory().constructParametricType(
                ResponseResult.class,
                mapper.getTypeFactory().constructCollectionType(List.class, cls));
    }
}