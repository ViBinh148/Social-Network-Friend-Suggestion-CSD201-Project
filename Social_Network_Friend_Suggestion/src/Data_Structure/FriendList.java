package Data_Structure;

import Model.User;

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
    //is empty
        public boolean isEmpty() {
        if (this.head == null && this.tail == null) {
            return true;
        } else {
            return false;
        }
    }

    //Add
    public void addFriend(User data) {
        NodeFriend newNode = new NodeFriend(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

}
