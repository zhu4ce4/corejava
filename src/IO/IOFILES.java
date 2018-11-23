package IO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class IOFILES {
    public static void main(String[] args) {
        Path p = Paths.get(System.getProperty("user.dir"), "IOTEST/SRC/test.txt");
        Path notexists = Paths.get(System.getProperty("user.dir"), "IOTEST/SRC/notexists/notexists.txt");
        Path tar = Paths.get(System.getProperty("user.dir"), "IOTEST/TAR/testresult.txt");

        try {
            Files.createDirectories(notexists.getParent());
            Files.createFile(notexists);

            File f = tar.toFile();
            f.renameTo(p.toFile()); //区分file.renameto 与files.copy
            if (!tar.toFile().getParentFile().exists()) {
                Files.createDirectories(tar.getParent());
            }
            if (!tar.toFile().exists()) {
                Files.createFile(tar);
            }
            Files.copy(p, tar);
            System.out.println(Files.isRegularFile(p));
            System.out.println(Files.isDirectory(notexists.getParent()));
            Files.move(p, tar, StandardCopyOption.ATOMIC_MOVE);
            InputStream is = Files.newInputStream(p);
            OutputStream os = Files.newOutputStream(p);

            Reader r = Files.newBufferedReader(p);
            Writer w = Files.newBufferedWriter(p);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
