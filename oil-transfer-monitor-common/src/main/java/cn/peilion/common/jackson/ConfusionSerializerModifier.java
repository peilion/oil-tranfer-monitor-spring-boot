package cn.peilion.common.jackson;

import cn.peilion.model.annotation.IdEncrypt;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于过滤序列化时处理的字段
 */
public class ConfusionSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                     BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        List<BeanPropertyWriter> newWriter = new ArrayList<>();
        for (BeanPropertyWriter writer : beanProperties) {
            String name = writer.getType().getTypeName();
            if (null == writer.getAnnotation(IdEncrypt.class)
                    && !name.equalsIgnoreCase("Date")
                    && !writer.getName().equalsIgnoreCase("eid")) {
                newWriter.add(writer);
            } else if (name.contains("Date")) {
                writer.assignSerializer(new DateSerializer());
                newWriter.add(writer);
            } else {//id  和有 idEncrypt 注解的属性都会走这个逻辑  都会进行加密混淆
                writer.assignSerializer(new ConfusionSerializer());
                newWriter.add(writer);
            }
        }
        return newWriter;
    }


}
