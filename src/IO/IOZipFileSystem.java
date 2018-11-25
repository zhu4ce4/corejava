package IO;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class IOZipFileSystem {
    public static void main(String[] args) {

        try (FileSystem fs = FileSystems.newFileSystem(Paths.get("IOZIPTEST.zip"), null)) {
            Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    System.out.println(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        String zipname = "D:\\project\\corejava\\IOZIPTEST.zip";
        try (FileSystem fss = FileSystems.newFileSystem(Paths.get(zipname), null)) {
            Files.copy(fss.getPath("IOTEST\\SRC\\test.txt"), Paths.get("D:\\project\\corejava\\IOTEST\\SRC\\notexists\\new\\newtwo\\tt.txt"));
            //通过复制解压缩
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }
}
