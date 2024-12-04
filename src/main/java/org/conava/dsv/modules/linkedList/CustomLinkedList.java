package org.conava.dsv.modules.linkedList;

import java.util.Objects;

/**
 * Custom implementation of a singly linked list.
 * Supports adding, removing, checking if a value is present, getting a value by index, and clearing the list.
 * Only supports strings as values.
 * The list has methods that help visualizing it and supports step by step execution of commands as well as undo and redo.
 */
public class CustomLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public CustomLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(String value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void add(int index, String value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {
                tail = newNode;
            }
        }
        size++;
    }

    public void remove(String value) {
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public boolean contains(String value) {
        Node current = head;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public int indexOf(String value) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private static class Node {
        String value;
        Node next;

        Node(String value) {
            this.value = value;
            this.next = null;
        }
    }
}
