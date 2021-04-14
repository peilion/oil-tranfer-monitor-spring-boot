package cn.peilion.utils.dsp;

import be.tarsos.dsp.util.fft.FloatFFT;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class FastFourierTransform {
    public static Map<String, float[]> process(float[] data, int sampleRate) {
        float[] dataCopy = Arrays.copyOf(data, data.length);
        FloatFFT floatFFT = new FloatFFT(dataCopy.length);
        floatFFT.realForward(dataCopy);
        float[] spectrum = new float[data.length / 2];
        int j = 0;
        for (int i = 0; i < dataCopy.length / 2; i++) {
            spectrum[i] = (float) Math.sqrt(
                    Math.pow(dataCopy[j] / data.length * 2, 2) + Math.pow(dataCopy[j + 1] / data.length * 2, 2)
            );
            j += 2;
        }

        float[] frequencyAxis = new float[dataCopy.length / 2];
        for (int i = 0; i < frequencyAxis.length; i++) {
            frequencyAxis[i] = i * sampleRate / data.length;
        }

        TreeMap<String, float[]> treeMap = new TreeMap<>();
        treeMap.put("spectrum", spectrum);
        treeMap.put("axis", frequencyAxis);
        return treeMap;
    }
}
