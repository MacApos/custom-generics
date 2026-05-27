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
        for (Integer i : List.of(10, 90, 20, 80, 50, 30, 40, 60, 70)) {
            map.put(i, i);
        }

    }
}