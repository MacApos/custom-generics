package com.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {
    private RedBlackTree<Integer, Integer> insert;
    private RedBlackTree<Integer, Integer> remove;
    private TreeNode<Integer, Integer> root;
    private TreeNode<Integer, Integer> left;
    private TreeNode<Integer, Integer> right;
    private TreeNode<Integer, Integer> leftsLeftChild;
    private TreeNode<Integer, Integer> leftsRightChild;
    private TreeNode<Integer, Integer> rightsLeftChild;
    private TreeNode<Integer, Integer> rightsRightChild;

    @BeforeEach
    void setUp() {
        insert = new RedBlackTree<>();
        remove = new RedBlackTree<>();
        for (Integer i : List.of(60, 40, 70, 20, 50, 10)) {
            remove.putNode(i, 10, i);
        }
    }

    void putNodes(Integer... values) {
        for (Integer value : values) {
            insert.putNode(value, value, value);
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
        setUpTree(insert);
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
        setUpTree(insert);
        assertValueAndColor(root, false, 10);
        assertValueAndColor(right, true, 90);
    }

    @Test
    void shouldNotPutNodeWhenItExists() {
        putNodes(10, 10);
        setUpTree(insert);
        assertValueAndColor(root, false, 10);
    }

    @Test
    void shouldPutLeftNodeWhenItHasLeftRedParentAndBlackUncle() {
        putNodes(10, 90, 20, 80, 50);
        setUpTree(insert);
        assertValueAndColor(root, false, 20);
        assertValueAndColor(left, false, 10);
        assertValueAndColor(right, false, 80);
        assertValueAndColor(rightsLeftChild, true, 50);
        assertValueAndColor(rightsRightChild, true, 90);
    }

    @Test
    void shouldPutRightNodeWhenItHasRightRedParentAndBlackUncle() {
        putNodes(10, 90, 20, 80, 50, 30, 40, 60, 70);
        setUpTree(insert);
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
        setUpTree(insert);
        assertValueAndColor(root, false, 20);
        assertValueAndColor(left, true, 10);
        assertValueAndColor(right, true, 90);
    }

    @Test
    void shouldPutRightNodeWhenItHasLeftRedParentAndBlackUncle() {
        putNodes(10, 90, 20, 80, 50, 30, 40);
        setUpTree(insert);
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
        setUpTree(insert);
        assertValueAndColor(root, false, 20);
        assertValueAndColor(left, false, 10);
        assertValueAndColor(right, false, 90);
        assertValueAndColor(rightsLeftChild, true, 80);
    }

    @Test
    void shouldRemoveNodeRoot() {
        remove.removeNode(60, 60);
        setUpTree(remove);
        assertValueAndColor(root, false, 50);
        assertValueAndColor(left, true, 20);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, false, 10);
        assertValueAndColor(leftsRightChild, false, 40);
    }

    @Test
    void shouldNotRemoveNodeNodeWhenItDoesNotExist() {
        remove.removeNode(105, 105);
        setUpTree(remove);
        assertValueAndColor(root, false, 40);
        assertValueAndColor(left, false, 20);
        assertValueAndColor(right, false, 60);
        assertValueAndColor(leftsLeftChild, true, 10);
        assertValueAndColor(rightsLeftChild, true, 50);
        assertValueAndColor(rightsRightChild, true, 70);
    }

    @Test
    void shouldRemoveNodeWhenItExists() {
        remove.removeNode(10, 10);
        setUpTree(remove);
        assertValueAndColor(root, false, 60);
        assertValueAndColor(left, true, 40);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, false, 20);
        assertValueAndColor(leftsRightChild, false, 50);
    }

    @Test
    void shouldRemoveBlackNodeWhenItHasRedParentAndBlackSibling() {
        remove.removeNode(10, 10);
        remove.removeNode(50, 50);
        setUpTree(remove);
        assertValueAndColor(root, false, 60);
        assertValueAndColor(left, false, 40);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, true, 20);
    }

    @Test
    void shouldRemoveBlackNodeWhenItHasBlackParentAndRedSibling() {
        remove.putNode(80, 80, 80);
        remove.removeNode(70, 70);
        setUpTree(remove);
        assertValueAndColor(root, false, 40);
        assertValueAndColor(left, false, 20);
        assertValueAndColor(right, true, 60);
        assertValueAndColor(leftsLeftChild, true, 10);
        assertValueAndColor(rightsLeftChild, false, 50);
        assertValueAndColor(rightsRightChild, false, 80);
    }

    @Test
    void shouldRemoveBlackNodeWhenItHasRedParentAndSiblingAndLeftNephew() {
        remove.removeNode(10, 10);
        remove.putNode(30, 30, 30);
        remove.removeNode(50, 50);
        setUpTree(remove);
        assertValueAndColor(root, false, 60);
        assertValueAndColor(left, true, 30);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, false, 20);
        assertValueAndColor(leftsRightChild, false, 40);
        assertNull(rightsLeftChild);
        assertNull(rightsRightChild);
    }

    @Test
    void shouldRemoveBlackNodeWhenItHasRedParentAndSiblingAndRightNephew() {
        remove.removeNode(50, 50);
        setUpTree(remove);
        assertValueAndColor(root, false, 60);
        assertValueAndColor(left, true, 20);
        assertValueAndColor(right, false, 70);
        assertValueAndColor(leftsLeftChild, false, 10);
        assertValueAndColor(leftsRightChild, false, 40);
        assertNull(rightsLeftChild);
        assertNull(rightsRightChild);
    }
}