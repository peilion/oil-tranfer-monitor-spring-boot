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

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author fpl11
 */
@MappedTypes({Float[].class})
@MappedJdbcTypes({JdbcType.BLOB})
public class BlobFloatArrayTypeHandler extends BaseTypeHandler<float[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, float[] parameter, JdbcType jdbcType)
            throws SQLException {
        ByteBuffer buffer = ByteBuffer.allocate(4 * parameter.length);
        FloatBuffer floatBuffer = buffer.asFloatBuffer();
        floatBuffer.put(parameter);
        byte[] array = buffer.array();
        ByteArrayInputStream bis = new ByteArrayInputStream(array);
        ps.setBinaryStream(i, bis, parameter.length);
    }

    @Override
    public float[] getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Blob blob = rs.getBlob(columnName);
        byte[] returnValue = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        float[] floatArray = convertByteArrayToFloatArray(returnValue);
        return floatArray;
    }

    @Override
    public float[] getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Blob blob = rs.getBlob(columnIndex);
        byte[] returnValue = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        float[] floatArray = convertByteArrayToFloatArray(returnValue);
        return floatArray;
    }

    @Override
    public float[] getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Blob blob = cs.getBlob(columnIndex);
        byte[] returnValue = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        float[] floatArray = convertByteArrayToFloatArray(returnValue);
        return floatArray;
    }

    public float[] convertByteArrayToFloatArray(byte[] raw) {
        FloatBuffer fb = ByteBuffer.wrap(raw).order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer();
        float[] floatArray = new float[fb.capacity()];
        fb.get(floatArray);
        return floatArray;
    }
}