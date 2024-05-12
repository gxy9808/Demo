package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;



public class Q17 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int resourcesNum = cin.nextInt();
        int taskNums = cin.nextInt();
        int colNums = 2;
        int[][] taskAttributes = new int[taskNums][colNums];
        for (int row = 0; row < taskNums; row++) {
            for (int col = 0; col < colNums; col++) {
                taskAttributes[row][col] = cin.nextInt();
            }
        }
        cin.close();
        int result = taskScheduler(resourcesNum, taskAttributes);
        System.out.println(result);
    }

    private static int taskScheduler(int resourcesNum, int[][] taskAttributes) {

        // 排序  按照任务优先级由高到低排序
        /*Arrays.sort(taskAttributes, (a, b)->{
            if(a[1] == b[1])
                return  b[0] - a[0];
            return a[1] - b[1];
        });*/

        int mod = (int) (1e9 + 7 );
        // 优先级队列   按照任务优先级由高到低排序， 优先级相同则按照执行时间排序
        PriorityQueue<int[]> tasks = new PriorityQueue<>((a, b)->{
            if(a[1] == b[1])
                return  b[0] - a[0];
            return a[1] - b[1];
        });

        // 优先级队列（默认为大根堆）  记录各VM执行任务的时间
        PriorityQueue<Integer> p = new PriorityQueue<>();

        for (int i = 0; i < taskAttributes.length; i++) {
            tasks.add(taskAttributes[i]);
        }
        // 先用VM执行优先级最高的n个任务
        for (int i = 0; i < resourcesNum; i++) {
            if(!tasks.isEmpty()) {
                p.add(tasks.poll()[0]);
            }
        }
        // 执行剩余任务
        while (!tasks.isEmpty()) {
            // 先取出执行时间少的VM 继续执行任务
            int minTime = p.poll();
            p.add(tasks.poll()[0] + minTime);  // 累加执行时间后继续加入优先队列中
        }

        int res = 0;
        while (!p.isEmpty()) {
            res = Math.max(res, p.poll());  // 找到执行时间最长的
        }
        return res %mod;
    }
}
