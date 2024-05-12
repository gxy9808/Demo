package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Q15 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int num = cin.nextInt();
        int[] steps = new int[num];
        for (int i = 0; i < num; i++) {
            steps[i] = cin.nextInt();
        }
        cin.close();

        System.out.println(getMinStep(steps));
    }

    static int getMinStep(int[] steps) {

        int n = steps.length;
        int count = 0;
        int tabTimes = 0;  // 上一行需要的缩进数

        for (int i = 0; i < n; i++) {

            int tabs = steps[i] - tabTimes;   // 每行需要的缩进数
            if (tabs > 0)
                count += tabs;
            tabTimes = steps[i];

        }

        return count;
    }
}