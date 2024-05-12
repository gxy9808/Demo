package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;


public class Q8 {


    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int nValue = cin.nextInt();
        int mValue = cin.nextInt();
        cin.nextLine();
        String[] strings = new String[mValue];
        for (int i = 0; i < mValue; i++) {
            strings[i] = cin.nextLine();
        }
        cin.close();

        char[] results = getNTimesCharacter(nValue, strings);

        System.out.print("[");
        for (int i = 0; i < results.length; i++) {
            if (i == 0) {
                System.out.print(results[i]);
            } else {
                System.out.print(" " + results[i]);
            }
        }
        System.out.print("]");
    }


    private static char[] getNTimesCharacter(int nValue, String[] strings) {

        List<HashSet<Character>> lists = new ArrayList<>();   // 列表记录各个字符串中满足条件的字符set集合

        for (String str : strings) {

            HashMap<Character, Integer> mp = new HashMap<>();  // 记录每个字符串中各字符出现的次数
            HashSet<Character> set = new HashSet<>();   // 记录每个字符串中满足出现次数条件的字符
            for (int i = 0; i < str.length(); i++) {
                if(mp.containsKey(str.charAt(i))) {
                    mp.put(str.charAt(i), mp.get(str.charAt(i))+1);
                } else {
                    mp.put(str.charAt(i), 1);
                }
                if(mp.get(str.charAt(i)) >= nValue)
                    set.add(str.charAt(i));   // 将满足条件的字符记入 set中
            }
            lists.add(set);
        }

        HashSet<Character> set1 = lists.get(0); // 用第一个字符串的字符set来初始化mpCount
        HashMap<Character, Integer> mpCount = new HashMap<>();  // 记录各字符串中满足条件的公共字符

        for (Character c : set1) {
            mpCount.put(c, 1);
        }

        for (int i = 1; i < lists.size(); i++) {  // 遍历其余字符串的字符set，看其字符中是否有跟set1相同的公共字符
            HashSet<Character> set2 = lists.get(i);
            for (Character c : set2) {
                if(mpCount.containsKey(c)) {
                    mpCount.put(c, mpCount.get(c) +1);  // 字符c在字符串数组中出现的次数加1
                }
            }
        }
        List<Character> res = new ArrayList<>();  // 符合条件的字符
        for (Map.Entry entry : mpCount.entrySet()) {
            if(entry.getValue().equals(strings.length)) {
                res.add((Character)entry.getKey());
            }
        }

        char[] ans = new char[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        Arrays.sort(ans);
        return ans;
    }
}


