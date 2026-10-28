/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithm;

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

    public void insert(User user) {
        root = insertRec(root, user);
    }

    private NodeBST insertRec(NodeBST node, User user) {
        if (node == null) {
            return new NodeBST(user);
        }

        int currentId = node.getData().getId();
        int newId = user.getId();

        if (newId < currentId) {
            node.setLeft(insertRec(node.getLeft(), user));
        } else if (newId > currentId) {
            node.setRight(insertRec(node.getRight(), user));
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

    public void inOrder() {

    }

    public void findMin() {

    }

    public void findMax() {

    }

    public void height() {

    }

    public void isBalanced() {

    }
}
