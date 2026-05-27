package com.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {

    private RedBlackTree<Integer, Integer> insertRedBlackTree;
    private RedBlackTree<Integer, Integer> redBlackTree;
    private TreeNode<Integer, Integer> root;
    private TreeNode<Integer, Integer> left;
    private TreeNode<Integer, Integer> right;
    private TreeNode<Integer, Integer> leftsLeftChild;
    private TreeNode<Integer, Integer> leftsRightChild;
    private TreeNode<Integer, Integer> rightsLeftChild;
    private TreeNode<Integer, Integer> rightsRightChild;

    @BeforeEach
    void setUp() {
        insertRedBlackTree = new RedBlackTree<>();
        redBlackTree = new RedBlackTree<>();
        for (Integer i : List.of(60, 40, 70, 20, 50, 10)) {
            redBlackTree.putNode(i, 10, i);
        }
    }

    void putNodes(Integer... values) {
        for (Integer value : values) {
            insertRedBlackTree.putNode(value, value, value);
        }
    }

    void setUpTree(RedBlackTree<Integer, Integer> redBlackTree) {
        root = redBlackTree.root;
        left = root.link[0];
        right = root.link[1];
        if (left != null) {
            leftsLeftChild = left.link[0];
            leftsRightChild = left.link[1];
        }
        if (right != null) {
            rightsLeftChild = right.link[0];
            rightsRightChild = right.link[1];
        }
    }

    void assertValueAndColor(TreeNode<Integer, Integer> node, boolean isRed, Integer... values) {
        if (values.length < 1) {
            return;
        }
        Integer key = values[0];
        assertEquals(key, node.key);
        assertEquals(values.length > 1 ? values[1] : key, node.value);
        assertEquals(values.length > 2 ? values[2] : key, node.hash);
        assertEquals(isRed, node.red);
    }

    @Test
    void shouldPutElementWhenTreeRootIsEmpty() {
        putNodes(10);
        setUpTree(insertRedBlackTree);
        assertValueAndColor(root, false, 10);
    }

    @Test
    void shouldPutElementWhenItDoesNotExists() {
        putNodes(10, 20);
        assertNotNull(root.link);
        assertEquals(2, root.link.length);
    }

    @Test
    void shouldPutNodeWhenItDoesNotExist() {
        putNodes(10, 90);
        setUpTree(insertRedBlackTree);
        assertValueAndColor(root, false, 10);
        assertValueAndColor(right, true, 90);
    }

    @Test
    void shouldNotPutNodeWhenItExists() {
        putNodes(10, 10);
        setUpTree(insertRedBlackTree);
        assertValueAndColor(root, false, 10);
    }

    @Test
    void shouldPutLeftNodeWhenItHasLeftRedParentAndBlackUncle() {
        putNodes(10, 90, 20, 80, 50);
        setUpTree(insertRedBlackTree);
        assertValueAndColor(root, false, 20);
        assertValueAndColor(left, false, 10);
        assertValueAndColor(right, false, 80);
        assertValueAndColor(rightsLeftChild, true, 50);
        assertValueAndColor(rightsRightChild, true, 90);
    }

    @Test
    void shouldPutRightNodeWhenItHasRightRedParentAndBlackUncle() {
        putNodes(10, 90, 20, 80, 50, 30, 40, 60, 70);
        setUpTree(insertRedBlackTree);
        assertValueAndColor(root, false, 40);
        assertValueAndColor(left, false, 20);
        assertValueAndColor(right, false, 80);
        assertValueAndColor(leftsLeftChild, false, 10);
        assertValueAndColor(leftsRightChild, false, 30);
        assertValueAndColor(rightsLeftChild, false, 60);
        assertValueAndColor(rightsRightChild, false, 90);
        assertValueAndColor(rightsLeftChild.link[0], true, 50);
        assertValueAndColor(rightsLeftChild.link[1], true, 70);
    }

    @Test
    void shouldPutLeftNodeWhenItHasRightRedParentAndBlackUncle() {
        putNodes(10, 90, 20);
        setUpTree(insertRedBlackTree);
        assertValueAndColor(root, false, 20);
        assertValueAndColor(left, true, 10);
        assertValueAndColor(right, true, 90);
    }

    @Test
    void shouldPutRightNodeWhenItHasLeftRedParentAndBlackUncle() {
        putNodes(10, 90, 20, 80, 50, 30, 40);
        setUpTree(insertRedBlackTree);
        assertValueAndColor(root, false, 20);
        assertValueAndColor(left, false, 10);
        assertValueAndColor(right, true, 80);
        assertValueAndColor(rightsLeftChild, false, 40);
        assertValueAndColor(rightsRightChild, false, 90);
        assertValueAndColor(rightsLeftChild.link[0], true, 30);
        assertValueAndColor(rightsLeftChild.link[1], true, 50);
    }

    @Test
    void shouldPutNodeWhenItHasRedParentAndRedUncle() {
        putNodes(10, 90, 20, 80);
        setUpTree(insertRedBlackTree);
        assertValueAndColor(root, false, 20);
        assertValueAndColor(left, false, 10);
        assertValueAndColor(right, false, 90);
        assertValueAndColor(rightsLeftChild, true, 80);
    }

    @Test
    void shouldRemoveRoot() {
        redBlackTree.remove(60, 60, 60);
        setUpTree(redBlackTree);
        assertValueAndColor(root, false, 50);
        assertValueAndColor(left, true, 20);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, false, 10);
        assertValueAndColor(leftsRightChild, false, 40);
    }

    @Test
    void shouldNotRemoveNodeWhenItDoesNotExist() {
        redBlackTree.remove(105, 105, 105);
        setUpTree(redBlackTree);
        assertValueAndColor(root, false, 40);
        assertValueAndColor(left, false, 20);
        assertValueAndColor(right, false, 60);
        assertValueAndColor(leftsLeftChild, true, 10);
        assertValueAndColor(rightsLeftChild, true, 50);
        assertValueAndColor(rightsRightChild, true, 70);
    }

    @Test
    void shouldRemoveNodeWhenItDoesNotExist() {
        redBlackTree.remove(10, 10, 10);
        setUpTree(redBlackTree);
        assertValueAndColor(root, false, 60);
        assertValueAndColor(left, true, 40);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, false, 20);
        assertValueAndColor(leftsRightChild, false, 50);
    }

    @Test
    void shouldRemoveBlackNodeWhenItHasRedParentAndBlackSibling() {
        redBlackTree.remove(10, 10, 10);
        redBlackTree.remove(50, 50, 50);
        setUpTree(redBlackTree);
        assertValueAndColor(root, false, 60);
        assertValueAndColor(left, false, 40);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, true, 20);
    }

    @Test
    void shouldRemoveBlackNodeWhenItHasBlackParentAndRedSibling() {
        redBlackTree.putNode(80,80,80);
        redBlackTree.remove(70, 70, 70);
        setUpTree(redBlackTree);
        assertValueAndColor(root, false, 40);
        assertValueAndColor(left, false, 20);
        assertValueAndColor(right, true, 60);
        assertValueAndColor(leftsLeftChild, true, 10);
        assertValueAndColor(rightsLeftChild, false, 50);
        assertValueAndColor(rightsRightChild, false, 80);
    }

    @Test
    void shouldRemoveBlackNodeWhenItHasRedParentRedSiblingAndLeftRedNephew() {
        redBlackTree.remove(10, 10, 10);
        redBlackTree.putNode(30, 30, 30);
        redBlackTree.remove(50, 50, 50);
        setUpTree(redBlackTree);
        assertValueAndColor(root, false, 60);
        assertValueAndColor(left, true, 30);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, false, 20);
        assertValueAndColor(leftsRightChild, false, 40);
        assertNull(rightsLeftChild);
        assertNull(rightsRightChild);
    }

    @Test
    void shouldRemoveBlackNodeWhenItHasRedParentRedSiblingAndRightRedNephew() {
        redBlackTree.remove(50, 50, 50);
        setUpTree(redBlackTree);
        assertValueAndColor(root, false, 60);
        assertValueAndColor(left, true, 20);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, false, 10);
        assertValueAndColor(leftsRightChild, false, 40);
        assertNull(rightsLeftChild);
        assertNull(rightsRightChild);
    }
}