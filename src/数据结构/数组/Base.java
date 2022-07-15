package 数据结构.数组;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本概念
 * @author 小丶木曾义仲丶哈牛柚子露丶蛋卷
 * @version 1.0
 * @date 2022/7/15 18:48
 */
public class Base {

    public static void main(String[] args) {
        // (方式1)初始化数组
        // 创建一个长度为 8 的数组
        int[] array = new int[8];
        // 元素赋值：长度为8，那么存放数据的索引(可以理解场编号)就为0~7
        array[0] = 8;

        // (方式2)初始化数组
        // 直接赋值
        int[] array2 = {1, 2, 3, 4, 5};

        // (方式3)创建可变数组
        List<Integer> arrayList = new ArrayList<>();
        // 向数组尾部添加元素
        arrayList.add(8);
        arrayList.add(8);
        arrayList.add(8);

    }

}
