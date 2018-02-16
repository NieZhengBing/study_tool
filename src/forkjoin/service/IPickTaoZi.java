package forkjoin.service;

import forkjoin.vo.PanTao;

/**
 * Created by M on 2018/2/16.
 */
public interface IPickTaoZi {
    boolean pick(PanTao[] src, int index);
}
