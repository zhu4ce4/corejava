package IO;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIPIO {
    public static void main(String[] args) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("result.zip"))) {
            String[] filenames = {"employee.txt", "istest.txt"};
            for (String namePath : filenames) {
                ZipEntry ze = new ZipEntry(namePath);
                zos.putNextEntry(ze);
                try (FileInputStream fis = new FileInputStream(namePath)) {
                    int length;
                    byte[] buffer = new byte[1024];
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream("result.zip"))) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                //此处无法得到entry.getsize()始终返回-1，原因未知，疑似打包zip时遗漏信息,没有设置size？
                byte[] bts = new byte[1024];
                while (true) {
                    int readoutsize = zis.read(bts);
                    if (readoutsize == -1) {
                        break;
                    }
                    String name = entry.getName();
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(String.format("./zipresult/%s", name)))) {
                        bos.write(bts, 0, readoutsize);
                    }
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
