package com.lev1.datastructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(3, "张三");
        LinkedNode node2 = new LinkedNode(4, "李四");
        LinkedNode node3 = new LinkedNode(5, "王五");
        LinkedNode node4 = new LinkedNode(6, "马六");
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(node3);
        list.addByOrder(node2);
        list.addByOrder(node1);
        list.addByOrder(node4);
        LinkedNode node5 = new LinkedNode(2, "张三");
        LinkedNode node6 = new LinkedNode(7, "李四");
        LinkedNode node7 = new LinkedNode(9, "王五");
        LinkedNode node8 = new LinkedNode(8, "马六");
        SingleLinkedList list1 = new SingleLinkedList();
        list1.addByOrder(node6);
        list1.addByOrder(node5);
        list1.addByOrder(node8);
        list1.addByOrder(node7);
//        list.add(node1);
//        list.add(node2);
//        list.add(node3);
//        list.add(node4);
//        list.del(6);
//        list.modify(new LinkedNode(3, "小张张"));
//        System.out.println("单链表的节点个数为：" + list.getLength());
//        System.out.println("单链表倒数第1个节点为：" + list.findLastIndexNode(4));
//        System.out.println("链表遍历结果：");
//        list.list();
//        System.out.println("逆序打印单链表：");
//        list.listByReversedOrder();
//        list.reverse();
//        System.out.println("反转后的链表为：");
//        list.list();
        System.out.println("合并前的两个单链表:");
        list.list();
        System.out.println("===========分割线============");
        list1.list();
        System.out.println("两个有序链表合并后的单链表：");
        SingleLinkedList list2 = SingleLinkedList.merge(list,list1);
        list2.list();
    }
}


class SingleLinkedList {
    private LinkedNode head = null;

    // 无参构造，初始化头结点
    public SingleLinkedList() {
        head = new LinkedNode(0, "");
    }

    // 向链表中插入元素
    // 不考虑对象排序，每次插入到链表末尾
    // 1.找到当前链表的最后节点
    // 2.将链表最后节点的next域指向将要插入的节点
    public void add(LinkedNode node) {
        // head节点不能动，需要一个辅助遍历temp
        LinkedNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 没有到最后，将temp后移
            temp = temp.next;
        }
        temp.next = node;
    }

    // 向单链表中按照id顺序添加元素
    // 1.遍历单链表
    // 2.找到存放位置的下一个位置
    // 3.将节点插入到链表中
    public void addByOrder(LinkedNode node) {
        // 使用辅助节点
        LinkedNode temp = head;
        // 判断是否有该节点，有则不插入，没有则插入
        boolean flag = false;
        // 遍历链表，节点插入的位置
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.getId() > node.getId()) {
                break;
            } else if (temp.next.getId() == node.getId()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("节点已存在~~");
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    // 从链表中删除元素
    // 1.先判断链表是否为空，为空的话直接返回
    // 2.遍历链表，直到找到该节点temp.next，然后将temp.next指向temp.next.next
    // 3.遍历结束后还未找到该节点，则直接返回
    public void del(int nodeId) {
        // 判断链表是否为空，链表为空直接返回
        if (isEmpty()) {
            System.out.println("链表为空~~");
            return;
        }
        // 辅助节点
        LinkedNode temp = head;
        // 遍历链表
        while (true) {
            // 链表遍历结束时还未找到该节点
            if (temp.next == null) {
                System.out.println("未找到需要删除的节点~~");
                break;
            }
            // 找到该节点，将temp的next域指向next的next
            if (temp.next.getId() == nodeId) {
                temp.next = temp.next.next;
                break;
            }
            // 移动temp位置，进行遍历
            temp = temp.next;
        }
    }

    // 通过id来修改链表中的元素
    // 1.通过id找到需要修改的节点
    // 2.修改节点中数据
    // TODO 没有考虑nodeId在链表中存在多个的情况，目前是删除找到的第一个
    public void modify(LinkedNode node) {
        // 判断链表是否为空，链表为空直接返回
        if (isEmpty()) {
            System.out.println("链表为空~~");
            return;
        }
        // 辅助节点
        LinkedNode temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("未找到需要修改的节点~~");
                break;
            }
            // 找到需要修改的节点，修改节点参数
            if (temp.getId() == node.getId()) {
                temp.setName(node.getName());
                break;
            }
            // 移动节点
            temp = temp.next;
        }
    }

    // 遍历单链表，得到单链表中节点个数
    public int getLength() {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return 0;
        }
        int count = 0;
        // 辅助节点
        LinkedNode temp = head.next;
        // 遍历单链表
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    // 查找单链表中的倒数第K个节点
    // 1.获取当前链表长度
    // 2.通过倒数的index计算出是正序第几个节点
    // 3.正序遍历链表，得到目标节点
    public LinkedNode findLastIndexNode(int index) {
        if (isEmpty()) {
            System.out.println("当前链表为空~~");
            return null;
        }
        // 记录当前链表长度
        int length = getLength();
        if (index <= 0 || index > length) {
            return null;
        }
        // 辅助链表
        LinkedNode temp = head.next;
        // 正序遍历链表，得到目标节点
        for (int i = 0; i < length - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // 单链表反转
    // 1.需要两个辅助节点，一个节点为剩余节点，一个节点为新的反转节点
    public void reverse() {
        // 链表为空或者只有一个元素时，无须反转
        if (isEmpty() && head.next.next == null) {
            return;
        }
        LinkedNode cur = null;
        LinkedNode temp = head.next;
        LinkedNode result = null;
        // 遍历节点，知道temp为空
        while (temp != null) {
            // 保存temp的next节点
            cur = temp.next;
            // 将temp的next节点指向目标节点，初始值为null
            temp.next = result;
            // 将temp赋值给目标节点
            result = temp;
            // 实际上就是(temp = temp.next)，因为之前temp与next节点断开连接，所以重新给temp赋值
            temp = cur;
        }
        // 将头结点指向目标节点
        head.next = result;
    }

    // 逆序遍历单链表，但不改变单链表的结构
    // 使用stack(栈===>先进先出)的数据结构，先遍历单链表，然后将链表中元素压入栈中
    // 遍历stack，依次取出栈中的元素
    public void listByReversedOrder() {
        LinkedNode temp = head.next;
        Stack<LinkedNode> stack = new Stack<>();
        while (temp != null) {
            stack.add(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    // 合并两个有序单链表，使得合并后的单链表依然有序
    //
    public static SingleLinkedList merge(SingleLinkedList list1, SingleLinkedList list2) {
        SingleLinkedList list = new SingleLinkedList();
        LinkedNode temp1 = list1.getHead().next;
        LinkedNode temp2 = list2.getHead().next;
        LinkedNode tmp = null;
        while (temp1 != null && temp2 != null) {
            if (temp1.getId() <= temp2.getId()) {
                tmp = temp1.next;
                temp1.next = null;
                list.add(temp1);
                temp1 = tmp;
            } else {
                tmp = temp2.next;
                temp2.next = null;
                list.add(temp2);
                temp2 = tmp;
            }
        }

        while (temp1 != null) {
            tmp = temp1.next;
            temp1.next = null;
            list.add(temp1);
            temp1 = tmp;
        }

        while (temp2 != null) {
            tmp = temp2.next;
            temp2.next = null;
            list.add(temp2);
            temp2 = tmp;
        }
        return list;
    }

    // 打印链表
    public void list() {
        // 判断链表是否为空，链表为空直接返回
        if (isEmpty()) {
            System.out.println("链表为空~~");
            return;
        }
        // 使用辅助节点temp
        LinkedNode node = head.next;
        while (true) {
            if (node == null) {
                break;
            }
            // 遍历打印节点
            System.out.println(node);
            // 打印之后将节点指向下一节点
            node = node.next;
        }
    }

    // 获取当前链表头结点
    public LinkedNode getHead() {
        return head;
    }

    // 判断当前链表是否为空
    public boolean isEmpty() {
        return head.next == null ? true : false;
    }

}

class LinkedNode {
    private int id;
    private String name;
    public LinkedNode next;// 指向下一节点

    public LinkedNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LinkedNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}