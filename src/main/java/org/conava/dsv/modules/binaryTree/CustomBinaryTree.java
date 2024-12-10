package org.conava.dsv.modules.binaryTree;

/**
 * The CustomBinaryTree class is a custom implementation of a binary tree for easy visualization.
 */
public class CustomBinaryTree {

    private Node root;
    private int size;

    public CustomBinaryTree() {
        root = null;
        size = 0;
    }

    public void add(String value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, String value) {
        if (current == null) {
            size++;
            return new Node(value);
        }
        int cmp = value.compareTo(current.value);
        if (cmp < 0) {
            current.left = addRecursive(current.left, value);
        } else if (cmp > 0) {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    public void remove(String value) {
        if (contains(value)) {
            root = removeRecursive(root, value);
            size--;
        }
    }

    private Node removeRecursive(Node current, String value) {
        if (current == null) {
            return null;
        }

        int cmp = value.compareTo(current.value);
        if (cmp < 0) {
            current.left = removeRecursive(current.left, value);
        } else if (cmp > 0) {
            current.right = removeRecursive(current.right, value);
        } else {
            // Node found
            if (current.left == null && current.right == null) {
                return null;
            } else if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } else {
                // Two children
                String smallestValue = findMin(current.right);
                current.value = smallestValue;
                current.right = removeRecursive(current.right, smallestValue);
            }
        }
        return current;
    }

    private String findMin(Node current) {
        return current.left == null ? current.value : findMin(current.left);
    }

    public boolean contains(String value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node current, String value) {
        if (current == null) {
            return false;
        }
        int cmp = value.compareTo(current.value);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return containsRecursive(current.left, value);
        } else {
            return containsRecursive(current.right, value);
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public Node getRoot() {
        return root;
    }

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }
}
