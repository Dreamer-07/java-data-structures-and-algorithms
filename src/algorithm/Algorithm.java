package algorithm;

import java.util.Arrays;

/**
 * 算法类
 * @author by Prover07
 * @classname Algorithm
 * @description TODO
 * @date 2022/3/11 22:21
 */
public class Algorithm {

    public static void main(String[] args) {
        int[] arr = {4, 7, 3, 8, 19, 1};
        //selectionSort(arr);
        bubbleSort(arr);
    }

    /**
     * 选择排序
     * 时间复杂度：使用了两个 for 循环，遍历 n ^ 2 次元素，也就是 O(n ^ 2)
     * 空间复杂度：没有消耗额外的空间(变量 i, minIndex, k 并不影响可以忽略，并没有开辟额外空间分配新的数组)，所以是 O(1)
     */
    public static void selectionSort(int[] arr) {
        // 开启遍历，这个外层每遍历一次就会确定一个数
        for (int i = 0; i < arr.length - 1; i++) {
            // 创建一个变量，用来保存最小元素的下标，方便交换; 同时假设当前遍历的元素就是最小的元素
            int minIndex = i;
            // 与后面的元素对比，因为是从左往右排序的所以前面的不用管
            for (int k = i + 1; k < arr.length; k++) {
                // 如果当前元素比正在 遍历的元素大(arr[k]) 就代表 arr[k] 小，那么记录 k 的下标
                minIndex = arr[minIndex] > arr[k] ? k : minIndex;
            }
            // 交换两个元素
            swap(arr, i, minIndex);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(1)
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        // 因为先确定最右边的元素，所以从最右边开启遍历
        for (int i = arr.length - 1; i > 0; i--) {
            // 从第一个元素开始，遍历到已经确定的元素前即可(最大的先确定，依次放到最右)
            for (int j = 0; j < i; j++) {
                // 比较两个元素，如果右边大，就让它和左边换
                if (arr[j] > arr[j + 1]){
                    swap(arr, j , j+1);
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 交换操作
     */
    private static void swap(int[] arr, int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }

}
