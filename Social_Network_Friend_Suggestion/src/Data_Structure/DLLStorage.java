/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_Structure;
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
    
    
}
