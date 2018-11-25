package IO;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class IOPath {
    public static void main(String[] args) {
        Path abs = Paths.get("d:/project/corejava/IOTEST/SRC");
        System.out.println(abs);
        Path abso = Paths.get("d:/project/corejava", "IOTEST", "SRC");
        System.out.println(abso);
        Path relative = Paths.get("IOTEST", "SRC");
        System.out.println(relative);

        String baseDir = System.getProperty("user.dir");
        Path relat = Paths.get(baseDir, "IOTEST", "SRC", "test.txt");
        System.out.println(relat);
        Path relativ = Paths.get("IOTEST", "SRC");
        Path myworkpath = Paths.get(baseDir).resolve(relat);    //p.resovle(q);q是绝对路径则结果就是q
        System.out.println(myworkpath);
        Path workpath = Paths.get(baseDir).resolve(relativ).resolve("test.txt");   //p.resolve(q);q不是绝对路径,则p后面跟着q,q可以使path或者字符串
        System.out.println(workpath);
        System.out.println("**********");
        Path p0 = workpath.getName(3);  //按照对路径进行划分为0-N取路径中的文件名
        System.out.println(p0);
        Path p = workpath.getParent();
        System.out.println(p);
        Path p1 = workpath.getFileName();
        System.out.println(p1);
        Path p2 = workpath.getRoot();
        System.out.println(p2);

        try {
            Scanner in = new Scanner(Paths.get(baseDir + "/IOTEST/SRC/test.txt"));
            System.out.println(in.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
