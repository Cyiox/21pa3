package main;
import java.io.*;

/**
 * Jameer Gomez-Santos
 * 5/4/24
 * jameerg@brandeis.edu
 * COSI 21
 * PA3
 * Prof Antonella
 * <This class functions as the main method for finding the shortest path from one node to another on a graph. OUTPUT IS INSIDE answer.txt >
 *  * Known Bugs: The min PQ needs to be pretty large in order for this to work correctly on the given graph

 */
public class FindMinPath {

    public static void main(String[] args) {
        final int DATA_STRUCTURE_SIZES = 5000;
        GraphWrapper gw = new GraphWrapper();
        MinPriorityQueue Q = new MinPriorityQueue(DATA_STRUCTURE_SIZES);
        GraphNode home = gw.getHome();

        home.priority = 0;
        Q.insert(home);

        while (!Q.isEmpty()) {
            GraphNode curr = Q.pullHighestPriorityElement();

            if (curr.isGoalNode()) {
                GraphNode goal = curr;
                String path = "";
                while (goal.previousNode != null) { // Constructs a string to be put into a file as output and if the goal has been found
                    path = goal.previousDirection + "\n" + path;
                    goal = goal.previousNode;
                }
                try {
                    FileWriter writer = new FileWriter("answer.txt");
                    writer.write(path);
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error writing to the file");
                }
            }

            if (curr.hasNorth()) { // Will check if any more nodes can be accessed, add them into the PQ, or change priorty if a shorter path is found.
                GraphNode neighbor = curr.getNorth();
                int x = curr.priority + curr.getNorthWeight();
                if (!Q.contains(neighbor) && !Q.isVisited(neighbor)) {
                    neighbor.priority = x;
                    neighbor.previousNode = curr;
                    neighbor.previousDirection = "NORTH";
                    Q.insert(neighbor);
                } else if (!Q.isVisited(neighbor) && x < neighbor.priority ) {
                    neighbor.priority = x;
                    neighbor.previousNode = curr;
                    neighbor.previousDirection = "NORTH";
                    Q.rebalance(neighbor);
                }
            }

            if (curr.hasSouth()) {
                GraphNode neighbor = curr.getSouth();
                int x = curr.priority + curr.getSouthWeight();
                if (Q.isVisited(neighbor)) {
                }
                if (!Q.contains(neighbor) && !Q.isVisited(neighbor)) {
                    neighbor.priority = x;
                    neighbor.previousNode = curr;
                    neighbor.previousDirection = "SOUTH";
                    Q.insert(neighbor);
                } else if (!Q.isVisited(neighbor) && x < neighbor.priority) {
                    neighbor.priority = x;
                    neighbor.previousNode = curr;
                    neighbor.previousDirection = "SOUTH";
                    Q.rebalance(neighbor);
                }
            }

            if (curr.hasEast()) {
                GraphNode neighbor = curr.getEast();
                int x = curr.priority + curr.getEastWeight();
                if (!Q.contains(neighbor) && !Q.isVisited(neighbor)) {
                    neighbor.priority = x;
                    neighbor.previousNode = curr;
                    neighbor.previousDirection = "EAST";
                    Q.insert(neighbor);
                } else if (!Q.isVisited(neighbor) && x < neighbor.priority) {
                    neighbor.priority = x;
                    neighbor.previousNode = curr;
                    neighbor.previousDirection = "EAST";
                    Q.rebalance(neighbor);
                }
            }

            if (curr.hasWest()) {
                GraphNode neighbor = curr.getWest();
                int x = curr.priority + curr.getWestWeight();
                if (!Q.contains(neighbor) && !Q.isVisited(neighbor)) {
                    neighbor.priority = x;
                    neighbor.previousNode = curr;
                    neighbor.previousDirection = "WEST";
                    Q.insert(neighbor);
                } else if (!Q.isVisited(neighbor) && x < neighbor.priority) {
                    neighbor.priority = x;
                    neighbor.previousNode = curr;
                    neighbor.previousDirection = "WEST";
                    Q.rebalance(neighbor);
                }
            }
        }
    }
}
