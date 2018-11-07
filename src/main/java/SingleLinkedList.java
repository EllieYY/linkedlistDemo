import java.security.Signature;

/**
 * @Description : TODO
 * @Author : Ellie
 * @Date : 2018/11/7
 */
public class SingleLinkedList<T> {
    private int size;
    private Node head = new Node();

    public Node addFirst(T data) {
        Node node = new Node(data);
        node.next = head.next;
        head.next = node;
        size++;
        return node;
    }

    public Node addLast(T data) {
        Node tail = getTail();
        Node node = new Node(data);
        node.next = tail.next;
        tail.next = node;
        return node;
    }

    public Node getTail() {
        Node tail = head;
        Node entry = getEntryNodeOfLoop();
        if (entry != null) {
            return entry;
        }

        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }

    public Node find(T data) {
        Node ptr = head.next;
        while (ptr != null) {
            if (ptr.item.equals(data)) {
                return ptr;
            }
            ptr = ptr.next;
        }
        return null;
    }

    // 递归反转
    public void reversal() {
        head.next = reversal(head.next);
    }

    // 链表反转
    public Node reversal(Node nextNode) {
        if (nextNode == null || nextNode.next == null) {
            return nextNode;
        }
        Node newHead = reversal(nextNode.next);
        nextNode.next.next = nextNode;
        nextNode.next = null;
        return newHead;
    }

    // 环的检测，入口节点非空表示有环
    public Node getEntryNodeOfLoop() {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node fastNode = head.next;
        Node slowNode = head.next;
        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
            if (fastNode == slowNode) {
                slowNode = head.next;
                while (slowNode != fastNode) {
                    slowNode = slowNode.next;
                    fastNode = fastNode.next;
                }
                return slowNode;
            }
        }
        return null;
    }

    // 删除链表的倒数第N个节点
    public Node removeFromEnd(int n) {
        Node pHead = head.next;
        Node pTail = pHead;
        for (int i = 0; i < n; i++) {
            pTail = pTail.next;   // 给定的N可以保证该操作有效
        }
        while (pTail.next != null) {
            pHead = pHead.next;
            pTail = pTail.next;
        }

        Node target = pHead.next;
        pHead.next = pHead.next.next;

        return target;
    }
    public Node removeMidNode() {
        Node firstNode = head.next;
        if (firstNode == null || firstNode.next == null) {
            head.next = null;
            return head;
        }
        Node slow = firstNode;
        Node fast = slow.next.next;
        while(fast.next.next != null && slow.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node target = slow.next;
        slow.next = slow.next.next;
        return target;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node node = head;
        while(node != null) {
            sb.append(node.item);
            sb.append(" ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // 链表节点
    private class Node {
        private T item;
        private Node next;

        Node() {
            item = null;
            next = null;
        }
        Node(T data) {
            item = data;
            next = null;
        }
        Node(T data, Node next) {
            this.item = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node: " + item;
        }
    }

    public static void main(String[] args){
        System.out.println(" ---- Test reversal of linkedlist ----");
        SingleLinkedList<String> ssl = new SingleLinkedList<String>();
        for (String s : "A B C D E F G".split(" ")) {
            ssl.addLast(s);
        }

        System.out.println("source: " + ssl);
        ssl.reversal();
        System.out.println("reversal: " + ssl);

        System.out.println(" ---- remove node from end ----");
        ssl.removeFromEnd(3);
        System.out.println("remove the 3rd node from the end: " + ssl);

        System.out.println(" --- remove mid node -----");
        ssl.removeMidNode();
        System.out.println("remove mid node: " + ssl);

        System.out.println(" ---- Check loop in linkedlist ----");
        SingleLinkedList<String> loopList = new SingleLinkedList<String>();
        for (String s : "A B C D E F G H I J K".split(" ")) {
            loopList.addLast(s);
        }

        System.out.println("Set Loop in D");
        loopList.getTail().next = loopList.find("D");

        System.out.println("entry node of loop: " + loopList.getEntryNodeOfLoop());

    }
}
