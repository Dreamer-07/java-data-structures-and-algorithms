package algorithm.c_violence;

/**
 * 暴力递归 - 汉诺塔
 *
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/5/13 8:20
 */
public class Demo1 {

    public static void main(String[] args) {
        move(3, "左", "右", "中");
        int x = 123;
        if (x < 0) {
            return;
        }

    }

    private static void move(int n, String from, String to, String other) {
        if (n == 0) {
            return;
        }
        // 将其他层先搬到另外一个分支上
        move(n - 1, from, other, to);
        // 移动当前层到 to 分支
        System.out.println("第" + n + "层从" + from + "到" + to + "移动");
        // 将其他层搬到 to 分支上
        move(n - 1, other, to, from);
    }

}
