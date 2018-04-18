import java.util.*;
public class Graph
{
    HashMap<Integer, Node> nodeLookup = new HashMap<>(); //<id, node>

    public Graph(int size)
    {
        for(int i = 1; i <= size; i++)
        {
            nodeLookup.put(i, new Node(i));
        }
    }

    private Node getNode(int id)
    {
        return nodeLookup.get(id);
    }

    public void addEdge(int start, int end, int weight)
    {
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

        for(Map.Entry<Integer, Node> entry : nodeLookup.entrySet())
        {
            //entry.getValue().prev = null;
            //System.out.println(entry.getValue().traveled);
            entry.getValue().traveled = Integer.MAX_VALUE;
        }
        source.traveled = 0;

        HashSet<Node> visited = new HashSet<>();
        PriorityQueue<Node> toCheck = new PriorityQueue<>(); //order to check children
        toCheck.add(source);
        while(toCheck.size() > 0)
        {
            Node current = toCheck.poll();
            visited.add(current);
            for(Node node : current.adjacent.keySet())
            {
                if(!visited.contains(node) && node.traveled > current.traveled + current.adjacent.get(node))
                {
                    //node.prev = current;
                    node.traveled = current.traveled + current.adjacent.get(node);
                    toCheck.add(node);
                }
                /*faster processing to cut off here, but may not be fastest path
                if(current.id == exit.id)
                {
                    break;
                }
                */
            }
        }
        if(exit.traveled == Integer.MAX_VALUE)
            return -1;
        return exit.traveled;
    }

    static class Node implements Comparable<Node>
    {
        int id;
        HashMap<Node, Integer> adjacent = new HashMap<>();  //<node, edge weight>

        //Node prev;
        int traveled = Integer.MAX_VALUE;

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
