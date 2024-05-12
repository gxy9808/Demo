package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Q16 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int flaw = Integer.parseInt(cin.nextLine());
        String input = cin.nextLine();
        cin.close();

        System.out.println(getLongestFlawedVowelSubstrLen(flaw, input));
    }

    // 待实现函数，在此函数中填入答题代码
    /*private static int getLongestFlawedVowelSubstrLen(int flaw, String input) {

        int maxlen = 0;
        String YuanYin = "aeiouAEIOU";
        // 从i开始的字串
        for (int i = 0; i < input.length(); i++) {
            // 起始为元音字母
            if (YuanYin.contains(String.valueOf(input.charAt(i)))) {
                for (int j = i + flaw; j < input.length(); j++) {
                    if (YuanYin.contains(String.valueOf(input.charAt(j)))) {
                        String substr = input.substring(i, j + 1);
                        if (flawCount(substr) == flaw)
                            maxlen = Math.max(maxlen, substr.length());
                    }
                }
            }
        }
        return maxlen;
    }

    private static int flawCount(String s) {
        String YuanYin = "aeiouAEIOU";
        int count = 0;
        // 计算中间字母的瑕疵度
        for (int i = 1; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if (!YuanYin.contains(String.valueOf(c)))
                count++;
        }
        return count;
    }*/

    /*private static int getLongestFlawedVowelSubstrLen(int flaw, String input) {

        HashSet<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));

        int left = 0, right = 0;  // 滑动窗口

        int count = 0; // 记录瑕疵度
        int maxlen = 0;

        while (right < input.length()) {

            // 移动右指针 找到元音
            if (right < input.length() &&!set.contains(input.charAt(right))) {
                count++;
                right++;
                continue;
            }

            // 移动左指针 找到元音
            while(left <= right && !set.contains(input.charAt(left))) {
                left++;
                count--;
            }

            if(count == flaw) {
                maxlen = Math.max(maxlen, right - left+1);
                right++;
            } else if(count < flaw) {   // 扩张右指针
                right++;
            } else {   // 收缩左指针

                while (left <= right && set.contains(input.charAt(left)))
                {
                    left++;
                }  // 找到非元音
                left++;
                count--; // 瑕疵度减少
            }
        }
        return maxlen;
    }*/

    private static int getLongestFlawedVowelSubstrLen(int flaw, String input) {

        HashSet<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));

        int left = 0, right = 0;  // 滑动窗口

        int count = 0; // 记录瑕疵度
        int maxlen = 0;

        while (right < input.length()) {
            // 向右扩大窗口  ，记录瑕疵度
            if(!set.contains(input.charAt(right))) {
                count++;
            }
            // 左边收缩窗口
            while (count > flaw) {
                if(!set.contains(input.charAt(left))) {
                    count--;
                }
                left ++;
            }
            // 满足条件
            if(left <= right && set.contains(input.charAt(left)) && set.contains(input.charAt(right)) && flaw == count)
                maxlen = Math.max(maxlen, right - left + 1);
            right++;
        }
        return maxlen;
    }

}
