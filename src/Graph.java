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
        Node source = getNode(start);
        Node exit = getNode(end);

        source.traveled = 0;

        PriorityQueue<Node> toCheck = new PriorityQueue<>(); //order to check children
        toCheck.add(source);
        Node current;
        while(toCheck.size() > 0)
        {
            current = toCheck.poll();
            for(Node node : current.adjacent.keySet())
            {
                if(!current.visited.contains(node) && node.traveled > current.traveled + current.adjacent.get(node))
                {
                    current.visited.add(node);
                    node.visited.add(current);

                    node.prev = current;
                    node.traveled = current.traveled + current.adjacent.get(node);
                    toCheck.add(node);
                }
                if(current.id == exit.id)
                    break;
            }
        }
        if(exit.traveled == Integer.MAX_VALUE)
            return -1;
        return exit.traveled;

    }

    public void resetVals()
    {
        for(Map.Entry<Integer, Node> entry : nodeLookup.entrySet())
        {
            entry.getValue().prev = null;
            entry.getValue().traveled = Integer.MAX_VALUE;
            entry.getValue().visited = new HashSet<>();
        }
    }

    static class Node implements Comparable<Node>
    {
        int id;
        HashMap<Node, Integer> adjacent = new HashMap<>();  //<node, edge weight>

        Node prev;
        int traveled = Integer.MAX_VALUE;
        HashSet<Node> visited = new HashSet<>();

        private Node(int id)
        {
            this.id = id;
        }

        public int compareTo(Node otherNode)
        {
            return this.traveled - otherNode.traveled;
        }
    }
}
