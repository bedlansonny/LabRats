import java.io.*;
import java.util.*;
public class Main
{
    public static void main(String args[]) throws IOException
    {
        Scanner in = new Scanner(new File("input.txt"));
        int c = in.nextInt(); //number of test cases
        for(int ci = 0; ci < c; ci++)
        {
            int n = in.nextInt(); //number of cells in the maze
            int e = in.nextInt(); //exit cell number
            int t = in.nextInt(); //time duration
            int m = in.nextInt(); //number of connections in the maze
            Graph maze = new Graph(n);

            for(int mi = 0; mi < m; mi++)
            {
                int a = in.nextInt();
                int b = in.nextInt();
                int weight = in.nextInt();

                maze.addEdge(a, b, weight);
            }

            int escapers = 0;
            for(int ni = 1; ni <= n; ni++)
            {
                int time = maze.pathWeight(ni, e);
                if(time <= t && time != -1)
                    escapers++;
                //System.out.printf("cell %d: %d seconds%n", ni, time);
            }

            System.out.println(escapers + "\n");
        }
    }
}
