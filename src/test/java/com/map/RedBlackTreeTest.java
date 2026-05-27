package com.map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RedBlackTreeTest {

    RedBlackTree<Integer, Integer> tree;
    TreeNode<Integer, Integer> root;

    @BeforeEach
    void setUp() {
        tree = new RedBlackTree<>();
        tree.putNode(1, 1, 1);
        root = tree.root;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldPutElementWhenTreeRootIsEmpty() {
        assertNotNull(root);
        assertNotNull(root.link);
        assertEquals(1, root.key);
        assertEquals(1, root.value);
        assertEquals(1, root.hash);
    }

    @Test
    void shouldPutElementWhenItDoesNotExists() {
        tree.putNode(2, 1, 1);
        assertNotNull(root.link);
        assertEquals(2, root.link.length);
    }

    @Test
    void shouldNotPutElementWhenItExists() {
    }

    @Test
    void shouldRemoveRoot() {}


//    @Test
//    void shouldRemoveRoot() {}
//
//
//    @Test
//    void shouldRemoveRoot() {}

}