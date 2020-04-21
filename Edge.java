public class Edge {

    private double weight;
    private int fromVertex;
    private int toVertex;

    public Edge(int from, int to, double w) {
        this.toVertex = to;
        this.fromVertex = from;
        this.weight = w;
    }

    public boolean equals(Edge e) {
        if (e.getTo() == toVertex && e.getFrom() == fromVertex && e.getWeight() == weight) {
            return true;
        }
        return false;
    }

    public int getTo() {return this.toVertex;}
    public int getFrom() {return this.fromVertex;}
    public double getWeight() {return this.weight;}
}