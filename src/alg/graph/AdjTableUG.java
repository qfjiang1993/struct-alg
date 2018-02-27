package alg.graph;

import java.io.*;
import java.util.List;

/**
 * 邻接表表示的无向图
 *
 * @author QFJiang on 2017/12/07
 */
public class AdjTableUG {

    private class Vertex {
        char data;

        public Vertex(char data) {
            this.data = data;
        }
    }

    private class Edge {
        Vertex vertex1;
        Vertex vertex2;

        public Edge(Vertex vertex1, Vertex vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
    }

    private Vertex[] vertices;
    private Edge[] edges;
    private List<List<Vertex>> adjTable;

    /**
     * 从文件中读取图数据
     *
     * @param file 图数据文件
     */
    public AdjTableUG(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String[] vertexNodes = line.split(" ");
            vertices = new Vertex[vertexNodes.length];
            for (int i = 0; i < vertexNodes.length; i++) {
                vertices[i] = new Vertex(vertexNodes[i].charAt(0));
            }
            line = reader.readLine();
            String[] edgePair = line.split(" ");
            edges = new Edge[edgePair.length];
            for (int i = 0; i < edgePair.length; i++) {
                String[] edge = edgePair[i].split(",");
                Vertex node1 = new Vertex(edge[0].charAt(0));
                Vertex node2 = new Vertex(edge[1].charAt(0));
                edges[i] = new Edge(node1, node2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
