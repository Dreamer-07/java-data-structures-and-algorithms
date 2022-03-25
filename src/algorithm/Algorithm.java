package algorithm;

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
        int[] arr = {4, 7, 3, 8, 19, 1, 999, 30};

        // selectionSort(arr);

        // bubbleSort(arr);

        // insertionSort(arr);

        // mergeSort(arr, 0, arr.length - 1);

        // quickerSort(arr, 0, arr.length - 1);

        // heapSort(arr);

        radixSort(arr, maxBits(arr));

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
     *
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
     *
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
        for (int i = 0; i < tempArr.length; i++) {
            arr[l + i] = tempArr[i];
        }
    }

    /**
     * 快速排序(v3)：
     * 时间复杂度：O(N * logN)
     * 空间复杂度：
     * - 最好：O(logN)
     * - 最坏：O(N)
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void quickerSort(int[] arr, int l, int r) {
        if (l < r) {
            // 随机选择一个下标的元素作为 num
            int numIdx = l + (int) (Math.random() * (r - l + 1));
            // 将 numIdx 下标的元素放到数组最左边方便计算
            swap(arr, numIdx, r);
            // 由 partition 函数决定左右两个数组的边界，并保证中间数据和 numIdx 下标的数据相同
            int[] p = partition(arr, l, r);
            // p 中保存了两个元素，方便对应的中间数组(等于num)的两个边界
            quickerSort(arr, l, p[0] - 1);
            quickerSort(arr, p[1] + 1, r);
        }
        ;
    }

    /**
     * 快速排序的核心：将 arr[] 中 l~r 中的数据化成为 [小于 arr[r] 的数据, 等于 arr[r] 的数据，大于 arr[r] 的数据]
     *
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
            } else if (arr[i] > arr[r]) {
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
     * 堆排序：
     * 时间复杂度：O(N * logN)
     * 注意：在堆排序中的 1. 无论使用 1.2 / 1.1 都不影响堆排序的时间复杂度，因为最后都会进行一个 O(N * logN) 的运算
     * 但如果只要求将一个数组变成一个大根堆，推荐使用 1.2 heapIfy
     * 空间复杂度：O(1)
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        int heapSize = 0;
        // 1. 将数组变成一个大根堆
        // 1.1 方法一：通过 heapInsert; 时间复杂度：O(N * logN)
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
//         1.2 方法二：通过 heapIfy; 时间复杂度：O(N)
        for (int j = arr.length; j >= 0; j--) {
            heapIfy(arr, j, arr.length);
        }
        heapSize = arr.length;

        // 2. 将数组中 heapSize 的最后一个元素和第一个元素交换
        // 时间复杂度：O(N)
        while (heapSize > 0) {
            // O(1)
            swap(arr, 0, heapSize - 1);
            // O(logN)
            heapIfy(arr, 0, --heapSize);
        }
    }

    /**
     * heapInsert 调整数组中的元素，使其符合大根堆的规则
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * heapIfy 从 index 开始重新调整大根堆的结构
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    private static void heapIfy(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        // 保证操作的元素是在大根堆中的
        while (left < heapSize) {
            // 求出两个子元素的之间的最大值
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            // 与当前元素进行一个对比
            if (arr[index] >= arr[largest]) {
                // 如果当前元素符合大根堆的结构就不需要调整
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    /**
     * 基数排序
     *
     * @param arr
     */
    public static void radixSort(int[] arr, int maxBits) {
        // 表示桶的个数
        final int radix = 10;

        // 进行 maxBits 次出入桶操作
        for (int i = 1; i <= maxBits; i++) {
            int[] count = new int[radix];
            // 标记不同数据在同一"位"上的个数并记录起来
            for (int num : arr) {
                // 求出当前 arr[j] 从左到右的第 i 位上的数据
                int digit = getDigit(num, i);
                // 对应的标记的数量++
                count[digit]++;
            }
            /*
             * 从左到右左前缀相加
             * */
            for (int k = 1; k < count.length; k++) {
                count[k] += count[k - 1];
            }
            /*
             * 从左到右到依次将桶里的数据"倒出来"
             * */
            int[] temp = new int[arr.length];
            for (int a = arr.length - 1; a >= 0; a--) {
                // 求出当前 arr[j] 从左到右的第 i 位上的数据
                int digit = getDigit(arr[a], i);
                // 将桶里的数据"倒出来"保存到 temp 数组中
                temp[count[digit] - 1] = arr[a];
                count[digit]--;
            }
            // 将出桶数据保存到 arr 中
            System.arraycopy(temp, 0, arr, 0, temp.length);
        }
    }

    /**
     * 求出数组中元素的最大“位”数
     *
     * @param arr
     * @return
     */
    private static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            // 每除于10就表示有1位；res++
            max /= 10;
        }
        return res;
    }

    /**
     * 获取 x 从左到右的第 d 位数
     *
     * @param x
     * @param d
     * @return
     */
    private static int getDigit(int x, int d) {
        return ((x / (int) Math.pow(10, d - 1))) % 10;
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
