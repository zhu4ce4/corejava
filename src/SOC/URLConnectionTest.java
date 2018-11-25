package SOC;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class URLConnectionTest {
    public static void main(String[] args) {
        try {
            String urlName;
            if (args.length > 0) {
                urlName = args[0];
            } else {
                urlName = "http://www.baidu.com";
            }

            URL url = new URL(urlName);
            java.net.URLConnection conn = url.openConnection();
            if (args.length > 2) {
                String username = args[1];
                String password = args[2];
                String input = username + ":" + password;
                Base64.Encoder encoder = Base64.getEncoder();
                String encoding = encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
                conn.setRequestProperty("Authorization", "Basic" + encoding);
            }
            conn.connect();


            int n = 1;
            String headerskey = null;
            while (null != (headerskey = conn.getHeaderFieldKey(n++))) {
                System.out.println(headerskey);
            }

//            Set<String> resultSet=headers.keySet();
//            for (String s : resultSet) {
//                List<String> re = headers.get(s);
//                System.out.println(s+":\t"+re.toString());
//            }
            Map<String, List<String>> headers = conn.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                for (String value : entry.getValue()) {
                    System.out.println(key + "：" + value);
                }
            }
            System.out.println("---------");
            System.out.println("getcontenttype \t-->\t" + conn.getContentType());
            System.out.println("getcontentEncoding \t-->\t" + conn.getContentEncoding());
            System.out.println("getdate \t-->\t" + conn.getDate());
            System.out.println("getExpiration \t-->\t" + conn.getExpiration());
            System.out.println("getlastmodified \t-->\t" + conn.getLastModified());

            String encoding = conn.getContentEncoding();
            if (encoding == null) {
                encoding = "utf-8";
            }
            try (Scanner in = new Scanner(conn.getInputStream(), encoding)) {
                for (int i = 1; in.hasNextLine() && i < 11; i++) {
                    System.out.println(in.nextLine());
                }
                if (in.hasNextLine()) {
                    System.out.println("........");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
