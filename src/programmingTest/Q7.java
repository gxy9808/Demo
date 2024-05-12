package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Q7 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String input = cin.nextLine();
        cin.close();
        int result = getCountOfSubString(input);
        System.out.println(result);
    }

    private static int getCountOfSubString(String input) {

        HashSet<String> set =  new HashSet<>();
        int count = 0;

        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;  // 滑动窗口
        String str = new String();

        while (left < input.length()) {
            char c= input.charAt(right);
            right++;
            str = str + String.valueOf(c);
            if(!window.containsKey(c))
                window.put(c ,1);
            else
                window.put(c, window.get(c) +1);

            if(window.get(c) <= 1 ) {
                set.add(str);
            }
            if(right == input.length() || window.get(c) >1) {
                left++;
                right = left;
                count += set.size();
                set.clear();
                window.clear();
                str ="";
            }
        }
        return count;
    }
}