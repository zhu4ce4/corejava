package JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class JDBCTest {
    public static void main(String[] args) {
        try (Scanner in = args.length == 0 ? new Scanner(System.in) : new Scanner(Paths.get(args[0]), "utf-8")) {
            try (Connection conn = getConnection(); Statement s = conn.createStatement()) {
                while (true) {
                    if (args.length == 0) {
                        System.out.println("输入command或者exit退出：");
                    }
                    if (!in.hasNextLine()) {
                        return;
                    }
                    String line = in.nextLine().trim();
                    if (line.equalsIgnoreCase("exit")) {
                        return;
                    }
                    if (line.endsWith(";")) {
                        line = line.substring(0, line.length() - 1);
                    }
                    try {
                        boolean isResult = s.execute(line);
                        if (isResult) {
                            try (ResultSet rs = s.getResultSet()) {
                                showResultSet(rs);
                            }
                        } else {
                            int updateCount = s.getUpdateCount();
                            System.out.println(updateCount + " rows updated");
                        }
                    } catch (SQLException es) {
                        for (Throwable e : es) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SQLException es) {
                for (Throwable e : es) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(System.getProperty("user.dir"), "src/JDBC", "mysql.properties"))) {
            props.load(in);
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String pwd = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, pwd);
    }

    public static void showResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) {
                System.out.print("\t");
            }
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) {
                    System.out.print("\t");
                }
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
    }
}
