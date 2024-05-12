package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class Q18 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8);
        DhcpServer dhcpServer = new DhcpServer();
        int operationCnt = cin.nextInt();
        for (int i = 0; i < operationCnt; i++) {
            String[] operation = cin.next().split("=");
            if ("REQUEST".equals(operation[0])) {
                System.out.println(dhcpServer.request(operation[1]));
            } else {
                dhcpServer.release(operation[1]);
            }
        }
        cin.close();
    }

    static class DhcpServer {
        DhcpServer() {
            // 在此补充你的代码
            RequestMacs = new HashMap<>();
            RecordMacs = new HashMap<>();
            ip = new boolean[256];
            idx = 0;
        }

        HashMap<String, Integer> RequestMacs;  // 记录当前已分配地址的mac和对应的ip
        HashMap<String, Integer> RecordMacs;  // 记录分配过ip的mac和对应Ip  (缓存)
        boolean[] ip;  // 可分配的Ip  （是否被分配了）
        int idx;

        String request(String mac) {

            if (RequestMacs.containsKey(mac)) {
                return "192.168.0." + RequestMacs.get(mac);
            }
            // mac地址已申请过并已释放
            if (RecordMacs.containsKey(mac) && !ip[RecordMacs.get(mac)]) {
                ip[RecordMacs.get(mac)] = true;
                RequestMacs.put(mac, RecordMacs.get(mac));
                return "192.168.0." + RequestMacs.get(mac);

            }
            // 升序分配从未分配过的Ip地址
            if (idx <= 255) {
                RequestMacs.put(mac, idx);
                RecordMacs.put(mac, idx);  // mac已经分配过ip，进行记录
                ip[idx] = true;
                idx++;
                return "192.168.0." + RequestMacs.get(mac);
            }
            for (int i = 0; i < 256; i++) {
                if (!ip[i]) {  //
                    RequestMacs.put(mac, i);
                    RecordMacs.put(mac, i);
                    ip[i] = true;
                    return "192.168.0." + i;
                }
            }
            return "NA";
        }

        void release(String mac) {
            if (RequestMacs.containsKey(mac)) {
                ip[RecordMacs.get(mac)] = false;
                RequestMacs.remove(mac);
            }
        }
    }
}