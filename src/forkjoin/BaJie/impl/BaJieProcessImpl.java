package forkjoin.BaJie.impl;

import forkjoin.service.IProcessTaoZi;
import forkjoin.vo.PanTao;

/**
 * @author M
 * @create 2018/2/16
 */
public class BaJieProcessImpl implements IProcessTaoZi{
    @Override
    public void processTaoZi(PanTao taozi) {
        eat();
    }

    private void eat() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
