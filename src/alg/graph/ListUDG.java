package alg.graph;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author QFJiang on 2017/12/07
 */
public class ListUDG {

    // 邻接表中表对应的链表的顶点
    private class ENode {
        int ivex;           // 该边所指向的顶点的位置
        ENode nextEdge;     // 指向下一条弧的指针
    }

    // 邻接表中表的顶点
    private class VNode {
        char data;          // 顶点信息
        ENode firstEdge;    // 指向第一条依附该顶点的弧
    }

    private VNode[] vNodes;  // 顶点数组


    /**
     * 创建图（输入数据）
     */
    public ListUDG() {

        // 输入"顶点数"和"边数"
        System.out.print("input vertex number: ");
        int vLen = readInt();
        System.out.print("input edge number: ");
        int eLen = readInt();
        if (vLen < 1 || eLen < 1 || (eLen > (vLen * (vLen - 1)))) {
            System.out.print("input error: invalid parameters!\n");
            return;
        }

        // 初始化"顶点"
        vNodes = new VNode[vLen];
        for (int i = 0; i < vNodes.length; i++) {
            System.out.printf("vertex(%d): ", i);
            vNodes[i] = new VNode();
            vNodes[i].data = readChar();
            vNodes[i].firstEdge = null;
        }

        // 初始化"边"
        for (int i = 0; i < eLen; i++) {
            // 读取边的起始顶点和结束顶点
            System.out.printf("edge(%d):", i);
            char c1 = readChar();
            char c2 = readChar();
            int p1 = getPosition(c1);
            int p2 = getPosition(c2);
            // 初始化node1
            ENode node1 = new ENode();
            node1.ivex = p2;
            // 将node1链接到"p1所在链表的末尾"
            if (vNodes[p1].firstEdge == null)
                vNodes[p1].firstEdge = node1;
            else
                linkLast(vNodes[p1].firstEdge, node1);
            // 初始化node2
            ENode node2 = new ENode();
            node2.ivex = p1;
            // 将node2链接到"p2所在链表的末尾"
            if (vNodes[p2].firstEdge == null)
                vNodes[p2].firstEdge = node2;
            else
                linkLast(vNodes[p2].firstEdge, node2);
        }
    }

    /**
     * 创建图（提供顶点与边）
     *
     * @param nodes 顶点数组
     * @param edges 边数组
     */
    public ListUDG(char[] nodes, char[][] edges) {

        // 初始化"顶点数"和"边数"
        int vLen = nodes.length;
        int eLen = edges.length;

        // 初始化"顶点"
        vNodes = new VNode[vLen];
        for (int i = 0; i < vNodes.length; i++) {
            vNodes[i] = new VNode();
            vNodes[i].data = nodes[i];
            vNodes[i].firstEdge = null;
        }

        // 初始化"边"
        for (char[] edge : edges) {
            // 读取边的起始顶点和结束顶点
            int p1 = getPosition(edge[0]);
            int p2 = getPosition(edge[1]);

            // 初始化node1
            ENode node1 = new ENode();
            node1.ivex = p2;
            // 将node1链接到"p1所在链表的末尾"
            if (vNodes[p1].firstEdge == null)
                vNodes[p1].firstEdge = node1;
            else
                linkLast(vNodes[p1].firstEdge, node1);
            // 初始化node2
            ENode node2 = new ENode();
            node2.ivex = p1;
            // 将node2链接到"p2所在链表的末尾"
            if (vNodes[p2].firstEdge == null)
                vNodes[p2].firstEdge = node2;
            else
                linkLast(vNodes[p2].firstEdge, node2);
        }
    }

    /*
     * 将node节点链接到list的最后
     */
    private void linkLast(ENode list, ENode node) {
        ENode p = list;
        while (p.nextEdge != null)
            p = p.nextEdge;
        p.nextEdge = node;
    }

    /*
     * 返回ch位置
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vNodes.length; i++)
            if (vNodes[i].data == ch)
                return i;
        return -1;
    }

    /*
     * 读取一个输入字符
     */
    private char readChar() {
        char ch = '0';
        do {
            try {
                ch = (char) System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')));

        return ch;
    }

    /*
     * 读取一个输入字符
     */
    private int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /*
     * 深度优先搜索遍历图的递归实现
     */
    private void DFS(int i, boolean[] visited) {
        ENode node;
        visited[i] = true;
        System.out.printf("%c ", vNodes[i].data);
        node = vNodes[i].firstEdge;
        while (node != null) {
            if (!visited[node.ivex])
                DFS(node.ivex, visited);
            node = node.nextEdge;
        }
    }

    /*
     * 深度优先搜索（类似于树的先序遍历）
     */
    public void DFS() {
        boolean[] visited = new boolean[vNodes.length];  // 顶点访问标记

        // 初始化所有顶点都没有被访问
        for (int i = 0; i < vNodes.length; i++)
            visited[i] = false;

        System.out.print("DFS: ");
        for (int i = 0; i < vNodes.length; i++) {
            if (!visited[i])
                DFS(i, visited);
        }
        System.out.println();
    }

    /*
     * 广度优先搜索（类似于树的层次遍历）
     */
    public void BFS() {
        int head = 0;
        int rear = 0;
        int[] queue = new int[vNodes.length];            // 辅组队列
        boolean[] visited = new boolean[vNodes.length];  // 顶点访问标记

        for (int i = 0; i < vNodes.length; i++)
            visited[i] = false;

        System.out.print("BFS: ");
        for (int i = 0; i < vNodes.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", vNodes[i].data);
                queue[rear++] = i;      // 入队列
            }

            while (head != rear) {
                int j = queue[head++];  // 出队列
                ENode node = vNodes[j].firstEdge;
                while (node != null) {
                    int k = node.ivex;
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.printf("%c ", vNodes[k].data);
                        queue[rear++] = k;
                    }
                    node = node.nextEdge;
                }
            }
        }
        System.out.println();
    }

    /*
     * 打印矩阵队列图
     */
    public void print() {
        System.out.println("List Graph:");
        for (int i = 0; i < vNodes.length; i++) {
            System.out.printf("%d(%c): ", i, vNodes[i].data);
            ENode node = vNodes[i].firstEdge;
            while (node != null) {
                System.out.printf("%d(%c) ", node.ivex, vNodes[node.ivex].data);
                node = node.nextEdge;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[] nodes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{{'A', 'C'}, {'A', 'D'}, {'A', 'F'},
                {'B', 'C'}, {'C', 'D'}, {'E', 'G'}, {'F', 'G'}};
        ListUDG pG;

        // 自定义"图"(输入矩阵队列)
        //pG = new ListUDG();
        // 采用已有的"图"
        pG = new ListUDG(nodes, edges);

        pG.print();   // 打印图
        pG.DFS();     // 深度优先遍历
        pG.BFS();     // 广度优先遍历
    }
}
