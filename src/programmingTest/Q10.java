package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Q10 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String inputStr = cin.nextLine();
        cin.close();
        String result = characterSort(inputStr);
        System.out.println(result);
    }

    private static String characterSort(String inputStr) {

        List<Character> nums  = new ArrayList<>();
        List<Character> lowers = new ArrayList<>();
        List<Character> uppers = new ArrayList<>();

        if(inputStr.length() == 0)
            return "";

        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);
            if(Character.isDigit(c)) {
                nums.add(c);
            } else if(Character.isLowerCase(c)) {
                lowers.add(c);
            } else if(Character.isUpperCase(c)) {
                uppers.add(c);
            }
        }
        Collections.sort(nums);
        Collections.sort(lowers);
        Collections.sort(uppers);

        lowers.addAll(uppers);  // 按照从小写字母到大写字母排序
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);
            if(Character.isDigit(c)) {
                res.append(nums.get(0));
                nums.remove(0);
            } else if(Character.isLetter(c)) {
                res.append(lowers.get(0));
                lowers.remove(0);
            }
        }
        return res.toString();
    }
}
