package cn.peilion.data.threadTask;

import cn.peilion.utils.dsp.Detrend;
import cn.peilion.utils.dsp.FastFourierTransform;

import java.util.concurrent.Callable;

/**
 * @author fpl11
 */
public class ComputeSpectrumTask implements Callable<ComputeSpectrumTaskResult> {
    private float[] phaseCurrent;
    private String phase;

    public ComputeSpectrumTask(float[] phaseCurrent,String phase) {
        this.phaseCurrent = phaseCurrent;
        this.phase = phase;
    }

    @Override
    public ComputeSpectrumTaskResult call() throws Exception {
        Detrend.process(phaseCurrent);
        return new ComputeSpectrumTaskResult(this.phase,FastFourierTransform.process(phaseCurrent, 10000));
    }

}