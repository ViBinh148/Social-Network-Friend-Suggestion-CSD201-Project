/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithm;

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

<<<<<<< Updated upstream
    public void insert() {

=======
    public boolean insert(User user) {
        if (user == null) {
            return false;
        }

        if (search(user.getId()) != null) {
            return false;
        }

        root = insertRec(root, user);
        return true;
>>>>>>> Stashed changes
    }

    public void delete() {

    }

    public void search() {

    }

    public void inOrder() {

    }
    public void findMin() {
        
    }
<<<<<<< Updated upstream
    public void findMax() {
        
=======

    // de quy xu ly search
    private NodeBST searchRec(NodeBST node, int id) {
        // Khong tim thay neu da di het nhanh cay.
        if (node == null) {
            return null;
        }

        int currentId = node.getData().getId();

        // Tim thay nguoi dung.
        if (id == currentId) {
            return node;
        }
        if (id < node.getData().getId()) {
            return searchRec(node.getLeft(), id);
        }
        return searchRec(node.getRight(), id);
>>>>>>> Stashed changes
    }
    public void height() {
        
    }
    public void isBalanced() {
        
    }
}
