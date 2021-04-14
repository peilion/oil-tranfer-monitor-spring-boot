package cn.peilion.data.threadTask;

import java.util.Map;

public class ComputeSpectrumTaskResult {
    String phase;
    Map<String, float[]> result;

    public ComputeSpectrumTaskResult(String phase, Map<String, float[]> result) {
        this.phase = phase;
        this.result = result;
    }

    public String getPhase() {
        return phase;
    }

    public Map<String, float[]> getResult() {
        return result;
    }
}