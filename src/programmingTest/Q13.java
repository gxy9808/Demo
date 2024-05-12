package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;


public class Q13 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int row = cin.nextInt();
        cin.nextLine();
        String[] orderTime = new String[row];
        for (int i = 0; i < row; i++) {
            orderTime[i] = cin.nextLine();
        }
        cin.close();
        int result = freeOrder(orderTime);
        System.out.print(result);
    }

    // 待实现函数，在此函数中填入答题代码
    private static int freeOrder(String[] orderTime) {

        Arrays.sort(orderTime);   // 按照时间排序

        HashMap<String, String> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < orderTime.length; i++) {

            String[] strs = new String[2];
            strs[0] = orderTime[i].substring(0,19);
            strs[1] = orderTime[i].substring(20);
            if(map.isEmpty() || !map.containsKey(strs[0])) {
                map.put(strs[0], strs[1]);
                count++;
            }
            else if(map.containsKey(strs[0]) && Integer.parseInt(strs[1]) <= Integer.parseInt(map.get(strs[0]))) {
                map.put(strs[0], strs[1]);
                count++;
            }

        }
        return count;
    }
}