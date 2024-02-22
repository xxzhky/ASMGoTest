package com.ledboot.main.leet;

import javax.swing.tree.TreeNode;

/**
 * @author: Fred
 * @date: 2024/2/21 18:17
 * @description: (类的描述)
 */
public class BSTValidator {
    public boolean solution(Node root) {
        // Write your code here

        return isValidBST(root);
    }

    public boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(Node root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.value <= min || root.value >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.value) && isValidBST(root.right, root.value, max);

    }

    public static void main(String[] args) {
        BSTValidator validator = new BSTValidator();

        Node root = new Node(10);
        Node left = new Node(5);
        Node right = new Node(15);

        root.left = left;
        root.right = right;

        left.left = new Node(2);
        left.right = new Node(6);

        System.out.println(validator.isValidBST(root));


        Node root2 = new Node(5);
        Node left2 = new Node(1);
        Node right2 = new Node(4);
        root2.left = left2;
        root2.right = right2;

        root2.right.left = new Node(3);
        root2.right.right = new Node(6);
        System.out.println(validator.isValidBST(root2));

    }


}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
