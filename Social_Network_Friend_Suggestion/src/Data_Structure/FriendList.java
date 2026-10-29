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

    public void addFriendTail(User data) { // addTail
        if (isEmpty() || isFriend(data.getId())) {
            NodeFriend newNode = new NodeFriend(data);
            head = newNode;
            tail = newNode;
        } else {
            NodeFriend newNode = new NodeFriend(data);
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    public void addFriendHead(User data) { // addhead
        if (isEmpty() || isFriend(data.getId())) {
            NodeFriend newNode = new NodeFriend(data);
            head = newNode;
            tail = newNode;
        } else {
            NodeFriend newNode = new NodeFriend(data);
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    public boolean removeFriend(User data) {
        if (isEmpty() || data == null) {
            return false;
        }

        NodeFriend current = head;

        // Tìm User cần xóa
        while (current != null) {
            if (current.getData().getId() == data.getId()) {
                break;
            }
            current = current.getNext();
        }

        // Không tìm thấy User
        if (current == null) {
            return false;
        }

        // Chỉ có một node
        if (head == tail) {
            head = null;
            tail = null;
        } // Xóa node đầu
        else if (current == head) {
            head = head.getNext();
            head.setPrev(null);
        } // Xóa node cuối
        else if (current == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
        }

        // Xóa node ở giữa
        else {
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }

        size--;
        return true;
    }

    public void traverseHead() { // traverse
        if (isEmpty()) {
            System.out.println("Friend list is empty.");
            return;
        } else {
            NodeFriend current = head;

            while (current != null) {
                System.out.println(current.getData());
                current = current.getNext();
            }
        }
    }

    public void traverseTail() { // traverse tail
        if (isEmpty()) {
            System.out.println("Friend list is empty.");
            return;
        } else {
            NodeFriend current = tail;

            while (current != null) {
                System.out.println(current.getData());
                current = current.getPrev();
            }
        }
    }

    // check if user is friend by userId
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

    // find friend by userId
    public User findFriend(int userId) {
        NodeFriend current = head;

        while (current != null) {
            if (current.getData().getId() == userId) {
                return current.getData();
            }
            current = current.getNext();
        }

        return null;

    }

}
