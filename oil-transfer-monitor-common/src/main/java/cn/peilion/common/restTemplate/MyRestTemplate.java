package cn.peilion.common.restTemplate;

import cn.peilion.model.common.dtos.ResponseResult;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class MyRestTemplate {


    private static final String HTTP_STATUS_SUCCESS = "200";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public <T> T postForResult(String url, Class<T> cls, Object body, Object... uriVariables) {
        ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, getHeaders()), String.class, uriVariables);
        return getResultBody(entity, cls);
    }

    public <T> List<T> postForResultList(String url, Class<T> cls, Object body, Object... uriVariables) {
        ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, getHeaders()), String.class, uriVariables);
        return getListResultBody(entity, cls);
    }

    public ResponseResult getForResult(String url, Object... uriVariables) throws JsonProcessingException {
        ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.GET, null, String.class, uriVariables);
        ResponseResult responseResult = objectMapper.readValue(entity.getBody(), ResponseResult.class);

        return responseResult;
    }


    private <T> T getResultBody(ResponseEntity<String> responseEntity, Class<T> cls) {
        ResponseResult<T> result = JSON.parseObject(responseEntity.getBody(), Map2ObjectUtils.constructType(cls));
        checkResultCode(result);
        return result.getData();
    }

    private <T> List<T> getListResultBody(ResponseEntity<String> responseEntity, Class<T> cls) {
        ResponseResult<List<T>> result = JSON.parseObject(responseEntity.getBody(), Map2ObjectUtils.constructTypeList(cls));
        checkResultCode(result);
        return result.getData();
    }

    private HttpHeaders getHeaders() {
        return getHeaders(MediaType.APPLICATION_JSON, null);
    }

    /**
     * 调用微服务的默认请求头
     *
     * @return
     */
    private HttpHeaders getHeaders(MediaType mediaType, Map<String, String> extHeaders) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        if (extHeaders != null) {
            for (Map.Entry<String, String> h : extHeaders.entrySet()) {
                headers.add(h.getKey(), h.getValue());
            }
        }

        return headers;
    }

    private <T> void checkResultCode(ResponseResult<T> result) {
        if (null == result) {
            throw new RuntimeException("result body is null");
        }
        if (!isSuccess(result.getCode())) {
            throw new RuntimeException(result.getErrorMessage());
        }
    }

    private boolean isSuccess(Integer code) {
        return code.equals(HTTP_STATUS_SUCCESS);
    }
}