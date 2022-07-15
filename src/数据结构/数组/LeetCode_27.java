package 数据结构.数组;

/**
 * 移除元素
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/7/15 23:08
 */
public class LeetCode_27 {

    public int removeElement(int[] nums, int val) {
        if(nums.length == 0) {
            return 0;
        }
        // 快慢指针
        int slow = 0, fast = 0;
        while(fast < nums.length) {
            // 慢指针用来记录不是 val 元素的位置
            // 快指针用来判断当前元素是否为 val 元素
            if(nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        // 不需要 + 1, 因为是后++, 而不是前++
        return slow;
    }

}
