# 数据结构与算法

## 基础知识

> 算法可视化：https://visualgo.net/zh

### 复杂度

> 时间复杂度

简单理解：本次操作中常数操作的时间

- 常数操作：和**数据量无关**，是一个**固定时间**的操作，例如 数组寻址/加减乘除等等

举个栗子：选择排序：实现数组从小到大排序，步骤-每次选择一个最小的按顺序从左到右放

1. 设有一个 N-1 长度的数组; 第一次排序需进行**查找 N 个元素，比较 N-1 次，进行 1 次交换**得常数操作

2. 第二次排序，由于第一个元素确定了(最小的)，就从下标为 1 的元素开始比较(以此类推); 由此可得第二个元素需要进行 **查找 N - 1 个元素，比较 N -2 次，进行 1 次交换** 的常数操作

3. 第三次排序，同理，第三个元素需要进行 **查找 N - 2 个元素，比较 N - 3 次，进行 1 次交换** 的常数操作

4. .... 最终会进行 **查找 (N) + (N - 1) + (N - 2) ..... (1)，比较 (N-1) + (N - 2) + (N - 3) ..... (1)，进行 N 次交换 ** 的常数操作

5. 将三个式子的结果接起来，通过[等差数列](https://www.bilibili.com/video/BV1Bs411573j?spm_id_from=333.337.search-card.all.click)可得最终的**常数操作数量**为：
   $$
   aN^2 + bN + C
   $$

6. 此时到了最重要的一步：**化简！！！**

   - 去掉低阶项，只要最高阶的项，上面的式子可以得到
     $$
     aN ^ 2
     $$

   - 忽略高阶项的系数
     $$
     N ^ 2
     $$

7. 最终 **选择排序** 的 **时间复杂度** 为
   $$
   O(N ^ 2)
   $$

> 空间复杂度

当前算法需要开辟的额外空间; 例如：当你进行**选择/冒泡排序**时，本身就是对数组进行操作的，并不需要开辟额外空间，此时空间复杂度为 **O(1)**; 而当我们的算法需要开辟一个额外的数组，并且需要在数组存放 N 个数据时，当前算法的空间复杂度为 **O(N)**

### 算法好坏的指标

1. 判断一个算法的好坏，优先看它的**时间复杂度**，一般越小越好，表示在遇到大数据量的情况下时，该算法所消耗的时间更少
2. 如果两个算法的时间复杂度相同，就需要观察它们的**常数项**，但注意 - 常数项表示的是常数操作，但不同类型的常数操作消耗的时间是不同的(例如一个进行了 10 次乘法运算，一个进行了 100 次位运算); 一般这种情况下就是**上代码**分析不同数据样本下的实际运行时间，这种测试又称为 **常数项时间**

### 选择排序

思想：每遍历一次就找到当前剩下数组元素中**最小的元素**，依次放到**最左边**，实现一个**从小到大的排序**

代码：

```java
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
* 交换操作
* /
private static void swap(int[] arr, int i, int k) {
    int temp = arr[i];
    arr[i] = arr[k];
    arr[k] = temp;
}
```

### 冒泡排序

思想：

- 从数组的第 1 个元素出发，与右边的元素对比(第 1 和第 2 个元素对比，第 2 个与第 3 个元素对比依次类推)，
- 只要**左边的元素比右边的元素大，就互换位置**，每遍历一次，就能**确定一个最大数**，放在**数组最右边**(因为大的数字一直在往右边移动)，最后实现一个**从小到大的排序的数组**

代码：

```java
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
```


