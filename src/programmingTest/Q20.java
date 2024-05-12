package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Q20 {

    // 通过率 88%
    /*private static int batchCalculation(int nCount, int mCount, int[] nums) {

        if(mCount >= nCount)
            return 10000;
        int maxlen = 0;

        List<Integer> datas = new ArrayList<>();

        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            datas.add(nums[i] - pre - 1);
            pre = nums[i];
        }
        if (pre < 1000)
            datas.add(10000 - pre);

        int len = 0;
        for (int i = 0; i < datas.size(); i++) {
            len = datas.get(i);
            int j = i+1;
            int m = 0;
            while (m < mCount && j < datas.size()) {
                len = len + datas.get(j) + 1;
                m++;
                j++;
            }
            maxlen = Math.max(maxlen, len);
        }
        return maxlen;
    }*/

    private static int batchCalculation(int nCount, int mCount, int[] nums) {

        if(mCount >= nCount)
            return 10000;
        int maxlen = 0;

        List<Integer> datas = new ArrayList<>();

        datas.add(1);
        for (int i = 0; i < nums.length; i++) {
            datas.add(nums[i]);
        }
        datas.add(10000);

        // 从第i个纠错点开始纠错
        for (int i = 1; i <= nCount - mCount + 1; i++) {
            int end = i + mCount -1;  // 连续纠错的最后一个纠错点
            maxlen = Math.max(maxlen, datas.get(end+1) - datas.get(i-1));
        }
            return maxlen;
        }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int nCount = cin.nextInt();
        int mCount = cin.nextInt();
        int[] nums = new int[nCount];
        for (int i = 0; i < nCount; i++) {
            nums[i] = cin.nextInt();
        }
        cin.close();
        int result = batchCalculation(nCount, mCount, nums);
        System.out.println(result);
    }
}