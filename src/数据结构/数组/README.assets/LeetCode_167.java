package 数据结构.数组.README.assets;

/**
 * 两数之和 II - 输入有序数组
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/7/15 23:09
 */
public class LeetCode_167 {

    public int[] twoSum(int[] numbers, int target) {
        // 定义两个指针，一个从左边开始，一个从右边开始; tmp ==> 临时变量
        int left = 0, right = numbers.length - 1, tmp;
        while(left < right) {
            // 获取当前两个元素之和
            tmp = numbers[left] + numbers[right];
            if(tmp < target) {
                // 小于目标值，左边向右移动(变大)
                left++;
            } else if(tmp > target) {
                // 大于目标值, 右边向左移动(变小)
                right--;
            } else {
                // 相等，返回即可
                return new int[]{left + 1, right + 1};
            }
        }
        // 没找到，返回 -1
        return new int[]{-1, -1};
    }

}
