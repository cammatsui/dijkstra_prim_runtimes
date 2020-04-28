
public class TestDijkstra {
    public static void main(String[] args) {
        /*Graph g = new ListGraph(8, true);
        g.addEdge(0,1,8);
        g.addEdge(0,2,6);
        g.addEdge(0,3,1);

        g.addEdge(1,3,1);
        g.addEdge(1,5,4);
        g.addEdge(1,6,4);

        g.addEdge(2,4,6);
        g.addEdge(2,5,7);
        
        g.addEdge(3,5,3);
        g.addEdge(3,7,4);

        g.addEdge(4,7,5);

        g.addEdge(5,6,9);
        g.addEdge(5,7,9);

        g.addEdge(6,7,5);

        //g.print()
        Graph primRes = g.prim2(0);
        primRes.print();*/

        /*Graph g = new ListGraph(8, true);
        g.addEdge(0,2,5);
        g.addEdge(0,3,9);
        g.addEdge(0,4,9);

        g.addEdge(1,3,9);
        g.addEdge(1,5,4);

        g.addEdge(2,4,3);
        g.addEdge(2,6,2);

        g.addEdge(3,5,6);
        g.addEdge(3,7,4);

        g.addEdge(4,6,6);
        g.addEdge(4,7,9);

        g.addEdge(5,6,9);

        g.print();

        VertexPath[] dijkstraTable = g.dijkstra(0);
        for (int i = 0; i < 8; i++) {
            System.out.println(dijkstraTable[i]);
        }*/
        
        Graph g = new MatrixGraph(8);

        g.addEdge(0,1,3);
        g.addEdge(0,2,3);
        g.addEdge(0,4,9);

        g.addEdge(1,0,4);

        g.addEdge(2,0,7);
        g.addEdge(2,6,3);

        g.addEdge(3,1,2);
        g.addEdge(3,5,7);
        g.addEdge(3,7,5);

        g.addEdge(4,2,1);
        g.addEdge(4,7,5);
        
        g.addEdge(5,6,4);
        g.addEdge(5,7,9);

        g.addEdge(6,5,8);
        
        g.addEdge(7,5,8);

        VertexPath[] dijkstraTable = g.dijkstra(0);
        for (int i = 0; i < 8; i++) {
            System.out.println(dijkstraTable[i]);
        }

    }
}