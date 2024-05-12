package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Q2 {
    // 待实现函数，在此函数中填入答题代码
    private static String getHexString(String num) {

        try {
            long number = Long.parseLong(num);        // 字符串转为数字
            if (number >= Math.pow(2, 32) || number < Math.pow(-2, 31))
                return "overflow";


            String res = new String();

            if (number >= 0)
                res = Long.toHexString(number);
            else
                res = Integer.toHexString((int) number);   // 转为16进制数/

            StringBuilder sb = new StringBuilder(res);
            for (int i = 0; i < 8 - res.length(); i++) {
                sb.insert(0, '0');
            }
            res = sb.toString();

            res = res.toUpperCase();  // 转成大写

            // 字符串分割 + 空格
            List<String> list = new ArrayList<>();
            for (int i = 0; i < res.length(); i += 2) {
                list.add(res.substring(i, i + 2));  // 字符串分割， 并转为大写
                list.add(" ");
            }
            list.remove(list.size() - 1);  // 删除最后一个空格

            List<String> list2 = new ArrayList<>(list);

            Collections.reverse(list);   // 大端转为小端
            StringBuilder str = new StringBuilder();
            for (String s : list2) {
                str.append(s);
            }
            str.append("\n");

            for (String s : list) {
                str.append(s);
            }

            return str.toString();
        } catch (NumberFormatException e) {
            return "overflow";
        }
    }

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String num = cin.nextLine();
        cin.close();
        String result = getHexString(num);
        System.out.println(result);
    }
}