## 数据结构-数组

### 基本概念

数组是将相同类型的元素存储于连续的内存空间的数据结构，其长度不可变

```java
// (方式1)初始化数组 
// 创建一个长度为 8 的数组
int[] array = new int[8];
// 元素赋值：长度为8，那么存放数据的索引(可以理解场编号)就为0~7
array[0] = 8;
```

也可以直接赋值

```java
// (方式2)初始化数组
// 直接赋值
int[] array2 = {1, 2, 3, 4, 5};
```

原本的数组都是不可变的，但一般高级语言都会进行封装 => **可变数组**，其基于数组和扩容机制实现，类似于 Java 中的 ArrayList

```java
// (方式3)创建可变数组
List<Integer> arrayList = new ArrayList<>();
// 向数组尾部添加元素
arrayList.add(8);
arrayList.add(8);
arrayList.add(8);
```

### 解题技巧一：双指针

在处理数组/链表时，双指针是经常用到的，主要分为两类：**左右指针** 和 **快慢指针**

- 左右指针：一个从左出发，一个从右出发(**前进的方向相反**)
  - 涉及关键字：二分查找
- 快慢指针：一个走的快，一个走的慢
  - 涉及关键字：原地修改数组，滑动窗口算法

#### l.26.删除有序数组的重复项

- 题目说明：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/

- 解答：

  ```java
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
  ```

- 题解：

  - 有序的 - 可以保证重复的元素是连续的
  - 通过 slow 指针记录不重复的元素位置，并完成赋值
  - 通过 fast 指针与 slow 指针的对比，判断当前元素是否已经存在，如果存在就需要完成赋值，然后查找下一个元素

- 优化：使用元素差实现

  ```java
  public int removeDuplicates(int[] nums) {
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
  ```

####  l.27.移除元素

- 题目说明：https://leetcode.cn/problems/remove-element/

- 解答：

  ```java
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
  ```

- 题解：原地修改，通过 `slow` 和 `fast` 的判断后不是 `val` 的，直接放到前面(fast)就好了

- 其他解法：[这个牛](https://leetcode.cn/problems/remove-element/solution/shua-chuan-lc-shuang-bai-shuang-zhi-zhen-mzt8/)，通过交换元素完成，写法挺 coool 的

#### l.167.两数之和 II - 输入有序数组

- 题目说明：https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/

- 解答：

  ```java
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
  ```

- 题解：

  - 注意有序，当 `tmp < target` 时，`right` 上已经是最大值了，所以需要让 `left` 元素变大一点(反之同理)

- 详细说明：[这个也牛](https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/solution/yi-zhang-tu-gao-su-ni-on-de-shuang-zhi-zhen-jie-fa/)

  > 双指针本质：相等于杨氏矩阵，而杨氏矩阵又相等于有序集合的笛卡尔积。右上角的数字就等于原序列的第一个值加上最后一个值，而我们向下走一步就意味着 `left` 向后移动一位，这刚好就是当前位置小于 `target` 的情况。同理，向左走一步就意味着 `right` 向前移动一步，也就是当前位置大于 `target` 的情况。(截取评论，可以简单理解为: 通过判断锁定一边，只改变另一边的选择)



 