package com.map;

public class RedBlackTree<K extends Comparable<K>, V> extends AbstractBucket<K, V> {
    public TreeNode<K, V> root;
    private int size;

    public int size() {
        return size;
    }

    private boolean isRed(TreeNode<K, V> node) {
        return node != null && node.red;
    }

    private int getIndex(TreeNode<K, V> node) {
        if (node == null || node.parent == null) {
            return -1;
        }
        return node == node.parent.link[0] ? 0 : 1;
    }

    private int invertDir(int dir) {
        return dir == 1 ? 0 : 1;
    }

    private void insertRotate(TreeNode<K, V> node) {
        TreeNode<K, V> parent = node.parent;
        TreeNode<K, V> grandparent;
        if (!isRed(parent) || (grandparent = parent.parent) == null) {
            return;
        }
        parent = rotate(node, parent, grandparent);
        colorSwap(parent, grandparent);
    }

    private TreeNode<K, V> rotate(TreeNode<K, V> node, TreeNode<K, V> parent, TreeNode<K, V> grandparent) {
        int nodeIndex = getIndex(node);
        int parentIndex = getIndex(parent);
        if (nodeIndex != parentIndex) {
            parent = singleRotation(parent, nodeIndex);
        }
        singleRotation(grandparent, parentIndex);
        return parent;
    }

    private TreeNode<K, V> singleRotation(TreeNode<K, V> root, int dir) {
        int rotationDir = invertDir(dir);
        TreeNode<K, V> pivot = root.link[dir];
        root.link[dir] = pivot.link[rotationDir];
        if (pivot.link[rotationDir] != null) {
            pivot.link[rotationDir].parent = root;
        }
        pivot.link[rotationDir] = root;

        pivot.parent = root.parent;
        if (root.parent == null) {
            this.root = pivot;
        } else {
            int index = root.parent.link[0] == root ? 0 : 1;
            root.parent.link[index] = pivot;
        }
        root.parent = pivot;
        return pivot;
    }

    private void colorSwap(TreeNode<K, V> node, TreeNode<K, V> parent) {
        boolean red = node.red;
        node.red = parent.red;
        parent.red = red;
    }

    private void colorFlip(TreeNode<K, V> node, boolean rootRed) {
        node.red = rootRed && this.root != node;
        node.link[0].red = !rootRed;
        node.link[1].red = !rootRed;
    }

    public boolean contains(V value) {
        return false;
    }

    @Override
    public V putNode(K key, V value, int hash) {
        if (this.root == null) {
            this.root = new TreeNode<>(key, value, hash, false);
            size = 1;
            return value;
        }

        TreeNode<K, V> node = root;
        TreeNode<K, V> x = new TreeNode<>(key, value, hash);
        while (true) {
            if (!isRed(node) && isRed(node.link[0]) && isRed(node.link[1])) {
                colorFlip(node, true);
                insertRotate(node);
            }

            int cmp = Integer.compare(hash, node.hash);
            int dir = cmp == 0 ? key.compareTo(node.key) : Math.max(0, cmp);
            if (dir == 0) {
                node.value = value;
                return value;
            }
            TreeNode<K, V> child = node.link[dir];
            x.parent = node;
            if (child == null) {
                node.link[dir] = x;
                insertRotate(x);
                break;
            }
            node = child;
        }
        size++;
        return value;
    }

    public V getValue(K key, V value, int hash) {
        return null;
    }

    public void remove(K key, V value, int hash) {
        TreeNode<K, V> node = null, child = root, y = null;
        while (child != null) {
            node = child;
            if (node.value == value) {
                y = node;
            }
            int cmp = Integer.compare(hash, node.hash);
            int dir = Math.max(0, cmp);
            child = node.link[dir];

            // check if there is double black
            if (isRed(node) || isRed(child)) {
                continue;
            }

            // case2
            int nDir = invertDir(dir);
            TreeNode<K, V> oppositeChild = node.link[nDir];
            if (isRed(oppositeChild)) {
                singleRotation(node, nDir);
                colorSwap(node, oppositeChild);
                continue;
            }

            TreeNode<K, V> parent = node.parent;
            int siblingDir = invertDir(getIndex(node));
            TreeNode<K, V> sibling;
            if (node.parent == null || (sibling = parent.link[siblingDir]) == null) {
                continue;
            }

            // case1
            int nSiblingDir = invertDir(siblingDir);
            TreeNode<K, V> nephew = sibling.link[siblingDir];
            TreeNode<K, V> oppositeNephew = sibling.link[nSiblingDir];
            if (!isRed(nephew) && !isRed(oppositeNephew)) {
                colorFlip(parent, false);
                continue;
            }

            // case3 and case4
            TreeNode<K, V> grandparent = parent.parent;
            if (grandparent == null) {
                continue;
            }
            boolean isNephewRed = isRed(nephew);
            rotate(isNephewRed ? nephew : oppositeNephew, sibling, parent);
            if (isNephewRed) {
                colorSwap(sibling, nephew);
            }
            colorSwap(node, parent);
        }

        // empty tree or no node
        if (y == null) {
            return;
        }

        // only one node,
        // node.parent == null instead of node == root to avoid warning -
        // does the same thing but assures parent isn't null
        size--;
        if (node.parent == null) {
            this.root = null;
            return;
        }

        y.value = node.value;
        TreeNode<K, V> link = node.link[node.link[0] == null ? 1 : 0];
        node.parent.link[getIndex(node)] = link;
        node.parent = null;
    }
}
