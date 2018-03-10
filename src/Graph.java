import java.util.*;
public class Graph
{
    HashMap<Integer, Node> nodeLookup = new HashMap<>(); //<id, node>

    private Node getNode(int id)
    {
        return nodeLookup.get(id);
    }

    public void addEdge(int start, int end, int weight)
    {
        if(getNode(start)==null)
            nodeLookup.put(start, new Node(start));
        if(getNode(end)==null)
            nodeLookup.put(end, new Node(end));

        Node a = getNode(start);
        Node b = getNode(end);

        a.adjacent.put(b, weight);
    }

    //Dijkstra
    //https://youtu.be/GazC3A4OQTE
    public int pathWeight(int start, int end)
    {
        HashSet<Node> visited = new HashSet<>();
        PriorityQueue<Node> toCheck = new PriorityQueue<>();
        //each node should have: Node prev and int traveled
    }

    static class Node
    {
        int id;
        HashMap<Node, Integer> adjacent = new HashMap<>();  //<node, edge weight>
        private Node(int id)
        {
            this.id = id;
        }
    }
}
