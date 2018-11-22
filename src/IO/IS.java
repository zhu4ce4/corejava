package IO;

import java.io.*;

public class IS {
    public static void main(String[] args) {
        //将一个文件拆分成多个文件,每个文件为指定的size,但最后一个文件的size可能为<=指定的size
        File fi;
        try (BufferedInputStream dis = new BufferedInputStream(new FileInputStream(fi = new File("istest.txt")),1024*10); FileOutputStream fos = new FileOutputStream("istestresult.txt")) {//fileoutputstream里面可用append参数,不设置append,则默认为false,将删除同名文件
            int eachSize = (int) fi.length() > 10 ? 10 : (int) fi.length(); //此处10实际运用时改为合适的大小如1024及其倍数
            byte[] readout = new byte[eachSize];    //eachsize不要使用fi.length,可能太大爆掉
            while (true) {
                int realSize = dis.read(readout);   //接收实际读取的byte数量
                if (realSize == -1) {
                    break;
                }
                for (int i = 0; i < realSize; i++) {
                    System.out.print((char) readout[i]);
                }
                fos.write(readout, 0, realSize);    //  根据实际接收的数量执行写入而不是将数组内多余的0都写入
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

