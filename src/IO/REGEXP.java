package IO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REGEXP {
//    public static void main(String[] args) {
////        String str = "[sd]dddd]re]\n[rerep7778]";
////        Pattern p = Pattern.compile("^\\[.+]$",Pattern.MULTILINE); //要包含[，则[必须是第一项，且须要转义，]则不用；.+?中的?表示非贪婪匹配，去掉?则是贪婪匹配
////        Pattern p = Pattern.compile("^\\[.+]$"); //若str中包含\n则无法find成功，需设置RegExp 对象的Multiline 属性，^ $才会与"\n"或"\r"之后的位置匹配。
//
//        String str2 = "'FRED' AND \"fred\" and \"fredddd' so";
//
//        //Pattern p2=Pattern.compile("['\"].+?['\"]");
//        Pattern p2=Pattern.compile("(['\"]).+?\\1",Pattern.CASE_INSENSITIVE);    //注意与上一行的区别,通过将(['"])括起来,并使用\\1引用实现前后一致
//
////        String str1 = "";
////        Pattern p1 = Pattern.compile("");
//
//        Matcher m = p2.matcher(str2);   //str2可以使任何实现了charsequence接口的类的对象，如string,stringbuilder,charbuffer.
//        while (m.find()) {
////            if (m.lookingAt()){
////            if (m.matches()) {
//                System.out.println(m.group());
//            }
//        }
//    }

//class MatcherTest {
//
//    public static void main(String[] args) {
//        Pattern p = Pattern.compile("\\d{3,5}");
//        String str = "123-34345-2394-00";
//        Matcher m = p.matcher(str);
//
//        //虽然匹配失败，但由于charSequence里面的"123"和pattern是匹配的,所以下次的匹配从位置4开始
//        print(m.matches());
//        //matches: 整个匹配(m模式匹配尝试：p中的regex与str不能整体匹配成功)，只有整个字符序列完全匹配成功，才返回True，否则返回False。
//        // 但如果前部分匹配成功（整体匹配失败，但123与p中的regex匹配成功，移动到位置4），将移动下次匹配的位置。
//        print(m.find());    //find: 部分匹配，从当前位置开始匹配，找到一个匹配的子串，将移动下次匹配的位置。
//        print(m.start());
//
//        //使用reset方法重置匹配位置
//        m.reset();  //reset:  给当前的Matcher对象配上个新的目标，目标是就该方法的参数；如果不给参数，reset会把Matcher设到当前字符串的开始处。
//
//        //第一次find匹配以及匹配的目标和匹配的起始位置
//        print(m.find());    //find: 部分匹配，从当前位置开始匹配，找到一个匹配的子串，将移动下次匹配的位置。
//        print(m.group() + " - " + m.start());   //第一次find出来的group结果
//        //第二次find匹配以及匹配的目标和匹配的起始位置
//        print(m.find());    //接着第一次find之后的位置接着find
//        print(m.group() + " - " + m.start());   //给出第二次find出来的结果
//
//        //第一次lookingAt匹配以及匹配的目标和匹配的起始位置
//        print(m.lookingAt());   //lookingAt: 部分匹配，总是从第一个字符进行匹配,匹配成功了不再继续匹配，匹配失败了,也不继续匹配。
//        print(m.group() + " - " + m.start());
//
//        //第二次lookingAt匹配以及匹配的目标和匹配的起始位置
//        print(m.lookingAt());   //总是返回到第一个字符开始尝试进行匹配
//        print(m.group() + " - " + m.start());
//
//        //使用reset方法重置匹配位置
//        m.reset();
//        //遍历所有匹配的子串
//        while (m.find()) {
//            print(m.group() + " - " + m.start());
//        }
//
//    }
//
//    public static void print(Object o) {
//        System.out.println(o);
//    }
//
//
//}


    public static void main(String[] args) {
        String REGEX = "a*b";
        String INPUT = "aabfooaabfooabfoobkkk";
        String REPLACE = "-";

        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        StringBuffer sb = new StringBuffer();
        while(m.find()){    //用if代替while实现查找并替换第一个，用while是针对所有匹配项
            m.appendReplacement(sb,REPLACE);    //查找并替换
        }
//        m.appendTail(sb); //若不用此行，则后面未能匹配的内容不会append进sb，用此行则后面未能匹配的内容也一并append到sb里面，达到查找替换的效果
        System.out.println(sb.toString());
    }
}


/*
可以使用alt+enter 选择edit regexp fragment实现自动转义

在其他语言中，\\ 表示：我想要在正则表达式中插入一个普通的（字面上的）反斜杠，请不要给它任何特殊的意义。
在 Java 中，\\ 表示：我要插入一个正则表达式的反斜线，所以其后的字符具有特殊的意义。
所以，在其他的语言中（如Perl），一个反斜杠 \ 就足以具有转义的作用，而在 Java 中正则表达式中则需要有两个反斜杠才能被解析为其他语言中的转义作用。也可以简单的理解在 Java 的正则表达式中，两个 \\ 代表其他语言中的一个 \，这也就是为什么表示一位数字的正则表达式是 \\d，而表示一个普通的反斜杠是 \\\\。
 */

