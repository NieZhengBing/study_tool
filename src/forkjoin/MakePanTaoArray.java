package forkjoin;

import forkjoin.vo.Color;
import forkjoin.vo.PanTao;
import forkjoin.vo.Size;
import java.util.Random;

/**
 * @author M
 * @create 2018/2/16
 */
public class MakePanTaoArray {
    public static final int ARRAY_LENGTH = 40000;
    public static final int STANDARD_VAL = 66694523;

    public static PanTao[] makeArray() {
        Random rColor = new Random();
        Random rSize = new Random();
        Random rYear = new Random();
        PanTao[] result = new PanTao[ARRAY_LENGTH];
        for(int i = 0; i < ARRAY_LENGTH; i++) {
            PanTao panTao = new PanTao(
                    rColor.nextBoolean() ? Color.RED : Color.GREEN,
                    rSize.nextBoolean() ? Size.BIG : Size.SMALL,
                    rYear.nextInt(9001));
            result[i] = panTao;
        }
        return result;
    }

    public static void main(String[] args) {
        PanTao[] panTaos = makeArray();
        for(PanTao panTao : panTaos) {
            System.out.println(panTao);
        }
    }
}
