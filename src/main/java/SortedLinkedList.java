import java.util.Iterator;

/**
 * @Description : TODO
 * @Author : Ellie
 * @Date : 2018/11/7
 */
public class SortedLinkedList<T extends Comparable<T>> {
    Node head = new Node();
    Node curNode = head;


    // 由大到小
    public Node insert(T data) {
        Node cur = head.next;
        Node pre = head;

        while (cur != null && cur.item.compareTo(data) > 0) {
            pre = cur;
            cur = cur.next;
        }
        Node node = new Node(data);
        node.next = cur;
        pre.next = node;

        return node;
    }
    public void insert(Node o) {
        Node cur = head.next;
        Node pre = head;

        while (cur != null && cur.item.compareTo(o.item) > 0) {
            pre = cur;
            cur = cur.next;
        }
        o.next = cur;
        pre.next = o;
    }
    public void insert(SortedLinkedList<T> sortedLinkedList) {
        Node ptr = sortedLinkedList.head.next;
        while (ptr != null) {
            insert(ptr.item);
            ptr = ptr.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = head.next;
        while (node != null) {
            sb.append(node.item);
            sb.append(" ");
            node = node.next;
        }
        return sb.toString();
    }

    class Node implements Comparable<Node> {
        T item;
        Node next;

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

        public int compareTo(Node o) {
            return item.compareTo(o.item);
        }

        @Override
        public String toString() {
            return "Node: " + item;
        }
    }

    public static void main(String[] args){
        SortedLinkedList<String> sll1 = new SortedLinkedList<String>();
        for (String s : "A B C X Y Z D E F G".split(" ")) {
            sll1.insert(s);
        }
        System.out.println("s1: " + sll1);

        SortedLinkedList<String> sll2 = new SortedLinkedList<String>();
        for (String s : "Q X P J K M D A".split(" ")) {
            sll2.insert(s);
        }
        System.out.println("s2: " + sll2);

        sll1.insert(sll2);
        System.out.println("s1+s2: " + sll1);
    }
}
