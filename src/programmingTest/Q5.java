package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Q5 {

    //  通过if else 对登记的呼叫号码进行循环判断转移号码。 通过率84%。 未通过用例的原因: 若登记的呼叫号码中先后有type=0和type=其他满足用户状态的转移号码，
    //  后者会将优先级最高的type=0的转移号码覆盖。优先级最高的应直接作为转移号码。
    /*private static String calling(String status, List<RegCallOperate> regCallForwardNums) {

        String trans = new String();

        for (RegCallOperate call : regCallForwardNums) {
            if (call.type == 0) {
                trans = call.number;
            } else if (call.type == 1 && status.equals("busy")) {
                trans = call.number;
            } else if (call.type == 2 && status.equals("no-response")) {
                trans = call.number;
            } else if (call.type == 3 && status.equals("unreachable")) {
                trans = call.number;
            } else if (call.type == 4 && !status.equals("idle")) {
                trans = call.number;
            }
        }

        if (!trans.isEmpty())  // 发生了转移
            return trans;
        if (status.equals("idle"))
            return "sucess";

        return "failure";
    }*/

    private static String calling(String status, List<RegCallOperate> regCallForwardNums) {
        // hahmap存储转移类型和转移号码
        Map<Integer, String> map = new HashMap<>();

        for (RegCallOperate call : regCallForwardNums) {
            map.put(call.type, call.number);
        }
        String trans = new String();
        if(map.containsKey(0))
            trans = map.get(0);
        if(map.containsKey(1) && status.equals("busy"))  //  若有多个type=1， 后面的转移号码会将前面的覆盖
            trans = map.get(1);
        if(map.containsKey(2) && status.equals("no-response"))
            trans = map.get(2);
        if(map.containsKey(3) && status.equals("unreachable"))
            trans = map.get(3);
        if(map.containsKey(4) && !status.equals("idle"))
            trans = map.get(4);

        if(!trans.isEmpty())
            return trans;
        if(status.equals("idle"))
            return "success";
        return "failure";
    }

    static class RegCallOperate {
        int type = -1;
        String number = null;

        RegCallOperate(int type, String number) {
            this.type = type;
            this.number = number;
        }
    }


    // main入口由OJ平台调用
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String[] first = cin.nextLine().split(" ");
        int row = Integer.parseInt(first[0]);
        String status = first[1];
        List<RegCallOperate> regCallForwardNums = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            String[] operate = cin.nextLine().split(" ");
            RegCallOperate regCallOperate = new RegCallOperate(Integer.parseInt(operate[0]), operate[1]);
            regCallForwardNums.add(regCallOperate);
        }
        cin.close();

        String result = calling(status, regCallForwardNums);
        System.out.println(result);
    }
}