package data_Structure;

import model.User;

public class FriendList {
    private NodeFriend head;
    private NodeFriend tail;
    private int size;

    public FriendList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public NodeFriend getHead() {
        return head;
    }

    public void setHead(NodeFriend head) {
        this.head = head;
    }

    public NodeFriend getTail() {
        return tail;
    }

    public void setTail(NodeFriend tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFriendTail(User data) { //addTail
        if(isEmpty()) {
            NodeFriend newNode = new NodeFriend(data);
            head = newNode;
            tail = newNode;
        } else {
            NodeFriend newNode = new NodeFriend(data);
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
    }

    public void addFriendHead(User data) { //addhead
        if(isEmpty()) {
            NodeFriend newNode = new NodeFriend(data);
            head = newNode;
            tail = newNode;
        } else {
            NodeFriend newNode = new NodeFriend(data);
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    public void removeFriend(User data) {

    }

    public void traverse() {
        
    }

    public void traverseReverse() {

    }

    public boolean isFriend(int userId) {
        NodeFriend current = head;

        while (current != null) {
            if (current.getData().getId() == userId) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    public User findFriend(int userId) {
        return null;
    }

}
