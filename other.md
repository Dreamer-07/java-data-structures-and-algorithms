# 数据结构与算法

## 基础知识

> 算法可视化：https://visualgo.net/zh

### 复杂度

> 时间复杂度

简单理解：本次操作中常数操作的总时间

- 常数操作：和**数据量无关**，是一个**固定时间**的操作，例如 数组寻址/加减乘除等等

举个栗子：(选择排序)实现数组从小到大排序，步骤-每次选择一个最小的按顺序从左到右放

1. 设有一个 N-1 长度的数组; 第一次排序需进行**查找 N 个元素，比较 N-1 次，进行 1 次交换**得常数操作

2. 第二次排序，由于第一个元素确定了(最小的)，就从下标为 1 的元素开始比较(以此类推); 由此可得第二个元素需要进行 **查找 N - 1 个元素，比较 N -2 次，进行 1 次交换** 的常数操作

3. 第三次排序，同理，第三个元素需要进行 **查找 N - 2 个元素，比较 N - 3 次，进行 1 次交换** 的常数操作
   
   最终会进行 **查找 (N) + (N - 1) + (N - 2) ..... (1)，比较 (N-1) + (N - 2) + (N - 3) ..... (1)，进行 N 次交换 ** 的常数操作

4. 将三个式子的结果接起来，通过[等差数列](https://www.bilibili.com/video/BV1Bs411573j?spm_id_from=333.337.search-card.all.click)可得最终的**常数操作数量**为：
   
   $$
   aN^2 + bN + C
   $$

5. 此时到了最重要的一步：**化简！！！**
   
   - 去掉低阶项，只要最高阶的项，上面的式子可以得到
     
     $$
     aN ^ 2
     $$
   
   - 忽略高阶项的系数
     
     $$
     N ^ 2
     $$

6. 最终 **选择排序** 的 **时间复杂度** 为
   
   $$
   O(N ^ 2)
   $$

> 空间复杂度

当前算法需要开辟的额外空间; 例如：

- 当你进行**选择/冒泡排序**时，本身就是对数组进行操作的，并不需要开辟额外空间，此时空间复杂度为 **O(1)**; 

- 而当我们的算法需要开辟一个额外的数组，并且需要在数组存放 N 个数据时，当前算法的空间复杂度为 **O(N)**

### 算法好坏的指标

1. 判断一个算法的好坏，优先看它的**时间复杂度**，一般越小越好，表示在遇到大数据量的情况下时，该算法所消耗的时间更少
2. 如果两个算法的时间复杂度相同，就需要观察它们的**常数项**，但注意 - 常数项表示的是常数操作，但不同类型的常数操作消耗的时间是不同的(例如一个进行了 10 次乘法运算，一个进行了 100 次位运算); 一般这种情况下就是**上代码**分析不同数据样本下的实际运行时间，这种测试又称为**常数项时间**

### 选择排序

思想：每遍历一次就找到当前剩下数组元素中**最小的元素**，依次放到**最左边**，实现一个**从小到大的排序**

代码：

```java
/**
* 选择排序
* 时间复杂度：使用了两个 for 循环，遍历 n ^ 2 次元素(一次用来找一次用来比较)，也就是 O(n ^ 2)
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
*/
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

### 插入排序

思想：

- 从左到右依次遍历数组元素，每遍历一次，就要保证从 0 开始到当前位置是有序的

- 例如：
  
  - 遍历到第 1 个元素时需要和第 0 个元素进行判断，如果 arr[1] < arr[0] 就交换顺序，由于到最左边了，所以遍历下一个元素; 
  
  - 第 2 元素需要和前面一个元素也就是第 1 个元素进行判断，
    
    - 如果 arr[2] > arr[1] 就代表是有序的就可以遍历下一个元素了
    - 但如果 arr[2] < arr[1] 就交换顺序
    - 此时由于没有达到最左边，所以还需要判断 arr[2] 是否大于 arr[0]，如果小于就交换，大于就停止，遍历下一个元素

> 该算法很特殊：元素的会影响遍历次数，例如 [1, 2, 3, 4] 这个数组使用遍历一次数组元素即可O(N),但选择和冒泡不同，它们不会受元素的影响依然是 O(N ^ 2)
> 
> 最好情况下时间复杂度为 O(N) 
> 
> 最坏情况下时间复杂度为 O(N ^ 2)
> 
> 但这里的算法**只考虑最坏情况**，所以该算法的时间复杂度为 o(N ^ 2) 

代码：

```java
public static void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        // arr[j] > arr[j + 1]: 如果前一个元素大于当前元素才进行交换，但又因为前面的元素已经是排好序的了，所以如果当前元素大于前一个元素时就没必要遍历了，因为 arr[j] 一定大于 arr[j - n]
        for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
            swap(arr, j, j + 1);
        }
    }
}
```

### 二分查找

思想：

- 在**数组有序**的情况下，可以将数组从中间划开分为两个数组，然后将要查找的元素与中间元素对比，如果比它小就往左边数组找，比它大就往右边找(相同返回即可)；在左/右边查找时也是先从中间划分开左右两个数组然后进行上述查找
- 对于**无序数组**的情况下，针对特定的**数据状况 / 问题标准**也可以用二分查找; 例如：求数组局部最小

代码：

### 计算递归行为的时间复杂度

> 当**子问题的问题规模相同**时，我们就可以使用`master`公式来估算该递归问题的时间复杂度。

首先需要判断当前递归算法是否符合 master 公式
$$
master = a * T(\frac{N}{b}) + O(N^d)
$$
其中 N 为该函数的总数据量，a 为调用了几次子递归，b 为子递归的规格，O(N ^ d) 为出去递归外的常数操作

![image-20220312144502593](README.assets/image-20220312144502593.png)

图中的算法符合 master 规范，即 **a 为 2，b 为 2，d 为 0** 可得：
$$
master = 2 * T(\frac{N}{2}) + O(1)\\
log_ba < d 时，时间复杂度为：O(N ^ d)\\
log_ba > d 时，时间复杂度为：O(N ^ {log_ba})\\
log_ba = d 时，时间复杂度为：O(N ^ d * log_2N)
$$

### 归并排序

思路：

- 先使每个子序列有序，再使子序列段间有序。 若将两个有序表合并成一个有序表，也可以称为二路归并
- 如何将两个有序表合并成一个有序表？
  1. 创建一个空数组 temp 暂时保存有序的数据
  2. 依次遍历两个有序集合，定义两个索引，依次遍历对比，只有元素小的数据才能先放到 temp 中然后对应的索引才可以 ++，直到将其中一个有序集合全部遍历
  3. 将剩下的那个有序集合中剩下的元素直接保存到 temp 中
  4. 将 temp 中的元素按一定顺序拷贝到原数组中

代码：

```java
/**
* 归并排序：
* 时间复杂度：O(N *  logN)
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
    // 最后将 tempArr 的元素拷贝给 arr 即可
    for (int i = 0; i < tempArr.length; i++ ) {
        arr[l + i] = tempArr[i];
    }
}
```

### 快速排序

思想：

- 快排分为三个版本，但原理都差不多
- 主要步骤：
  1. **先从数组中选择一个数 num**，如何将数组划分成左边是**小于等于 num 的数**，而右边是**大于 num 的数**
  2. 然后再将左边和右边的数组(不包括 num)放到递归中重复以上操作
- 版本区别
  - 1.0 版本就是主要步骤中所描述的，时间复杂度为 O(N ^ 2)
  - 2.0 版本是将 数组左边只保存小于 num 的数，中间放等于 num 的数，右边是大于 num 的数；递归时数组中间并不参与，时间复杂度为 O(N ^ 2)
  - 3.0 版本是在 2.0 的版本的基础上，改变了选择了 num 的方式；此前 1/2 版本中都是选择最左边的数作为 num，而 3 版本是**随机选择**的; 时间复杂度为 O(N * logN)

代码：

```java
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
```

### 堆

> 堆结构

- 从逻辑上可以理解为堆是一颗 **完全二叉树**(不懂得可以百度下，很简单理解di)

- 可以用一个数组连续的 heapSize 个元素表示堆(完全二叉树)
  
  ```
  在这连续的 heapSize 个元素中，每个元素都满足一下规律
  父元素   == i / 2 - 1
  左子元素 == i * 2 + 1
  右子元素 == i * 2 + 2
  ```

- 分类
  
  - 大根堆：在完全二叉树种，以 任意一个节点 作为 头节点 都是 **对应子树的最大值**
  - 小根堆：在完全二叉树种，以 任意一个节点 作为 头节点 都是 **对应子树的最小值**

> 堆结构相关算法的核心

- `heapInsert`：将数组变成一个大根堆
  
  让元素和它的父元素进行对比，如果比它大就交换到上一层去
  
  ```java
  /**
  * heapInsert 调整数组中的元素，使其符合大根堆的规则
  * @param arr
  * @param index
  */
  private static void heapInsert(int[] arr, int index) {
      while (arr[index] > arr[(index - 1) / 2]){
          swap(arr, index, (index - 1) / 2);
          index = (index - 1) / 2;
      }
  }
  ```

- `heapify`: 重新调整数组结构,保证其符合堆的特点
  
  从 index 开始调整子树让其符合大根堆的结构
  
  ```java
  /**
  * heapIfy 从 index 开始重新调整大根堆的结构
  * @param arr
  * @param index
  * @param heapSize
  */
  private static void heapIfy(int[] arr, int index, int heapSize) {
      int left = index * 2 + 1;
      // 保证操作的元素是在大根堆中的
      while (left < heapSize) {
          // 求出两个子元素的之间的最大值
          int largest  = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
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
  ```

> 堆排序

思想：

- 先将数组调整至大根堆的结构(heapInsert)
- 将数组的第一个元素和 heapSize 的最后一个元素交换，然后 `heapSize--`(表示数组中从0开始一共有几个元素是堆结构)，然后对第一个元素进行 `heapIfy` 操作，依次类推，直到 heapSize == 0

代码：

```java
/**
* 堆排序：
* 时间复杂度：O(N * logN)
*  注意：在堆排序中的 1. 无论使用 1.2 / 1.1 都不影响堆排序的时间复杂度，因为最后都会进行一个 O(N * logN) 的运算
*       但如果只要求将一个数组变成一个大根堆，推荐使用 1.2 heapIfy
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
```

> 注意

1. 优先级队列的底层就是堆结构，例如 Java 中的 **PriorityQueue\<Integer>**(小根堆)
2. 由于堆使用的数组，可能会存在**扩容**问题，但一般影响不大，扩展的时间复杂度为 **O(logN)**
3. 如果是使用系统提供的优先级队列，如果只需要进行**存储获取**(保存一个数，获取一个数)的操作可以使用，但如果**想可以随意的修改堆中的数据，建议手写堆结构**

### 计数排序和基数排序

> 与上述的所有排序(选择，冒泡，插入，归并，快速，堆)都不同，它们都是基于比较的排序(计数和基数不是)

计数排序：

- 时间复杂度：O(N)；空间复杂度：O(N)
- 特点：
  1. 输入的数据必须是有确定范围的整数
  2. 计数排序只能用在**数据范围不大的场景**中，如果数据范围 k 比要排序的数据 n 大很多，就不适合用计数排序了。而且，计数排序只能给非负整数排序，如果要排序的数据是其他类型的，要将其在不改变相对大小的情况下，转化为非负整数。
- 主要思想：使用一个额外数组，该数组负责记录对应下标的数据出现的次数(例如数据为 17 那么额外数组下标为 17 的元素就 + 1)，通过这个次数，我们就可以知道元素在有序数组中的位置

基数排序(又称为**桶排序**)：

- 时间复杂度：O(N); 空间复杂度：O(N + m)

- 特点：
  
  1. 需要分割出独立的"位"来比较，而且位之间可以进行比较。
  2. 每一位的数据范围不能太大，要可以用线性排序算法来排序，否则，基数排序的时间复杂度就无法做到 O(n)。
  3. 如果排序的元素位数不一样，位数不够的可以在后面补位。

- 主要思想：根据每个数的各个位数进行排序。先根据个位数排序，再根据十位数排序，以此类推，最后根据最高位。

- 代码：
  
  ```java
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
  ```

### 排序算法总结

|          | 时间复杂度       | 空间复杂度   | 稳定性 |
|:-------- |:----------- | ------- | --- |
| 选择排序     | O(N ^ 2)    | O(1)    | ×   |
| 冒泡排序     | O(N ^ 2)    | O(1)    | √   |
| 插入排序     | O(N ^ 2)    | O(1)    | √   |
| 快速排序(v3) | O(N ^ logN) | O(logN) | ×   |
| 归并排序     | O(N ^ logN) | O(N)    | √   |
| 堆排序      | O(N ^ logN) | O(1)    | ×   |

注意：

![image-20220326115438716](README.assets/image-20220326115438716.png)

1. 常数项时间最低的分别是 **插入排序** & **快速排序**

2. 对应算法的选择需要充分利用 O(N ^ logN) 和 O(N ^ 2) 排序各自的优势
   
   也可以使用综合排序，将两种算法合起来一起使用，例如在使用 快速排序 的过程中，对于一些样本数量(需要排序的数据数量)较小的情况可以使用 插入排序，因为样本数量比较小，需要算法瓶颈的可能性是很低的，而插入排序的并不需要使用额外空间复杂度且常数项时间极低
   
   ```java
   public static void quickerSort(int[] arr, int l, int r) {
       if (l < r) {
   
           if (r - l > 60) {
               // 可以在样本数量小于 60 时使用快速排序
               quickerSort(arr, l, r);
               return;
           }
   
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
   }
   ```
   
   在整个数组的排序调度上依然使用快速排序，但在递归一些样本量较小的数据时就可以使用快速排序，减小空间复杂度且影响不大

3. 对应的基础类型的数据可以不考虑稳定性，但对于其他类型就需要根据业务情况而定

### 哈希表

- 在使用层面上可以理解为一种集合结构

- 如果只有 key，没有伴随数据 value，可以使用 HashSet 结构

- 如果既有 key，又有伴随数据 value，可以使用 HashMap 结构
  
  注意：HashMap 和 HashSet 唯一的区别就在于是否有伴随数据，底层结构是一样的

- 哈希表的 crud 操作可以任务**时间复杂度都是 O(1)**，但是常数时间比较大

- 放入哈希表的数据，如果是基础类型，内部按值传递，内存占用就是这个东西的大小

- 放入哈希表的数据，如果不是基础类型，内部按引用传递，内存占用就是这个东西的内存地址

### 有序表

- 在使用层面上可以理解为一种集合结构

- 如果只有 key，没有伴随数据 value，可以使用 TreeSet 结构
  
  如果既有 key，又有伴随数据 value，可以使用 TreeMap 结构
  
  注意：TreeSet 和 TreeMap 的唯一区别在于是否有伴随数据，底层结构是一样的

- 有序表和哈希表的区别在于，有序表会把 key 按照顺序组织起来，而哈希表不组织

- 红黑树，AVL 树，size-balance-tree 和 跳表 都属于有序表数据结构，只是底层具体实现不同

- 放入有序表的数据，如果是基础类型，内部按值传递，内存占用就是这个东西的大小

- 放入有序表的数据，如果不是基础类型，就必须提供比较器，内部按引用传递，内存占用就是这个东西的内存地址

- 只要是有序表，都有一下固定的基本操作和固定的 **时间复杂度 O(logN)**
  
  ![image-20220326132335544](README.assets/image-20220326132335544.png)

### 二叉树

> 递归序：依次访问树的左右节点

```java
public static class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }
}

/**
* 递归序
*/
public static void recursion(TreeNode root) {
    if (root == null) {
        return;
    }
    // 1
    recursion(root.left);
    // 2
    recursion(root.right);
    // 3.
}
```

> 递归实现先序/中序/后序遍历：在递归序的基础上实现用不同顺序的访问树的节点

注意在 **递归序** 中留下注释的地方，在这些地方分别访问当前 `root` 节点，就是对应的 **先序/中序/后序遍历**

```java
/**
* 先序遍历
* @param root
*/
public static void preOrder(TreeNode root) {
    if (root == null) {
        return;
    }
    // 访问当前节点
    System.out.println(root.value);
    preOrder(root.left);
    preOrder(root.right);
}

/**
 * 中序遍历
 * @param root
 */
public static void midOrder(TreeNode root) {
    if (root == null) {
        return;
    }
    midOrder(root.left);
    // 访问当前节点
    System.out.println(root.value);
    midOrder(root.right);
}

/**
* 后序遍历
* @param root
*/
public static void postOrder(TreeNode root) {
    if (root == null) {
        return;
    }
    postOrder(root.left);
    postOrder(root.right);
    // 访问当前节点
    System.out.println(root.value);
}
```

> 非递归实现先序/中序/后序遍历: 借助额外数据结构实现

先序遍历

```java
/**
* 非递归实现先序遍历
* @param root
*/
public static void unRecurPreOrder(TreeNode root) {
    // 使用栈实现
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode treeNode = stack.pop();
        System.out.println(treeNode.value);
        if (treeNode.right != null) {
            stack.push(treeNode.right);
        }
        if (treeNode.left != null) {
            stack.push(treeNode.left);
        }
    }
}
```

中序遍历

```java
/**
* 非递归实现中序遍历
*
* @param root
*/
public static void unRecurMidOrder(TreeNode root) {
    // 使用栈实现
    Stack<TreeNode> stack = new Stack<>();
    TreeNode temp = root;
    while (!stack.isEmpty() || temp != null) {
        // 先往左边走，同时将当前节点压入栈中
        if (temp != null) {
            stack.push(temp);
            temp = temp.left;
        } else {
            // 左边莫得了, 获取上一个压入栈中的节点
            temp = stack.pop();
            // 访问当前节点
            System.out.println(temp.value);
            // 访问右边的节点
            temp = temp.right;
        }
    }
}
```

后序遍历

```java
/**
* 非递归实现后序遍历
* @param root
*/
public static void unRecurPostOrder(TreeNode root) {
    Stack<TreeNode> stack1 = new Stack<>();
    Stack<TreeNode> stack2 = new Stack<>();
    stack1.push(root);
    while (!stack1.isEmpty()) {
        TreeNode node = stack1.pop();
        stack2.push(node);
        if (node.left != null) {
            stack1.push(node.left);
        }
        if (node.right != null) {
            stack1.push(node.right);
        }
    }
    while (!stack2.isEmpty()){
        System.out.println(stack2.pop().value);
    }
}
```

> 宽度优先遍历

```java
public static void exam01(TreeNode root) {
    // 在 java 中 LinkedList 就是队列
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    nodeQueue.add(root);
    while (!nodeQueue.isEmpty()) {
        TreeNode treeNode = nodeQueue.poll();
        System.out.println(treeNode.value);
        if (treeNode.left != null) {
            nodeQueue.add(treeNode.left);
        }
        if (treeNode.right != null) {
            nodeQueue.add(treeNode.right);
        }
    }
}
```

> 求出二叉树最大宽度(哈希表/不用哈希表)

```java
/**
* 求出树的最大宽度(答案1): 求出树的最大宽度
*
* @param root
* @return
*/
public static int exam02Answer01(TreeNode root) {
    HashMap<TreeNode, Integer> levelMap = new HashMap<>();
    int curLevel = 1;
    int curCount = 0;
    int maxCount = Integer.MIN_VALUE;
    levelMap.put(root, 1);
    // 在 java 中 LinkedList 就是队列
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    nodeQueue.add(root);
    while (!nodeQueue.isEmpty()) {
        TreeNode treeNode = nodeQueue.poll();
        Integer level = levelMap.get(treeNode);
        if (level == curLevel) {
            curCount++;
        } else {
            maxCount = Math.max(maxCount, curCount);
            curCount = 1;
            curLevel++;
        }
        if (treeNode.left != null) {
            nodeQueue.add(treeNode.left);
            levelMap.put(treeNode.left, curLevel + 1);
        }
        if (treeNode.right != null) {
            nodeQueue.add(treeNode.right);
            levelMap.put(treeNode.right, curLevel + 1);
        }

    }
    return maxCount;
}

/**
* 求出树的最大宽度(答案2)：求出树的最大宽度
*
* @param root
* @return
*/
public static int exam02Answer02(TreeNode root) {
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    nodeQueue.add(root);
    int curLevel = 1;
    int curCount = 0;
    int maxCount = Integer.MIN_VALUE;
    // 当前层(curLevel)的最后一个节点
    TreeNode curLevelEndTreeNode = root;
    // 下一层的最后一个节点
    TreeNode nextLevelEndTreNode = null;
    while (!nodeQueue.isEmpty()) {
        TreeNode treeNode = nodeQueue.poll();

        if (treeNode.left != null) {
            nodeQueue.add(treeNode.left);
            nextLevelEndTreNode = treeNode.left;
        }
        if (treeNode.right != null) {
            nodeQueue.add(treeNode.right);
            nextLevelEndTreNode = treeNode.right;
        }

        if (treeNode == curLevelEndTreeNode) {
            maxCount = Math.max(++curCount, maxCount);
            curLevel++;
            curCount = 0;
            curLevelEndTreeNode = nextLevelEndTreNode;
            nextLevelEndTreNode = null;
        } else {
            curCount++;
        }


    }
    return maxCount;
}
```

> 判断是否为搜索二叉树

原版

```java
/**
* 判断一个树是否为搜索二叉树(左子树的所有节点的值一定小于当前节点的值;右子树的所有节点的值一定大于当前)
* @param head
* @return
*/
public static boolean isBST(TreeNode head) {
    if (head != null) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = head;
        // 可以设置一个值用来保存前一个节点的值
        // 因为 [左子树的最大值 > 当前节点; 当前节点 > 右子树最小值] 的这个判断逻辑可以通过 "中序遍历" 解决
        // 中序遍历: 先遍历左节点，然后遍历当前节点，再遍历右节点
        int preValue = Integer.MIN_VALUE;
        while (!stack.isEmpty() || temp != null) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            } else {
                temp = stack.pop();
                // 如果前一个节点的值大于当前节点的值就代表不是搜索树
                if (preValue > temp.value) {
                    return false;
                } else {
                    preValue = temp.value;
                }
                temp = temp.right;
            }
        }
    }
    return true;

}
```

树型dp:

```java
/**
* 判断一颗树是否为搜索二叉树
*
* @param node
* @return
*/
public static BSTInfo isBST2(TreeNode node) {
    if (node == null) {
        return null;
    }
    BSTInfo left = isBST2(node.left);
    BSTInfo right = isBST2(node.right);
    /*
    * 解释一下 flag：
    *   当 left == null 时代表左边是搜索二叉树
    *   当 left != null 时需要通过 
    *       left.flag(左子树是否为搜素二叉树) 以及
    *       将左子树中最大值和当前节点的值进行比较，如果 left.max < node.value 就代表左边是搜索二叉树
    *   right 同理
    * */
    boolean flag = (left == null || (left.flag && left.max < node.value))
        &&
        (right == null || (right.flag && right.min > node.value));
    int min = left != null ? Math.min(left.min, node.value) : node.value;
    int max = right != null ? Math.max(right.max, node.value) : node.value;

    return new BSTInfo(flag, min, max);
}

private static class BSTInfo {
    // 是否为搜索二叉树
    private boolean flag;
    // 当前子树上的最小值
    private int min;
    // 当前子树上的最大值
    private int max;

    public BSTInfo() {
    }

    public BSTInfo(boolean flag, int min, int max) {
        this.flag = flag;
        this.min = min;
        this.max = max;
    }
}
```

> 判断是否为完全二叉树

```java
/**
* 判断一颗树是否为完全二叉树(若设二叉树的深度为k，除第 k 层外，其它各层 (1～k-1) 的结点数都达到最大个数，第k 层所有的结点都连续集中在最左边，这就是完全二叉树。)
*     可能出现的情况：
*         1. 当一个节点的 left == null && right != null 则就不是完全二叉树
*         2. 节点要么是叶子节点，要么 (left & right) != null，要么 left != null && right == null
*         3. 当出现了 叶子节点或只有一个子节点的节点时 之后的所有节点只能是叶子节点
* @param head
* @return
*/
public static boolean isCBT(TreeNode head) {
    // 定义一个变量用来区分是否出现了 left != null && right == null 的节点
    boolean flag = false;

    // 做树宽度遍历
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(head);
    while (!queue.isEmpty()) {
        TreeNode treeNode = queue.poll();
        // 当一个节点的 left == null && right != null 则就不是完全二叉树
        if (treeNode.left == null && treeNode.right != null) {
            return false;
        }

        // 当出现了 [(left != null && right == null)或叶子节点] 时之后的所有节点只能是叶子节点
        // 这里可以简化成 left != null 即可，原理可以自己想想
        if (flag && treeNode.left != null) {
            return false;
        }

        // left == null => 叶子节点; right == null => 左节点有右节点没用
        boolean check = treeNode.left == null || treeNode.right == null;

        // 当出现了 叶子节点或只有一个子节点的节点时 之后的所有节点只能是叶子节点
        if (check && !flag) {
            flag = true;
        }

        if (treeNode.left != null) {
            queue.add(treeNode.left);
        }

        if (treeNode.right != null) {
            queue.add(treeNode.right);
        }
    }
    return true;
}
```

> 判断是否为满二叉树

树型dp：树型动态规划，将问题拆分出来通过递归可以得到想要的答案(**FBTInfo**)，再整理答案返回给上一层

```java
/**
* (树型dp) 判断一颗树是否为满二叉树
*
* @param head
* @return
*/
public static FBTInfo isFBT(TreeNode treeNode) {
    if (treeNode == null) {
        return new FBTInfo(0, 0);
    }
    FBTInfo left = isFBT(treeNode.left);
    FBTInfo right = isFBT(treeNode.right);
    int height = Math.max(left.height, right.height) + 1;
    int size = left.size + right.size + 1;
    return new FBTInfo(height, size);
}

private static class FBTInfo {
    private int height;
    private int size;

    public FBTInfo() {
    }

    public FBTInfo(int height, int size) {
        this.height = height;
        this.size = size;
    }
}
```

> 判断是否为平衡二叉树

```java
/**
* 判断是否为平衡二叉树(每个节点的左节点和右节点最大深度相差不能超过1)
* @param node
* @return
*/
public static BBTInfo isBBT(TreeNode node) {
    if (node == null) {
        return new BBTInfo(true, 0);
    }
    BBTInfo leftInfo = isBBT(node.left);
    BBTInfo rightInfo = isBBT(node.right);
    boolean flag = leftInfo.flag && rightInfo.flag && Math.abs(leftInfo.level - rightInfo.level) < 2;
    int level = Math.max(leftInfo.level, rightInfo.level) + 1;

    return new BBTInfo(flag, level);
}


private static class BBTInfo {
    private boolean flag;
    private int level;

    public BBTInfo() {
    }

    public BBTInfo(boolean flag, int level) {
        this.flag = flag;
        this.level = level;
    }
}
```

### 图

> 可以描述图的数据结构是非常多的，对于不同的算法题目也可能会给不同的数据结构，那么我们可以自己定义一个**图的模板**，当我们遇到一些表示图的数据结构时，我们只用将其转换为自己的模板,然后再写相关逻辑就会方便很多

> 这里是使用的 dalao 已经写好的模板，可以自行补充

```java
/**
 * 图结构中表示边的类
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/4/21 9:35
 */
public class Edge {

    /**
     * 权重
     */
    public int weight;

    /**
     * 入点
     */
    public Node form;

    /**
     * 出点
     */
    public Node to;

    public Edge(int weight, Node form, Node to) {
        this.weight = weight;
        this.form = form;
        this.to = to;
    }
}
```

```java
/**
 * 图结构中表示节点的类
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/4/21 9:30
 */
public class Node {

    /**
     * 节点的值
     */
    public int value;

    /**
     * 节点的入度
     */
    public int in;

    /**
     * 节点的出度
     */
    public int out;

    /**
     * 由当前节点发散的边所能指向的节点
     */
    public ArrayList<Node> nexts;

    /**
     * 由当前节点发散的边
     */
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
```

```java
public class Graph {

    public HashMap<Integer, Node> nodes;

    public HashSet<Integer> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
```

> (算法1) 图的宽度优先遍历

```java
/**
* 图的宽度优先遍历
* @param node
*/
public void exec1(Node node){
    // 使用队列辅助完成
    Queue<Node> queue = new LinkedList<>();
    // 使用 set 集合去重
    HashSet<Node> set = new HashSet<>();
    queue.add(node);
    set.add(node);
    while (!queue.isEmpty()) {
        Node node1 = queue.poll();

        /*
        * 在进行图的宽度优先遍历时对数据的操作
        * */
        System.out.println(node1.value);

        for (Node next : node1.nexts) {
            // 如果 set 集合中还没有 next 节点，就表示它还没有遍历过，就加入到队列中
            if (!set.contains(next)) {
                queue.add(next);
                set.add(next);
            }
        }
    }
}
```

> (算法2) 图的广度优先遍历

```java
public void exec2(Node node) {
    Stack<Node> nodeStack = new Stack<>();
    // 去重
    HashSet<Node> set = new HashSet<>();
    nodeStack.push(node);
    set.add(node);
    while (!nodeStack.isEmpty()) {
        Node node1 = nodeStack.pop();
        System.out.println(node1.value);
        for (Node next : node1.nexts) {
            if (!set.contains(next)) {
                nodeStack.push(next);
                set.add(next);
            }
        }
    }
} 
```

> (算法3) 拓扑排序算法

```java
/**
* 拓扑排序:
*  - 要求有向图，且有入度为 0 的节点，并且没有环
*  - 说明：在开发的实际过程中，如果我们引用了很多 Maven 依赖，
*         那么如何确定依赖打包的顺序(避免依赖循环)，就是拓扑排序所需要解答的
*  - 思路：先找到入度为 0 的节点(不依赖其他依赖)，
*         然后将其 nexts(需要依赖当前节点的依赖) 的入度 -1(表示它需要的依赖已经搞定一个)
*         依次类推，直到没有入度为 0 的节点
* @param graph
*/
public ArrayList<Node> exec3(Graph graph) {
    // 只有入度为 0 的节点才能进入的队列
    Queue<Node> zeroInQueue = new LinkedList<>();
    // 统计所有节点的入度信息
    HashMap<Node, Integer> nodeInMap = new HashMap<>();
    for (Node node : graph.nodes.values()) {
        nodeInMap.put(node, node.in);
        if (node.in == 0) {
            zeroInQueue.add(node);
        }
    }
    ArrayList<Node> result = new ArrayList<>();
    while (!zeroInQueue.isEmpty()) {
        Node pollNode = zeroInQueue.poll();
        result.add(pollNode);
        for (Node next : pollNode.nexts) {
            nodeInMap.put(next, nodeInMap.get(next) - 1);
            if (nodeInMap.get(next) == 0) {
                zeroInQueue.add(next);
            }
        }
    }
    return result;
}
```

> 生成最小生成树：

- `kruskal` 算法(要求无向图)

  - 需要依赖于**并查集**结构

    ```java
    /**
    * 一个简单版本的"并查集"结构
    *  主要功能：
    *      1) 合并：提供两个不交集(Disjoint sets，一系列没有重复元素的集合)的合并
    *      2) 查询：查询两个元素是否在一个集合内
    */
    public static class UnionFind {
    
        public HashMap<Node, List<Node>> setMap;
    
        public UnionFind(List<Node> nodes) {
            for (Node node : nodes) {
                ArrayList<Node> nodeList = new ArrayList<>();
                nodeList.add(node);
                setMap.put(node, nodeList);
            }
        }
    
        /**
        * 查询两个节点是否在一个集合内
        * @param node1
        * @param node2
        * @return
        */
        public boolean isSameSet(Node node1, Node node2) {
            return setMap.get(node1) == setMap.get(node2);
        }
    
        /**
        * 合并两个集合
        * @param node1
        * @param node2
        */
        public void unionSet(Node node1, Node node2) {
            List<Node> node1List = setMap.get(node1);
            List<Node> node2List = setMap.get(node2);
            for (Node node : node2List) {
                node1List.add(node);
                setMap.put(node, node1List);
            }
        }
    }
    ```

  - 主要思路：

    - 每个节点都对应一个集合，这个集合表示与该节点连通的节点集合

    - 将所有的边进行一个排序，从最小的边开始，判断 `form` & `to` 是否在一个集合内，如果不在，就将它们放在一个集合内

    - 如果一个边可以将 form/to 节点放到集合内，那么这个边就是最小生成树中的一个边

  - 代码实现：

    ```java
    /**
    * 最小生成树 - k 算法
    * @param graph
    * @return
    */
    public List<Edge> kruskal(Graph graph) {
        UnionFind unionFind = new UnionFind(graph.nodes.values());
        // 使用小根堆来辅助排序
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        priorityQueue.addAll(graph.edges);
        ArrayList<Edge> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            // 如果 edge 的 form/to 不在最小生成树对应的集合内，就它们加进去
            if (!unionFind.isSameSet(edge.form, edge.to)) {
                unionFind.unionSet(edge.form, edge.to);
                result.add(edge);
            }
        }
        return result;
    }
    
    private class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    ```

- `prim` 算法(要求无向图)

  - 主要思路：
  
    - 从任意一个节点出发，通过其属性 `edges` 获取它所有的相关的**边**，将边加入到**小根堆队列**中
  
    - 获取小根堆队列的堆顶，可以得到一个 `weight` 最小的边，根据其属性 `to` 可以访问到下一个节点 `next`
  
      如果当前节点没有在**最小生成树**中，那么把**它和刚刚找到的边**加入到最小生成树中
  
    - 将下一个 `next` 节点的 `edges` 属性保存到**小根堆队列**中
  
  - 代码实现
  
    ```java
    /**
    * 最小生成树 - p 算法
    *
    * @param graph
    * @return
    */
    public List<Edge> prim(Graph graph) {
        // 去重集合
        HashSet<Node> set = new HashSet<>();
        // 小根堆
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        // 最小生成树的边
        ArrayList<Edge> result = new ArrayList<>();
    
        for (Node node : graph.nodes.values()) {
            queue.addAll(node.edges);
            set.add(node);
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                Node next = edge.to;
                if (!set.contains(next)) {
                    set.add(next);
                    queue.addAll(next.edges);
                    result.add(edge);
                }
            }
    
        }
    
        return result;
    }
    ```
  

> 迪杰斯特拉：(适用范围：不能有累加和为负数的环)

- 主要思路：

  1. 迪杰斯特拉的目标是为了求 **从一个点出发到图上任意点的最短路径**
  2. 可以使用一个哈希表来记录 `head` 节点到图上所有节点的距离(包括自己，且距离为 0)
  3. 从哈希表中找到一个当前距离最小的**节点 B**，然后访问它的 `edges` 属性获得 距离(weight) & 到达的节点(to)
  4. 判断 **哈希表.get(to)** VS **哈希表.get(节点B) + weight** 谁小，就用谁 哈希表.put(to, 小距离)
  5. 锁定节点B，然后再进行第 3 步(被锁定的节点一定要不会被找到)

- 代码实现

  ```java
  /**
  * 迪杰斯特拉
  * @param head
  * @return
  */
  public HashMap<Node, Integer> dijkstra(Node head) {
      // 设置一个哈希表，复杂记录 head 节点到每个节点的最短距离
      HashMap<Node, Integer> distanceMap = new HashMap<>();
      // 当然了，包括到自己的距离(呲牙.jpg)
      distanceMap.put(head, 0);
      // 设置一个可以用来保存被锁定的节点的结构
      HashSet<Node> selectNodes = new HashSet<>();
      // 找到 distanceMap 结构中距离最小且没有被锁定的节点
      Node minNode = getMinDistanceAndNuSelectedNode(distanceMap, selectNodes);
      while (minNode != null) {
          int distance = distanceMap.get(minNode);
          for (Edge edge : minNode.edges) {
              Node to = edge.to;
              int weight = edge.weight;
              // 如果距离表中没有该节点，那么直接新增即可
              if (!distanceMap.containsKey(to)) {
                  distanceMap.put(to, distance + weight);
              } else {
                  distanceMap.put(to, Math.min(distanceMap.get(to), distance + weight));
              }
          }
          // 锁定当前节点
          selectNodes.add(minNode);
          // 重新找
          minNode = getMinDistanceAndNuSelectedNode(distanceMap, selectNodes);
      }
      return distanceMap;
  }
  
  /**
  * 找到 distanceMap 结构中距离最小且没有被锁定的节点
  * @param distanceMap
  * @param selectNodes
  * @return
  */
  private Node getMinDistanceAndNuSelectedNode(HashMap<Node, Integer> distanceMap,
                                               HashSet<Node> selectNodes) {
      Node minNode = null;
      int minValue = Integer.MAX_VALUE;
      for (Map.Entry<Node, Integer> nodeIntegerEntry : distanceMap.entrySet()) {
          Node node = nodeIntegerEntry.getKey();
          Integer distance = nodeIntegerEntry.getValue();
          if (!selectNodes.contains(node) && minValue > distance) {
              minNode = node;
              minValue = distance;
          }
      }
      return minNode;
  }
  ```
  
- 改写堆结构

  ```java
  ```

  

### 前缀树

概念：也称为字典树/单词查找树/键树，是一种树型结构；典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。它的优点是：利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较。



```json
yu: {
    hou: {
        
    }
    qian: {
        
    }
}
```





实现：

```java
public class TireNode {

    /**
     * 以当前节点为前缀的节点个数
     */
    public int pass;

    /**
     * 以当前节点为结尾的节点个数
     */
    public int end;

    /**
     * 当前的节点连接的节点
     *  - 这里的用数组是因为数据量小
     *  - 如果实战中使用，可以用 HashMap<Char, TireNode>
     */
    public TireNode[] nodes;

    public TireNode() {
        pass = 0;
        end = 0;
        //  这里的学习是以 26 个字母组成的字符串为例的，所以用一个拥有 26 个位置的数组来存储
        nodes = new TireNode[26];
    }
}
```

```java
public class Tire {

    private TireNode root;

    public Tire() {
        this.root = new TireNode();
    }

    /**
     * 将字符串插入到前缀树中
     *
     * @param str
     */
    private void insert(String str) {
        // 获取字符数组
        char[] chars = str.toCharArray();
        root.pass++;
        TireNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            // 通过 字符相减规律 可以得到当前字符在数组中 index(a=0, b=1....z=25)
            int index = chars[i] - 'a';
            if (temp.nodes[index] == null) {
                temp.nodes[index] = new TireNode();
            }
            // 经过当前字符的节点数量++
            temp.nodes[index].pass++;
            // 下移到当前字符的节点
            temp = temp.nodes[index];
        }
        // 以当前字符的节点作为结尾的个数++
        temp.end++;
    }

    /**
     * 查询字符串在前缀树中出现的次数
     *
     * @param str
     * @return
     */
    private int search(String str) {
        if (str == null) {
            return 0;
        }
        // 获取字符数组
        char[] chars = str.toCharArray();
        TireNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            // 字符串还未遍历完，已经没有对应的字符节点了，代表该字符不存在于前缀树中，直接返回0
            if (temp.nodes[index] == null) {
                return 0;
            }
            temp = temp.nodes[index];
        }
        return temp.end;
    }

    /**
     * 删除前缀树中的某个字符串
     *
     * @param str
     */
    private void delete(String str) {
        // 一定要保证该字符串是存在于树中的
        if (search(str) != 0) {
            char[] chars = str.toCharArray();
            TireNode temp = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (--temp.nodes[index].pass == 0) {
                    // 如果当前字符节点已经没有经过的字符了，就直接置空即可
                    temp.nodes[index] = null;
                    return;
                }
                temp = temp.nodes[index];
            }
            temp.end--;
        }
    }

    /**
     * 查找以指定字符作为前缀的字符串数据个数
     *
     * @param str
     * @return
     */
    private int prefixNumber(String str) {
        if (str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        TireNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            // 字符串还未遍历完，已经没有对应的字符节点了，代表该字符不存在于前缀树中，直接返回0
            if (temp.nodes[index] == null) {
                return 0;
            }
            temp = temp.nodes[index];
        }
        return temp.pass;
    }

}
```

### 贪心算法

**概念**：

在对问题求解时，总是做出在**当前看来**是最好的选择。即不从整体最优上加以考虑，算法得到的是在某种意义上的局部最优解。但一般情况下我们希望可以通过这个局部最优解得到全局的最优解

**笔试解题套路**：

1. 实现一个不依靠贪心策略的的解法X(可以用暴力解)
2. 根据不同标准编写不同的贪心策略(A/B/C等)
3. 用**解法X**和**对数器**，去验证每个贪心策略，用实验的方式得知哪个贪心策略正确
4. **不要去纠结贪心策略的证明**(为什么按照这种策略就是最优解，不要去纠结这个，如果要求这个根据不同的业务性质需要一些数学功底)

**经常使用的技巧**：

1. 根据某标准建立一个比较器来排序
2. 根据某标准建立一个比较器来组成堆

**例题：**

> 会议问题：有n个项目和1个会议室，会议室最多只能给1个项目使用，且每个项目都有自己宣讲开始和结束的时间，在规定时间内(例如早上6点到晚上6点)，由你来安排，要求**会议室进行宣讲的场次最多**

思路：

- 先列出大概有什么策略(按谁先开始从小到大/按持续时间从小到大/按结束时间从小到大等等)
- 然后看看能不能找出以上策略的反例
  1. 按谁先开始从小到大-有一个项目从早上6点开到晚上6点，寄
  2. 按持续时间从小到大-有3个项目：[{6:00 ~ 12:00}, {11:00 ~ 13:00}, {12:00 ~ 18:00}]，这样只能讲中间这1个，寄
  3. 按结束时间从小到大-好像没有
- 那我们就以 **按结束时间从小到大** 进行编码

实现：

```java
```

### 暴力递归

**概念：暴力递归就是尝试**

1. 把问题转换为**规模缩小了的同类问题**的子问题
2. 有明确的不需要继续进行递归的条件(也称为 base case)
3. 有当得到了的子问题的结果之后决策过程
4. 不需要记录每一个子问题的解

**例题：**

> 汉诺塔问题

```java
public static void main(String[] args) {
    move(3, "左", "右", "中");
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
```

> 打印一个字符串的全部子序列，包括空字符串(两种做法)

```java
private static void printAllSubsequence(int n, String str, List<Character> sub) {
    if (n == str.length()) {
        System.out.println(sub);
        return;
    }
    printAllSubsequence(n + 1, str, sub);
    sub.add(str.charAt(n));
    printAllSubsequence(n + 1, str, sub);
    sub.remove(Character.valueOf(str.charAt(n)));
}

private static void printAllSubsequence(int n, char[] str) {
    if (n == str.length) {
        System.out.println(str);
        return;
    }
    char temp = str[n];
    // 要这个字符
    printAllSubsequence(n + 1, str);
    // 不要这个字符，置空
    str[n] = 0;
    printAllSubsequence(n + 1, str);
    // 还原
    str[n] = temp;
}
```

> 打印一个字符串的全部排列

> 打印一个字符串的全部排列，并且要求不出现重复的排列

> ![image-20220512214800924](README.assets/image-20220512214800924.png)

> 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数

>  ![image-20220512220548351](README.assets/image-20220512220548351.png)

> ![image-20220512221807878](README.assets/image-20220512221807878.png)

> N皇后

## 解题技巧 

### 异或运算的性质与扩展

异或运算：N ^ N = 0; N ^ 0 = N

性质：

- 满足交换律和结合律

- 可以不用额外变量交换两个数(交换的两个数据不能是内存地址相同的，否则两个数据都会变成 0)
  
  ```java
  arr[i] = arr[i] ^ arr[j]; // a = a^b; b=b
  arr[j] = arr[i] ^ arr[j]; // a = a^b; b = a^b^b => a ^ 0 = a
  arr[i] = arr[i] ^ arr[j]; // a = a^b^a => b^0 = b; b =a
  ```

### 对数器

作用：

- 对于有一些题目，如果是在线上 OJ 测试一把只会给几个简单的 smaples，但有可能我们的算法虽然能处理一些简单的 samples，但遇到复杂的 samples 可能又会出现问题；
- 这个时候我们为题目编写两套算法，一套是我们精心设计的算法，另一套可能是暴力解法等等；这时再通过**对数器生成大量的随机样例并同时调用这两个算法**，对比结果；
- 对数器中，我们要求的绝对正确的算法是没有时间和空间复杂度的限制的，唯一的要求是确保绝对正确。因为只有绝对正确，我们才能通过样例的比对，发现我们的代码是在哪里出了错误

实现：

```java
//生成一个随机的但是可控的数组
public static int[] generateRandomArray(int size, int value)
{
    // Math.random() -> double [0,1)
    // (int) ((size + 1) * Math.random()) -> [0,size]整数
    // 生成长度随机[0, size]的数组
    int[] arr = new int[(int) ((size+1) * Math.random())];
    for (int i = 0; i < arr.length; i++)
    {
        // 一个随机数减去另一个随机数，生成[-value, value]的随机数
        arr[i] = (int) ((value+1) * Math.random()) - (int) (value * Math.random());
    }
    return arr;
}
```

```java
// 判断两个数组是否相等
public static boolean isEqual(int[] arr1, int[] arr2)
{
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null))
        return false;
    if (arr1 == null && arr2 == null)
        return true;
    if (arr1.length != arr2.length)
        return  false;
    for (int i = 0; i < arr1.length; i++)
    {
        if (arr1[i] != arr2[i])
            return false;
    }
    return true;
}
```

### 比较器

使用：

1. 比较器的实质就是重载比较运算符
2. 可以用在需要按照特殊排序规则的 算法/结构 上

java 实现：

```java
/**
 * 比较器：可以通过按照特定规则进行排序
 * @author 23911
 * @version 1.0
 * @date 2022/3/25 16:19
 */
public class Comparators implements Comparator<Integer> {

    /**
     * 比较两个参数的方法
     *  规则：
     *      当返回值 < 0 时，o1 在前面，o2 在后面
     *      当返回值 > 0 时，o1 在后面，o2 在前面
     *      当返回值 = 0 时，都可以
     *  技巧：
     *      降序：{@code return o1 - o2}
     *      升序：{@code return o2 - o1}
     * @author 23911
     * @date 2022/3/25 16:21
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }

}
```

注意：`compare` 方法的两个参数类型是根据实现 **Comparator** 接口时定义的泛型而定的

### 求中位数

- 一般的写法：mid = (l + r) / 2
  
  - 存在问题：l + r 可能会导致 **整型溢出**，当然了也只有 R / L 特别的情况下才有可能发现

- 更好的写法: mid = l + ((r - l) / 2)
  
  因为用的是减法，不会导致 **整型溢出**

- 究极简化版：mid = l + ((r - l) >> 1)

### 链表题目的相关技巧

解题技巧：

1. 额外数据结构记录(哈希表等)
2. 快慢指针

面试时链表解题的方法论

- 针对**笔试**，使用时间复杂度低的算法即可，不用太在于空间复杂度
- 针对**面试**，在保证时间复杂度低的同时，尽量减小空间复杂度

#### 回环链表求出回环点

> 规律：在回环链表上使用快慢索引，当快慢索引第一次相遇时，让快索引回到 head 节点，慢索引不动
> 
> 然后和慢索引同时移动，每次移动一步，当两个节点再次相遇时，就是回环节点

```java
private static Node isLoop(Node head) {
    if (head == null || head.next == null || head.next.next == null) {
        return null;
    }
    // 定义快慢指针
    Node idx1 = head.next;
    Node idx2 = head.next.next;

    while (idx2 != idx1) {
        if (idx2.next == null || idx2.next.next == null) {
            return null;
        }
        idx1 = idx1.next;
        idx2 = idx2.next.next;
    };
    // 回环链表不可能遍历完
    if (idx2 == null) {
        return null;
    }
    idx2 = head;
    /*
    * 这里有个规律：
    * 在回环链表上使用快慢索引，当快慢索引第一次相遇时，此时快索引回到 head 节点，
    * 和慢索引同时移动，每次移动一步，当两个节点再次相遇时，就是回环节点
    * */
    while (idx2 != idx1) {
        idx1 = idx1.next;
        idx2 = idx2.next;
    }
    return idx1;
}
```