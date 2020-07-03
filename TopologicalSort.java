package GraphProblems;

import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;


/**
 * Date 06/05/2020
 * @author Vishal Bisht
 *
 * TOPOLOGICAL SORTING GRAPH ALGORITHM:
 *
 * Given a directed acyclic graph, perform a topological sort on this graph.
 *
 * Steps:
 * 1. Requires Set to store visited elements and a Stack to store sorted elements
 * 2. Perform DFS by keeping the track of visited
 * 3. Push the vertex which are completely explored into a Stack
 * 4. At last, Pop the elements from the Stack to get a toplogical sorted order of the given graph
 *
 * Time Complexity -> O(N)
 * Space Complexity -> O(N)
 * where N is the number of vertices
 */
public class TopologicalSort<T> {

    // Method that performs the actual Topological Sorting of the given graph
    // Used an ArrayDequeue to store the sorted elements
    // Used a HashSet to store the visited elements
    public Deque<Vertex<T>> topologySort(Graph<T> graph) {
        Deque<Vertex<T>> stackOfSorted = new ArrayDeque<>();
        Set<Vertex<T>> visited = new HashSet<>();
        for (Vertex<T> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex)) {
                continue;
            }
            topologySortUtil(vertex,stackOfSorted,visited);
        }
        return stackOfSorted;
    }

    private void topologySortUtil(Vertex<T> vertex, Deque<Vertex<T>> stackOfSorted, Set<Vertex<T>> visited) {
        visited.add(vertex);
        for(Vertex<T> childVertex : vertex.getAdjacentVertexes()){
            if(visited.contains(childVertex)){
                continue;
            }
            topologySortUtil(childVertex,stackOfSorted,visited);
        }
        stackOfSorted.offerFirst(vertex);
    }
    
    public static void main(String args[]){
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 3);
        // An edge from 1 to 3, signifies 1 -> i.e. 3 has a dependency on 1 and 1 cannot occur before 3
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 8);
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);
        
        TopologicalSort<Integer> sort = new TopologicalSort<Integer>();
        Deque<Vertex<Integer>> result = sort.topologySort(graph);
        // System.out.println(result);
        while(!result.isEmpty()){
            System.out.println(result.pop());
        }
    }
}