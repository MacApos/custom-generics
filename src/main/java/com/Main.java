package com;

import com.list.CustomLinkedList;
import com.map.CustomHashMap;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList<Integer> linkedList = CustomLinkedList.of(2, 3, 5, 7);
        System.out.println("Linked list");
        System.out.printf("First element: %s\n", linkedList.getFirst());
        System.out.printf("Last element: %s\n", linkedList.getLast());
        printLinkedList(linkedList);
        System.out.println("add 11");
        linkedList.add(11);
        printLinkedList(linkedList);

        System.out.println("remove element with index 4");
        linkedList.remove(4);
        printLinkedList(linkedList);
        System.out.println("remove 5");
        linkedList.remove(Integer.valueOf(5));
        printLinkedList(linkedList);

        System.out.println("set element with index 2 to 5");
        linkedList.set(2, Integer.valueOf(5));
        printLinkedList(linkedList);
        System.out.println("set element with value of 5 to 11");
        linkedList.set(Integer.valueOf(5), Integer.valueOf(11));
        printLinkedList(linkedList);

        System.out.println("Hash map");
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        System.out.println("put");
        map.put(0, "Ala");
        map.put(1, "ma");
        map.put(2, "kota");
        printHashMap(map);

        System.out.println("set");
        map.put(2, "kota");
        map.put(2, "psa");
        printHashMap(map);

        System.out.println("remove");
        map.remove(2);
        printHashMap(map);
    }

    private static void printLinkedList(CustomLinkedList<Integer> linkedList) {
        String collect = IntStream.range(0, linkedList.size())
                .map(linkedList::get)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.printf("List size: %s, elements: %s\n", linkedList.size(), collect);
    }

    private static void printHashMap(CustomHashMap<Integer, String> hashMap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashMap.size(); i++) {
            sb.append(i).append(":").append(hashMap.get(i)).append(" ");
        }
        System.out.printf("Map size: %s, elements: %s\n", hashMap.size(), sb);
    }
}