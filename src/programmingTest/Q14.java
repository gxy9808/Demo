package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Q14 {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int questionsCount  = cin.nextInt();
        int peopleCount = cin.nextInt();
        int[][] correctRanges = new int[peopleCount][];
        for (int i = 0; i < peopleCount; i++) {
            correctRanges[i] = new int[2];
            correctRanges[i][0] = cin.nextInt();
            correctRanges[i][1] = cin.nextInt();
        }
        cin.close();

        System.out.println(getMinPeople(questionsCount , peopleCount, correctRanges));
    }

    private static int getMinPeople(int questionsCount , int peopleCount, int[][] correctRanges) {

        // 将数组按照答对题目的开始题号排序
        /*Arrays.sort(correctRanges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });*/
        Arrays.sort(correctRanges, (a, b)->{
            if(a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });

        for (int i = 0; i < correctRanges.length; i++) {
            System.out.println(correctRanges[i][0] + " " + correctRanges[i][1]);
        }
        // 当前拼接的答对结束题号， 下一个要拼接的答对结束题号
        int curEnd = 1,  nextEnd = 0;
        int n = correctRanges.length;
        int i = 0, res = 0;

        // 注意是离散覆盖！
        while (i < n && correctRanges[i][0] <= curEnd +1) {  // >curEnd 则拼接不上
            // 从第i个同学寻找下一个答对题号的拼接范围
            while (i < n && correctRanges[i][0] <= curEnd+1) {
                nextEnd = Math.max(nextEnd, correctRanges[i][1]);
                i++;
            }
            res ++;
            curEnd = nextEnd;
            if(nextEnd >= questionsCount)
                return  res;
        }
        return -1;
    }
}