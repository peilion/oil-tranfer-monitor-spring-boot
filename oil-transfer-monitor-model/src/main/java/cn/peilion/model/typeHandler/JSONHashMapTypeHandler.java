/**
 * Copyright 2009-2015 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.peilion.model.typeHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;


/**
 * @author fpl11
 */
@MappedTypes({HashMap.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class JSONHashMapTypeHandler extends BaseTypeHandler<HashMap> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TypeReference<HashMap> typeRef = new TypeReference<HashMap>() {};


    @SneakyThrows
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, HashMap parameter, JdbcType jdbcType) {
        String s = objectMapper.writeValueAsString(parameter);
        ps.setString(i, s);
    }

    @SneakyThrows
    @Override
    public HashMap getNullableResult(ResultSet rs, String columnName) {
        return objectMapper.readValue(rs.getString(columnName), typeRef);
    }

    @SneakyThrows
    @Override
    public HashMap getNullableResult(ResultSet rs, int columnIndex) {
        return objectMapper.readValue(rs.getString(columnIndex), typeRef);
    }

    @SneakyThrows
    @Override
    public HashMap getNullableResult(CallableStatement cs, int columnIndex) {
        return objectMapper.readValue(cs.getString(columnIndex), typeRef);
    }

}