package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Q12 {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String commands = cin.nextLine();
        cin.close();
        String result = execCommand(commands);
        System.out.println(result);
    }

    private static String execCommand(String commands) {

        int x = 0, y = 0;

        int op_x = 0, op_y = 1;

        for (int i = 0; i < commands.length(); i++) {

            switch (commands.charAt(i)) {
                case 'G':
                    x += op_x * 1;
                    y += op_y * 1;
                    break;
                case 'L':
                    if ((op_x == 0 && op_y == 1) || (op_x == 0 && op_y == -1)) {
                        int temp = op_x;
                        op_x = -op_y;
                        op_y = -temp;
                        break;
                    } else {
                        int temp = op_x;
                        op_x = op_y;
                        op_y = temp;
                    }
                    break;
                case 'R':
                    if ((op_x == 1 && op_y == 0) || (op_x == -1 && op_y == 0)) {
                        int temp = op_x;
                        op_x = -op_y;
                        op_y = -temp;
                    } else {
                        int temp = op_x;
                        op_x = op_y;
                        op_y = temp;
                    }
                    break;
            }
        }

            StringBuilder res = new StringBuilder();
            res.append("(");
            res.append(x);
            res.append(",");
            res.append(y);
            res.append(")");
            return res.toString();
        }
}