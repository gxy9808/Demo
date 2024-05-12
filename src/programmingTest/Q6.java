package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Q6 {

    private static int getMaxSendNumber(int cap, int[] billLen, int[] billPrior) {

        PriorityQueue<ArrayList<Integer>> p = new PriorityQueue<>((a , b)-> {
            if(a.get(1) == b.get(1))
                return a.get(0) - b.get(0);
            else
                return a.get(1) - b.get(1);
        });  // 优先级队列

        // PriorityQueue<ArrayList<Integer>> p = new PriorityQueue<>(new MyCmp());

        for (int i = 0; i < billLen.length; i++) {
            ArrayList<Integer> pair = new ArrayList<>(Arrays.asList(billLen[i], billPrior[i]));
            p.add(pair);
        }

        int count = 0;
        while (!p.isEmpty()) {

            ArrayList<Integer> order = p.peek();
            if(order.get(0) > cap ) {
                return count;
            } else {
                cap -= order.get(0);
                p.poll();
                count ++;
            }
        }
        return count;

    }

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int cap = cin.nextInt();
        int line = cin.nextInt();
        int[] billLen = new int[line];
        for (int i = 0; i < line; i++) {
            billLen[i] = cin.nextInt();
        }
        int[] billPrior = new int[line];
        for (int i = 0; i < line; i++) {
            billPrior[i] = cin.nextInt();
        }
        cin.close();
        int result = getMaxSendNumber(cap, billLen, billPrior);
        System.out.println(result);
    }
}

class MyCmp implements Comparator<ArrayList<Integer>>{

    @Override
    public int compare(ArrayList<Integer> l1, ArrayList<Integer> l2) {

        if(l1.get(1) == l2.get(1))
            return l1.get(0) - l2.get(0);
        else
            return l1.get(1) - l2.get(1);
    }
}