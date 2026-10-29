/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_Structure;

import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author ADMIN
 */
public class DLLStorage {

    private NodeDLL head;
    private NodeDLL tail;
    private int size;

    public DLLStorage() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public NodeDLL getHead() {
        return head;
    }

    public void setHead(NodeDLL head) {
        this.head = head;
    }

    public NodeDLL getTail() {
        return tail;
    }

    public void setTail(NodeDLL tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //Check empty
    public boolean isEmpty() {
        return head == null;
    }

    public User findUserById(int userID) {
        if (this.isEmpty()) {
            return null;
        }
        NodeDLL current = this.head;
        while (current != null) {
            if (current.getData().getId() == userID) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    //addtail
    public boolean addUser(User user) {
        NodeDLL new_data = new NodeDLL(user);
        if (user == null) {
            return false;
        }
        if (isEmpty()) {
            this.head = new_data;
            this.tail = new_data;
            size++;
            return true;
        }
        if (findUserById(user.getId()) != null) {
            return false;
        }
        tail.setNext(new_data);
        new_data.setPrevious(tail);
        tail = new_data;
        size++;
        return true;
    }

    public boolean removeUser(int userId) {
        NodeDLL current = head;
        if (this.isEmpty()) {
            return false;
        }
        // Tìm nút cần xóa.
        while (current != null) {
            if (current.getData() != null && current.getData().getId() == userId) {
                break;
            }
            current = current.getNext();
        }
        // not found
        if (current == null) {
            return false;
        }

        if (current.getPrevious() == null) {
            head = current.getNext();
        } else {
            current.getPrevious().setNext(current.getNext());
        }
        if (current.getNext() == null) {
            tail = current.getPrevious();
        } else {
            current.getNext().setPrevious(current.getPrevious());
        }
        // Tách nút 
        current.setPrevious(null);
        current.setNext(null);
        size--;
        return true;
    }

   

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();

        NodeDLL current = head;

        while (current != null) {
            User user = current.getData();

            if (user != null) {
                listUser.add(user);
            }

            current = current.getNext();
        }

        return listUser;
    }

}
