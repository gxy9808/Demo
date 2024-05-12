package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.*;


public class Q19 {
    // 待实现函数，在此函数中填入答题代码
    private static int[] getActiveUserNum(String[] logs) {

        int[] res = new int[32]; // 月、日活数

        HashSet<String> successIp = new HashSet<>();

        HashMap<Integer, HashSet<String>> map = new HashMap<>();  // 日期 和 对应访问成功的IP
        for (String s: logs) {

            String[] strs = s.split("\\|");  // 分割为四部分
            String date = strs[0];
            int day = Integer.parseInt(date.substring(8));
            String[] IpParts = strs[1].split("\\.");
            String newIp = new String();
            for (int i = 0; i < IpParts.length; i++) {
                newIp +=  Integer.parseInt(IpParts[i]);
                if(i != IpParts.length-1)
                    newIp += ".";
            }
            String web = strs[2];
            String state = strs[3];


            if(web.equals("/login.do") && state.equals("success")) {
                if(!map.containsKey(day)){
                    HashSet<String> set = new HashSet<>();
                    map.put(day, set);
                }
                map.get(day).add(newIp);
                successIp.add(newIp);

            }
        }

        for (int i = 1; i <= 31 ; i++) {
            if(map.containsKey(i))
                res[i] = map.get(i).size();
        }

        res[0] = successIp.size();

        return res;
    }
    /*private static int[] getActiveUserNum(String[] logs) {

        int[] res = new int[32]; // 月、日活数

        HashSet<String> successIp = new HashSet<>();

        HashMap<String, List<String[]>> map = new HashMap<>();
        for (String s: logs) {

            String date = s.substring(0,10);
            String[] ipToState = s.substring(11).split("\\|");
            String[] IpParts = ipToState[0].split("\\.");
            String newIp = new String();
            for (int i = 0; i < IpParts.length; i++) {
                newIp +=  Integer.parseInt(IpParts[i]);
                if(i != IpParts.length-1)
                    newIp += ".";
            }
            ipToState[0] = newIp;
            if(map.containsKey(date))   // 日期和 IP状态信息的映射
                map.get(date).add(ipToState);
            else {
                List<String[]> l = new ArrayList<>();
                l.add(ipToState);
                map.put(date, l);
            }

        }

        for (Map.Entry<String, List<String[]>> e : map.entrySet()) {
            String date = e.getKey();
            int idx = Integer.parseInt(date.substring(date.length()-2)); // 获取某一天
            List<String[]> groups = e.getValue();
            int count = 0;
            HashSet<String> set = new HashSet<>();  // 记录当前天已成功访问的ip
            for (int i = 0; i < groups.size(); i++) {
                String ip = groups.get(i)[0];
                String web = groups.get(i)[1];
                String st = groups.get(i)[2];
                if(!set.contains(ip) && web.equals("/login.do") && st.equals("success")){
                    count++;
                    set.add(ip);
                    successIp.add(ip);
                }
            }
            res[idx] = count;
        }
            res[0] = successIp.size();

        return res;
    }*/

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());

        int num = Integer.parseInt(cin.nextLine());
        String[] logs = new String[num];
        for (int i = 0; i < num; i++) {
            logs[i] = cin.nextLine();
        }
        cin.close();

        int[] results = getActiveUserNum(logs);
        String[] strResult = Arrays.stream(results).mapToObj(String::valueOf).toArray(String[]::new);
        System.out.println(String.join(" ", strResult));
    }
}