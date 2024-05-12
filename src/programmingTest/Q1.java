package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Q1 {
    // 待实现函数，在此函数中填入答题代码
    private static String[] getAllFault(String[] arrayA, String[] arrayB) {

        HashSet<String> warnings = new HashSet<>();

        for (int i = 0; i < arrayA.length; i++) {
            if(!warnings.contains (arrayA[i])) {
                warnings.add(arrayA[i]);
            }
        }

        for (int i = 0; i < arrayB.length; i++) {
            if(!warnings.contains (arrayB[i])) {
                warnings.add(arrayB[i]);
            }
        }

        String[] strs = new String[warnings.size()];

        int n = 0;

        for (String s : warnings) {
            strs[n] = s;
            n++;
        }

        Arrays.sort(strs);  // 按照字典序排序
        return strs;
    }

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int arrayALen = Integer.parseInt(cin.nextLine().trim());
        String[] arrayA = new String[0];
        if (arrayALen > 0) {
            arrayA = cin.nextLine().trim().split(" ");
        } else {
            cin.nextLine();
        }


        int arrayBLen = Integer.parseInt(cin.nextLine().trim());
        String[] arrayB = new String[0];
        if (arrayBLen > 0) {
            arrayB = cin.nextLine().trim().split(" ");
        }

        cin.close();

        String[] result = getAllFault(arrayA, arrayB);
        System.out.print("[" + String.join(" ", result) + "]");
    }
}