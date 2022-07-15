package 数据结构.数组;

/**
 * 删除有序数组的重复项
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/7/15 19:12
 */
public class LeetCode_26 {

    public int removeDuplicates(int[] nums) {
        // 慢指针: slow; 快指针: fast
        int slow = 0, fast = 0;
        for(int i = 0; i < nums.length; i++, fast++) {
            // 慢指针负责保存不重复的元素
            // 快指针负责跳过重复的元素
            if(nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
        }
        // 需要返回数组长度
        return slow + 1;
    }

    /**
     * 解法2
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        // 慢指针: slow; 快指针: fast
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            // 数据差，如果相同数据，差为 0
            if (nums[fast] - nums[slow] > 0) {
                nums[++slow] = nums[fast];
            }
            fast++;
        }
        // 需要返回数组长度
        return slow + 1;
    }

}
