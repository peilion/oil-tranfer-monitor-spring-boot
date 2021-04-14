package cn.peilion.utils.dsp;

public class Detrend {
    public static void process(float[] data) {
        float sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        float avg = sum / data.length;
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i] - avg;
        }
    }
}
