
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithm;

import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author Admin
 */
public class BSTSearch {

    private NodeBST root;

    public NodeBST getRoot() {
        return root;
    }

    public void setRoot(NodeBST root) {
        this.root = root;
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    // insert user vao cay BST
    public boolean insert(User user) {
        if (user == null) {
            return false;
        }

        if (search(user.getId()) != null) {
            return false;
        }

        root = insertRec(root, user);
        return true;
    }

    // de quy xu ly insert
    private NodeBST insertRec(NodeBST node, User user) {
        if (node == null) {
            return new NodeBST(user);
        }

        int currentId = node.getData().getId();
        int newId = user.getId();

        if (newId < currentId) {
            node.setLeft(insertRec(node.getLeft(), user)); // insert vao nhanh trai
        } else if (newId > currentId) {
            node.setRight(insertRec(node.getRight(), user)); // insert vao nhanh phai
        }

        return node;
    }

    public void delete(int id) {
        root = deleteRec(root, id);
    }

    // xu ly delete
    private NodeBST deleteRec(NodeBST node, int id) {
        // TH: Cây trong or khong tim thay ID
        if (node == null) {
            return node;
        }

        // tim node delete
        if (id < node.getData().getId()) {
            node.setLeft(deleteRec(node.getLeft(), id));
        } else if (id > node.getData().getId()) {
            node.setRight(deleteRec(node.getRight(), id));
        }
        // Tim thay (id == node.getData().getId())
        else {
            // TH: Node chi co 1 con or khong co con
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            // TH: Node du 2 con
            // Tìm User có ID nhỏ nhất ở nhánh bên phải để thế chỗ
            User minUser = findMinRec(node.getRight());
            node.setData(minUser);

            // Xóa nút minUser ben phai
            node.setRight(deleteRec(node.getRight(), minUser.getId()));
        }

        return node;
    }

    public NodeBST search(int id) {
        return searchRec(root, id);
    }

    // de quy xu ly search
    private NodeBST searchRec(NodeBST node, int id) {
        if (node == null || node.getData().getId() == id) {
            return node;
        }

        if (id < node.getData().getId()) {
            return searchRec(node.getLeft(), id);
        }

        return searchRec(node.getRight(), id);
    }

    // tra ve danh sach user
    public List<User> inOrderList() {
        List<User> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }

    // duyet va them vao list
    private void inOrderRec(NodeBST node, List<User> list) {
        if (node != null) {
            inOrderRec(node.getLeft(), list);   // duyet trai
            list.add(node.getData());           // add node vao list
            inOrderRec(node.getRight(), list);  // duyet phai
        }
    }

    public User findMin() {
        if (isEmpty()) {
            return null;
        }

        return findMinRec(root);
    }

    // find User co ID nho nhat trong mot nhanh cay(dung cho delete va findMin)
    private User findMinRec(NodeBST node) {
        User minVal = node.getData();

        while (node.getLeft() != null) {
            minVal = node.getLeft().getData();
            node = node.getLeft();
        }

        return minVal;
    }

    public User findMax() {
        if (isEmpty()) {
            return null;
        }

        NodeBST node = root;
        User maxVal = node.getData();

        while (node.getRight() != null) {
            maxVal = node.getRight().getData();
            node = node.getRight();
        }

        return maxVal;
    }

    public int height() {
        return heightRec(root);
    }

    private int heightRec(NodeBST node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = heightRec(node.getLeft());
        int rightHeight = heightRec(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalanced() {
        return isBalancedRec(root);
    }

    private boolean isBalancedRec(NodeBST node) {
        if (node == null) {
            return true;
        }

        int leftHeight = heightRec(node.getLeft());
        int rightHeight = heightRec(node.getRight());

        //
        int balanceFactor;
        if (leftHeight > rightHeight) {
            balanceFactor = leftHeight - rightHeight;
        } else {
            balanceFactor = rightHeight - leftHeight;
        }

        if (balanceFactor <= 1
                && isBalancedRec(node.getLeft())
                && isBalancedRec(node.getRight())) {
            return true;
        }

        return false;
    }
}
