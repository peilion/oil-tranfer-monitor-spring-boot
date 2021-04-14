package cn.peilion.utils.common;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author fpl11
 */
public class BlobUtils {
    public static float[] convertByteArrayToFloatArray(byte[] raw) {
        FloatBuffer fb = ByteBuffer.wrap(raw).order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer();
        float[] floatArray = new float[fb.capacity()];
        fb.get(floatArray);
        return floatArray;
    }
}
