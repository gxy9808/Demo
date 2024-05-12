package programmingTest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Q4 {
    // 待实现函数，在此函数中填入答题代码
    /*private static int calcExpression(String expression) {

        // 遍历单个字符， 无法考虑带符号的数 如 -3
        Stack<Integer> st = new Stack<>();
        int num = 0;  // 操作数
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ',') {
                st.push(num);
                num = 0;
                continue;
            }
            if(Character.isDigit(c))
                num = num*10 + c -'0';
            if (!Character.isDigit(c)) {
                switch (c) {
                    case '+':
                        int a = st.pop();
                        int b = st.pop();
                        st.push(a+b);
                        break;
                    case '-':
                        a = st.pop();
                        b = st.pop();
                        st.push(b -a);
                        break;
                    case '*':
                        a = st.pop();
                        b = st.pop();
                        st.push(a*b);
                        break;
                    case '/':
                        a = st.pop();
                        b = st.pop();
                        st.push(b/a);
                        break;
                }
                i++;  // 挑错运算符后面的','
            }
        }
        int n = st.peek();
        return n;
    }*/

    private static int calcExpression(String expression) {

        String[] strs = expression.split(",");
        Stack<Integer> st = new Stack<>();
        HashSet<String> signs = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        int num = 0;  // 操作数
        for (int i = 0; i < strs.length; i++) {
            String c = strs[i];
            // 遇到+-*/  取栈顶两元素计算
            if (signs.contains(c)) {
                switch (c) {
                    case "+":
                        int a = st.pop();
                        int b = st.pop();
                        st.push(a + b);
                        break;
                    case "-":
                        a = st.pop();
                        b = st.pop();
                        st.push(b - a);
                        break;
                    case "*":
                        a = st.pop();
                        b = st.pop();
                        st.push(a * b);
                        break;
                    case "/":
                        a = st.pop();
                        b = st.pop();
                        st.push(b / a);
                        break;
                }
            } else {
                st.push(Integer.parseInt(c));
            }
        }
        return st.peek();
    }


    // main入口由OJ平台调用
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String expression = cin.nextLine();
        cin.close();

        int result = calcExpression(expression);
        System.out.println(result);
    }
}