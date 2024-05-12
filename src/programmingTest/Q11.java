package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Q11 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int cnt = cin.nextInt();
        float demand = cin.nextFloat();
        float[] inventory = new float[cnt];
        for (int i = 0; i < cnt; i++) {
            inventory[i] = cin.nextFloat();
        }
        float[] price = new float[cnt];
        for (int i = 0; i < cnt; i++) {
            price[i] = cin.nextFloat();
        }
        cin.close();
        String result = phoneCaseInventoryManage(demand, inventory, price);
        System.out.println(result);
    }

    // 待实现函数，在此函数中填入答题代码
    /*private static String phoneCaseInventoryManage(float demand, float[] inventory, float[] price) {

        float maxVal = 0.0f;
        HashSet<Float> set = new HashSet<>();
        for (int i = 0; i < inventory.length; i++) {
                if(inventory[i] < demand) {
                    for (int j = i+1; j < inventory.length ; j++) {
                        maxVal = Math.max(maxVal, inventory[i] *(price[i]/inventory[i]) + (demand - inventory[i])*(price[j]/inventory[j]));
                    }
                } else {
                    maxVal = Math.max(maxVal, demand*(price[i]/inventory[i]));

                }
        }

        return String.format("%.2f",maxVal);
    }*/

    private static String phoneCaseInventoryManage(float demand, float[] inventory, float[] price) {

        List<List<Float>> profitList = new ArrayList<>();
        float maxVal = 0.0f;
        for (int i = 0; i < inventory.length; i++) {
            float profit = price[i]/inventory[i];
            List<Float> pair = Arrays.asList(inventory[i], profit);
            profitList.add(pair);
        }

        Collections.sort(profitList, new Comparator<List<Float>>() {
            @Override
            public int compare(List<Float> o1, List<Float> o2) {
                // 按照利润由大到小排序
                return Float.compare(o2.get(1), o1.get(1));
            }
        });

        for (int i = 0; i < profitList.size(); i++) {

            if(profitList.get(i).get(0) <= demand) {
                maxVal += profitList.get(i).get(1) *profitList.get(i).get(0);
                demand -= profitList.get(i).get(0);
            } else {
                maxVal +=  profitList.get(i).get(1) * demand;
                break;
            }
        }
        return String.format("%.2f",maxVal);
    }
}