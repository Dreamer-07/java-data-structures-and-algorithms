package exercise;

/**
 * @author by Prover07
 * @classname Class01
 * @description TODO
 * @date 2022/3/12 9:26
 */
public class Algorithm {


    public static void main(String[] args) {
        //exam01(new int[]{1, 2, 1, 2, 3, 3, 3});
        exam02(new int[]{3, 5, 7, 7, 9, 9});
    }

    /**
     * 题目描述：
     * - 给你一个数组，里面的元素存在 只有一种数出现了奇数次，其他数都出现了偶数次 的规律
     * 要求：
     * - 时间复杂度: O(N)
     * - 空间复杂度: O(1)
     *
     * @param arr
     */
    public static void exam01(int[] arr) {
        // 定义一个变量
        int temp = 0;
        // 遍历数组
        for (int i = 0; i < arr.length; i++) {
            /*
             * 进行异或操作
             * 异或操作满足 a ^ a = 0, b ^ 0 = b，a ^ b ^ c = a ^ c ^ b 的特点
             * 循环结束时 temp = arr[0] ^ arr[1] ^ arr[2] ^ arr[3] ....
             *   由于第三个规则，所以我们可得 "只要异或运算的数是同一批数，不管选择什么样的顺序，最后结果都是一样的"
             *   举个栗子：temp = 1 ^ 2 ^ 1 ^ 2
             *           由于第三个规则 a ^ b ^ c = a ^ c ^ b
             *           可以转换成 temp = 1 ^ 1 ^ 2 ^ 2
             *   由于只有一个数出现了奇数次，那么其他数都会被异或为 0
             *           (1 ^ 1) ^ (2 ^ 2) ^ (3 ^ 3) ^ 3
             *           (0) ^ (0) ^ (0) ^ 3 => 3
             *   那么最后的 temp 就为那个出现了奇数次的数
             * */
            temp ^= arr[i];
        }
        // 最后 temp 就是这个数
        System.out.println(temp);
    }


    /**
     * 题目描述：
     * - 给你一个数组，里面的元素存在 只有两种数出现了奇数次，其他数都出现了偶数次 的规律
     * 要求：
     * - 时间复杂度：O(N)
     * - 空间复杂度：O(N)
     *
     * @param arr
     */
    public static void exam02(int[] arr) {
        // 首先对数组内的所有元素都使用异或操作
        int temp = 0;
        for (int num : arr) {
            temp ^= num;
        }
        /*
        最后 temp 等于两个出现了奇数次的数(a,b)的异或操作，也就是 temp = a ^ b
        据题意可得 a != b & temp != 0，所以 a 的二进制数与 b 的二进制数一定有 1 位不相同
        有因为 temp = a ^ b 所以，temp 的二进制数位上的 1 就是 a 与 b 不同的地方
            例如：a = 3(0011); b = 5(0101); temp = 3 ^ 5 = (0011) ^ (0101) = 0110
        */
        // 那么可以根据 temp 某一位上是否为为 1 为标准，将 arr 拆分成 a + other & b + other 的两个集合(other 为出现了偶数次的数字)
        /*
        如何得到 temp 上某一位为 1 的值？ => 通过 与(&) 运算
            公式：如何得到一个二进制数最右边的 1 所构成的数字？
                 rightTemp = num & (~num + 1)
                 其中 ~ 为取反操作符， 例如 ~(1001) = 0110
                 假设 num = 101100 即 ~num = 010011 再加上 1 为 010100
                 为了方便判断所以我们只要保存最右边的 1 即可
                 通过 & 操作(只有两个位数都为 1 才返回 1，否则返回 0)
                 101100(num) & 010100 = 000100(rightTemp)
        */
        int rightOne = temp & (~temp + 1);
        // 再遍历一次数组，通过 rightOne 进行分组
        int temp2 = 0;
        for (int num : arr) {
            /*
            * 这里的目的就是将其中一组中的所有的数都进行异或(^)操作，就可以得到 a/b
            * 又因为 temp = a ^ b 所以只要得到 temp2(a/b) 就将其与 temp 进行异或操作就能得到另外一个数 (b/a)
            *   => temp = a ^ b ^ temp2(a) => a ^ b ^ a => b
            * 为什么可以根据 num & rightOne == 0 为什么可以分组呢？
            *   因为 rightOne != 0 且它的二进制数上位为1的值就是 a 的二进制数与 b 的二进制数对比最右边不同的地方
            *   因为 other 的出现次数为偶数所以无所谓，最后 temp2 都只能得到 a / b
            *   例如：{3, 5, 7, 7, 9, 9} 可能会被分为 {3, 7, 7} & {5, 9, 9} (举个栗子，没有算过，欸嘿)
            * 这里 0 也可以写成 rightOne 效果一样
            * */
            if ((num & rightOne) == 0) {
                temp2 ^= num;
            }
        }
        System.out.println((temp ^ temp2) + " " + temp2);
    }
}
