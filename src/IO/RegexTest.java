package IO;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("输入pattern");
        String patternString = in.nextLine();
        Pattern p = Pattern.compile(patternString);

        while (true) {
            System.out.println("输入string,输入exit退出");
            String input = in.nextLine();
            if (null == input || "exit".equals(input)) {
                return;
            }
            Matcher m = p.matcher(input);
            if (m.matches()) {
                System.out.println("匹配");
                int g = m.groupCount();
                if (g > 0) {
                    for (int i = 0; i < input.length(); i++) {
                        for (int j = 1; j <= g; j++) {
                            if (i == m.start(j) && i == m.end(j)) {
                                System.out.print("()");
                            }
                        }
                        for (int j = 1; j <= g; j++) {
                            if (i == m.start(j) && i != m.end(j)) {
                                System.out.print('(');
                            }
                        }
                        System.out.print(input.charAt(i));
                        for (int j = 1; j <= g; j++) {
                            if (i + 1 != m.start(j) && i + 1 == m.end(j)) {
                                System.out.print(')');
                            }
                            System.out.println();
                        }
                    }
                }
            }else {
                System.out.println("没有匹配");
            }
        }
    }
}
