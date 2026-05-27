package com;

import com.list.CustomLinkedList;
import com.map.MapLinkedList;
import com.map.RedBlackTree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> strings = new LinkedList<>();
        System.out.println(strings.add("1"));
        System.out.println(strings.add("2"));

        ArrayList<Integer> integers = new ArrayList<>();

        List.of();
        var list = new CustomLinkedList<String>();
        CustomLinkedList<String> stringCustomLinkedList = CustomLinkedList.of("s");
        CustomLinkedList<String> stringCustomLinkedList2 = CustomLinkedList.of(new String[]{"s", "b", "c"});
        strings.indexOf(1);
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(1, 3);

        MapLinkedList<Integer, String> linkedList2 = new MapLinkedList<>();
        // equal objects must have the same hashCode
//        linkedList2.putNode(1, "one", 1);
//        linkedList2.putNode(1, "two", 1);
        // unequal objects may have the same hashCode
        linkedList2.putNode(1, "one", 1);
        linkedList2.putNode(2, "one", 1);

        linkedList2.removeNode(2, "one", 1);
        linkedList2.removeNode(2, "two", 1);

        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        tree.putNode(1,1,1);
        tree.putNode(2,1,1);

        String[] strings1 = new String[2];

    }
}