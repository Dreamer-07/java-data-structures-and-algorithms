package algorithm;

import java.sql.Array;
import java.util.Arrays;

/**
 * 算法类
 *
 * @author by Prover07
 * @classname Algorithm
 * @description TODO
 * @date 2022/3/11 22:21
 */
public class Algorithm {

    public static void main(String[] args) {
        int[] arr = {4, 7, 3, 8, 19, 1, 999, -1};
        //selectionSort(arr);
        //bubbleSort(arr);
        //insertionSort(arr);
        //mergeSort(arr, 0, arr.length - 1);
        quickerSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
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
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(1)
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        // 因为先确定最右边的元素，所以从最右边开启遍历
        for (int i = arr.length - 1; i > 0; i--) {
            // 从第一个元素开始，遍历到已经确定的元素前即可(最大的先确定，依次放到最右)
            for (int j = 0; j < i; j++) {
                // 比较两个元素，如果右边大，就让它和左边换
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 插入排序：
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(1)
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // arr[j] > arr[j + 1]: 如果前一个元素大于当前元素才进行交换，
            // 但又因为前面的元素已经是排好序的了，所以如果当前元素大于前一个元素时就没必要遍历了，因为 arr[j] 一定大于 arr[j - n]
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    /**
     * 归并排序：
     * 时间复杂度：O(N * logN)
     * 空间复杂度：O(N)
     * @param arr
     */
    public static void mergeSort(int[] arr, int l, int r) {
        // 如果 l == r 就直接返回
        if (l == r) {
            return;
        }
        // 获取中位数
        int mid = l + ((r - l) >> 1);
        // 先使左右两个子序列有序
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        // 使子序列段间
        merge(arr, l, mid, r);
    }

    /**
     * 归并排序的核心：将两个有序的子序列的合并成一个有序的子序列段
     * @param arr
     * @param l
     * @param m
     * @param r
     */
    private static void merge(int[] arr, int l, int m, int r) {
        // 初始化辅助空间
        int[] tempArr = new int[r - l + 1];
        /*
         * 定义两个下标，依次从左往右比较两个数组中的元素，哪个小就放到 tempArr 中，然后对应的索引就 +1
         * */
        int tempIdx = 0;
        int lIdx = l;
        int rIdx = m + 1;
        while (lIdx <= m && rIdx <= r) {
            tempArr[tempIdx++] = arr[lIdx] < arr[rIdx] ? arr[lIdx++] : arr[rIdx++];
        }
        while (lIdx <= m) {
            tempArr[tempIdx++] = arr[lIdx++];
        }
        while (rIdx <= r) {
            tempArr[tempIdx++] = arr[rIdx++];
        }
        // 最后将 tempArr 的元素从 l 开始到 r 复制给 arr 即可
        for (int i = 0; i < tempArr.length; i++ ) {
            arr[l + i] = tempArr[i];
        }
    }

    /**
     * 快速排序(v3)：
     * 时间复杂度：O(N * logN)
     * 空间复杂度：
     *  - 最好：O(logN)
     *  - 最坏：O(N)
     * @param arr
     * @param l
     * @param r
     */
    public static void quickerSort(int[] arr, int l, int r) {
        if (l < r) {
            // 随机选择一个下标的元素作为 num
            int numIdx = l +  (int) (Math.random() * (r - l + 1));
            // 将 numIdx 下标的元素放到数组最左边方便计算
            swap(arr, numIdx, r);
            // 由 partition 函数决定左右两个数组的边界，并保证中间数据和 numIdx 下标的数据相同
            int[] p = partition(arr, l , r);
            // p 中保存了两个元素，方便对应的中间数组(等于num)的两个边界
            quickerSort(arr, l, p[0] - 1);
            quickerSort(arr, p[1] + 1, r);
        };
    }

    /**
     * 快速排序的核心：将 arr[] 中 l~r 中的数据化成为 [小于 arr[r] 的数据, 等于 arr[r] 的数据，大于 arr[r] 的数据]
     * @param arr
     * @param l
     * @param r
     * @return 返回中间区域的边界
     */
    private static int[] partition(int[] arr, int l, int r) {
        // less 表示左区域(比 num 小)边界的下一个数，也就是中间区域的最左边
        int less = l;
        // more 表示右区域(比 num 大)边界的前一个数，也就是中间区域的最右边
        int more = r - 1;
        // i 指向需要判断的数据的下标
        int i = l;
        // 当判断的数据已经到了右边区域内部时就不用判断了
        while (i <= more) {
            if (arr[i] < arr[r]) {
                // 如果当前数据比 num 小，就放到左区域，并把它的 边界++ => 也就是 less++
                swap(arr, i++, less++);
            } else if(arr[i] > arr[r]) {
                // 如果当先数据比 num 大，就放到右区域，并把它的 边界-- => 也就是 more--
                // 注意，这里 i 不要 ++，因为换过来的数是右区域(比 num 大)边界的前一个数，并不是右区域内部的数，即还未判断的数
                swap(arr, more--, i);
            } else {
                // 如果相同就代表是中间区域的，判断下一个数据即可
                i++;
            }
        }
        // 最后 i > more，i 处于右区域的左边界，所以让它和 num 下标 r 进行一个交换
        /*        less more
        * [1,  2,  3 ,  3,  7 , 5 , 3]
        *                   i       r
        * 交换之后
        *        less  more
        * [1,  2,  3 ,  3,  3 , 5 , 7]
        *                   i       r
        * */
        swap(arr, i, r);
        // 返回边界，这里的 i 也可以换成 more + 1
        return new int[]{less, i};
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
