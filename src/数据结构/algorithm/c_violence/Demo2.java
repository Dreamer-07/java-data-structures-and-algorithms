package algorithm.c_violence;

import java.util.ArrayList;
import java.util.List;

/**
 * 暴力递归 - 打印一个字符串的全部子序列
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/5/13 8:29
 */
public class Demo2 {

    public static void main(String[] args) {
        printAllSubsequence(0, "abc", new ArrayList<>());
    }

    private static void printAllSubsequence(int n, String str, List<Character> sub) {
        if (n == str.length()) {
            System.out.println(sub);
            return;
        }
        printAllSubsequence(n + 1, str, sub);
        sub.add(str.charAt(n));
        printAllSubsequence(n + 1, str, sub);
        sub.remove(Character.valueOf(str.charAt(n)));
    }

    private static void printAllSubsequence(int n, char[] str) {
        if (n == str.length) {
            System.out.println(str);
            return;
        }
        char temp = str[n];
        // 要这个字符
        printAllSubsequence(n + 1, str);
        // 不要这个字符，置空
        str[n] = 0;
        printAllSubsequence(n + 1, str);
        // 还原
        str[n] = temp;
    }

}
