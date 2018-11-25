package IO;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

//通过walk打印文件夹文件名称（含子孙目录）打印层次结构
class IOWalkFileTree {
    public static void main(String[] args) {
        try {
            getWalk(Paths.get("D:\\project\\corejava\\IOTEST"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getWalk(Path p) throws IOException {
//        System.out.println(p.getName(p.getNameCount() - 1));
//        try (Stream<Path> entries = Files.walk(p)) {    //files.walk方法产生一个遍历目录中所有子孙的stream对象
//            entries.forEach(System.out::println);    //打印出来可见entries里面返回的第一个path是p本身,用walk不用递归,另可加depth限制深度
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Files.walkFileTree(p, new SimpleFileVisitor<>() {
            int aTabNum = 0;

            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                for (int i = 0; i < aTabNum; i++) {
                    System.out.print("\t");
                }
                System.out.println(path.getFileName());
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
                for (int i = 0; i < aTabNum; i++) {
                    System.out.print("\t");
                }
                aTabNum++;
                System.out.println(path.getFileName());
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                aTabNum--;
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult visitFileFailed(Path path, IOException exc) {
                return FileVisitResult.SKIP_SUBTREE;

            }
        });
    }
}


