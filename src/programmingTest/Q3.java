package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Q3 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String binaryString = cin.nextLine();
        cin.close();
        int result = binaryToDecimal(binaryString);
        System.out.println(result);
    }

    // 待实现函数，在此函数中填入答题代码
    private static int binaryToDecimal(String binaryString) {

        // 负数
        if(binaryString.length() ==32 && binaryString.startsWith("1")) {
            long decimal = Long.parseLong(binaryString, 2);
            decimal = ~(decimal-1);   // 1. 减一  2. 取反  3. 取负
            return (int)-decimal;

        } else {
            return Integer.parseInt(binaryString, 2);
        }
    }
}