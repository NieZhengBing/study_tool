package forkjoin.BaJie.impl;

import forkjoin.service.IPickTaoZi;
import forkjoin.service.IProcessTaoZi;
import forkjoin.vo.Color;
import forkjoin.vo.PanTao;
import forkjoin.vo.Size;

/**
 * @author M
 * @create 2018/2/16
 */
public class BaJiePickImpl implements IPickTaoZi{
    private IProcessTaoZi processTaoZi;

    public BaJiePickImpl(IProcessTaoZi processTaoZi) {
        this.processTaoZi = processTaoZi;
    }

    @Override
    public boolean pick(PanTao[] src, int index) {
        if (src[index].getColor() == Color.RED && src[index].getSize() == Size.BIG) {
            processTaoZi.processTaoZi(src[index]);
            return true;
        }
        return false;
    }
}
