/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data_Structure;

import Model.User;

/**
 *
 * @author ADMIN
 */
public class NodeDLL {
    private User data;
    private NodeDLL next;
    private NodeDLL previous;

    public NodeDLL(User data, NodeDLL next, NodeDLL previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public NodeDLL(User data) {
        this.data = data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public void setNext(NodeDLL next) {
        this.next = next;
    }

    public void setPrevious(NodeDLL previous) {
        this.previous = previous;
    }

    public User getData() {
        return data;
    }

    public NodeDLL getNext() {
        return next;
    }

    public NodeDLL getPrevious() {
        return previous;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
    
    
}
