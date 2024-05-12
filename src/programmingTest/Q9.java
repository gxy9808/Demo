package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Stack;

public class Q9 {
    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String expression = cin.nextLine();
        cin.close();
        String result = calculate(expression);
        System.out.println(result);
    }

    // 待实现函数，在此函数中填入答题代码
    private static String calculate(String expression) {

        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int res = 0;
        int num = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if(Character.isDigit(c)) {
                num = num * 10 + (int)(c -'0');
            }
            // 遇到 加减乘除进行 压栈出栈操作
            if(!Character.isDigit(c) || i == expression.length()-1){   // 注意这里是if！！！
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() *num);
                        break;
                    case '/':
                        if(num == 0)
                            return "error";
                        else {
                            stack.push(stack.pop() /num);
                            break;
                        }
                }
                sign = c;
                num = 0;
            }
        }

        while (!stack.isEmpty()) {
            res += stack.peek();
            stack.pop();
        }
        return  String.valueOf(res);
    }
}
