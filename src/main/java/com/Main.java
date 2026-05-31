package com;

import com.list.CustomLinkedList;
import com.map.CustomHashMap;
import com.map.MapLinkedList;
import com.map.RedBlackTree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("", "");

        CustomHashMap<Integer, Integer> map = new CustomHashMap<>();

        List<Integer> integers = List.of(10, 90, 20, 80, 50, 30, 40, 60, 70);

        for (Integer i :integers) {
            map.put(i, i);
        }

        for (Integer i :integers) {
            map.remove(i);
        }
    }
}