public class TestGraph {
    public static void main(String[] args) {
        Graph g = new ListGraph(8, true);
        
        g.addEdge(0,1,9);
        g.addEdge(0,3,2);
        g.addEdge(0,4,1);

        g.addEdge(1,2,4);
        g.addEdge(1,3,4);
        g.addEdge(1,6,9);
        
        g.addEdge(2,4,8);

        g.addEdge(3,5,3);
        g.addEdge(3,7,2);

        g.addEdge(4,6,8);
        
        g.addEdge(6,7,8);


        //g.print();

        Graph r = Graph.randUndConGraph(10);
        r.print();

        /*VertexPath[] dijkstraTable = g.dijkstra(2);
        for (int i = 0; i < dijkstraTable.length; i++) {
            System.out.println(dijkstraTable[i]);
        }*/
        //Graph p = g.prim(0);
        //p.print();
    }
}