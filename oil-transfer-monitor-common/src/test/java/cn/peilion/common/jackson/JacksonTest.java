package cn.peilion.common.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;


public class JacksonTest {

    @Test
    public void testJackson() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper = ConfusionModule.registerModule(objectMapper);


    }
}
