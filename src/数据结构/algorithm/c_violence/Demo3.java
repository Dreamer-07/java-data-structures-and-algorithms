package algorithm.c_violence;

/**
 * 暴力递归 - 打印一个字符串的全部排列
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/5/13 8:48
 */
public class Demo3 {

    public static void main(String[] args) {
        printAllArrangement(0, "abc".toCharArray());
    }

    private static void printAllArrangement(int n, char[] str) {
        if (n == str.length) {
            System.out.println(str);
            return;
        }
        for (int i = n; i < str.length; i++) {
            swap(str, n, i);
            printAllArrangement(n + 1, str);
            swap(str, n, i);
        }

    }

    private static void swap(char[] arr, int i, int k) {
        char tmp = arr[i];
        arr[i] = arr[k];
        arr[k] = tmp;
    }


}
