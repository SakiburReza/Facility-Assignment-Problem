package hungarian;

//Online Java Compiler
//Use this editor to write, compile and run your Java code online

import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Graph {
 int vertex;
 int graph[][];

 public Graph(int n) {
     this.vertex = n;
     graph = new int[vertex][vertex];
     for (int i = 0; i < vertex; i++) {
         for (int j = 0; j < vertex; j++) {
             graph[i][j] = -1;
         }
     }
 }

 void addEdge(int n1, int n2, int w) {
     graph[n1][n2] = w;
     graph[n2][n1] = w;
 }

 int minimumDistance(int distance[], Boolean spSet[]) {
     // Initialize min value
     int m = Integer.MAX_VALUE, m_index = -1;

     for (int vx = 0; vx < vertex; vx++) {
         if (spSet[vx] == false && distance[vx] <= m) {
             m = distance[vx];
             m_index = vx;
         }
     }
     return m_index;

 }

 // A utility method to display the built distance array
 void printSolution(int distance[], int n) {
     System.out.println("The shortest Distance from source 0th node to all other nodes are: ");
     for (int j = 0; j < n; j++)
         System.out.println("To " + j + " the shortest distance is: " + distance[j]);
 }

 int[] dijkstra(int s) {
     int distance[] = new int[vertex];
     Boolean spSet[] = new Boolean[vertex];

     // Initializing all of the distances as INFINITE
     // and spSet[] as false
     for (int j = 0; j < vertex; j++) {
         distance[j] = Integer.MAX_VALUE;
         spSet[j] = false;
     }

     // Distance from the source vertex to itself is always 0
     distance[s] = 0;

     // compute the shortest path for all the given vertices
     for (int cnt = 0; cnt < vertex - 1; cnt++) {
         // choose the minimum distance vertex from the set of vertices
         // not yet processed. ux is always equal to source s in the first
         // iteration.
         int ux = minimumDistance(distance, spSet);

         // the choosed vertex is marked as true
         // it means it is processed
         spSet[ux] = true;

         // Updating the distance value of the neighboring vertices
         // of the choosed vertex.
         for (int vx = 0; vx < vertex; vx++) {
             if (!spSet[vx] && graph[ux][vx] != -1 && distance[ux] != Integer.MAX_VALUE
                     && distance[ux] + graph[ux][vx] < distance[vx]) {
                 distance[vx] = distance[ux] + graph[ux][vx];
             }
        }
     }

     // display the build distance array
     // printSolution(distance, vertex);
     return distance;
 }

 void print() {
     for (int i = 0; i < vertex; i++) {
         for (int j = 0; j < vertex; j++) {
             if (graph[i][j] == -1)
                 System.out.print(0 + " ");
             else
                 System.out.print(graph[i][j] + " ");
         }
         System.out.println();
     }
 }

 // main method
 public static void main(String argvs[]) {
     // A 9 * 9 matrix is created.
     // arr[x][y] = - 1 means, there is no any edge that connects the nodes x and y
     // directly
     int v = 40;
     int fa = v / 2;
     // int[] f = new int[3];
     // int[] c = new int[3];
     // int graph[][] = new int[v][v];
     // printg(graph);

     try {
         File myObj = new File("graph_40.txt");
         File myObjf = new File("fc_20.txt");
         FileWriter myWriter = new FileWriter("input4_20.txt");
         Scanner myReader = new Scanner(myObj);
         Scanner myReader1 = new Scanner(myObjf);

         Graph g = new Graph(v);
         int[] f = new int[fa];
         int[] c = new int[fa];
         // for(int i=0;i<20;i++){
         while (myReader.hasNextLine()) {
             int no = myReader.nextInt();
             System.out.println(no);
             float prob = myReader.nextFloat();
             System.out.println(prob);
             int n = myReader.nextInt();
             int m = myReader.nextInt();
             System.out.println(m);
             for (int i = 0; i < m; i++) {
                 int n1 = myReader.nextInt();
                 int n2 = myReader.nextInt();
                 int w = myReader.nextInt();
                 g.addEdge(n1 - 1, n2 - 1, w);
                 // graph[n1][n2]=w;
                 // graph[n2][n1]=w;
             }
             g.print();
             for (int i = 0; i < fa; i++) {
                 f[i] = myReader1.nextInt();
             }
             for (int i = 0; i < fa; i++) {
                 c[i] = myReader1.nextInt();
             }
             //System.out.println(fa);
             myWriter.write(fa+"\n"+fa+"\n");

             for (int i = 0; i < fa; i++) {
                 int[] dist = g.dijkstra(c[i] - 1);
                 System.out.print(dist.length+" ");
                 for (int q = 0; q < fa; q++) {
                     myWriter.write(dist[f[q]-1] + " ");
                     //System.out.print(dist[q] + " ");
                 }
                 myWriter.write("\n");
                // System.out.println("\n");
             }
             myWriter.write("\n");
             // Graph g = new Graph(v);
             // g.dijkstra(0);
             // g.print();
             // obj.dijkstra(graph, 0);

             // distance[] = obj.dijkstra(grph, 0);
             // myWriter.write("Files in Java might be tricky, but it is fun enough!");
             // String data = myReader.nextLine();
             // System.out.println(data);
         }
         myReader.close();
         myReader1.close();
         myWriter.close();
     } catch (FileNotFoundException e1) {
         System.out.println("no file found.");
     } catch (IOException e2) {
         System.out.println("no can write.");
     }

     // creating an object of the class DijkstraExample
     // DijkstraExample obj = new DijkstraExample(6);
     // obj.dijkstra(grph, 0);
 }
}
