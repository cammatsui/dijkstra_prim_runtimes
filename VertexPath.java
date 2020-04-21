public class VertexPath implements Comparable<VertexPath> {
    private int vertex;
    private int prev;
    private double shortestPath;

    public VertexPath(int vertex) {
        this.vertex = vertex;
        this.shortestPath = Double.POSITIVE_INFINITY;
    }

    public int getVertex() {return vertex;}
    public int getPrev() {return prev;}
    public double getPath() {return shortestPath;}
    public void setPrev(int newPrev) {this.prev = newPrev;}
    public void setPath(double newPath) {this.shortestPath = newPath;}

    @Override
    public int compareTo(VertexPath other) {
        if (other.getPath() < shortestPath) {
            return 1;
        } else if (other.getPath() > shortestPath) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {return vertex + " | " + shortestPath + " | " + prev;}


}