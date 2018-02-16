package forkjoin.SunWuKong.impl;

import forkjoin.service.IProcessTaoZi;
import forkjoin.vo.PanTao;

/**
 * @author M
 * @create 2018/2/16
 */
public class WuKongProcessImpl implements IProcessTaoZi{

    @Override
    public void processTaoZi(PanTao taozi) {
        inBag();
    }

    private void inBag() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
