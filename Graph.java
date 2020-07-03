package GraphProblems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T>{

    private List<Edge<T>> allEdges;
    private Map<Long,Vertex<T>> allVertices;
    boolean isDirected = false;
    
    public Graph(boolean isDirected){
        allEdges = new ArrayList<Edge<T>>();
        allVertices = new HashMap<Long,Vertex<T>>();
        this.isDirected = isDirected;
    }
    
    // Adding the edge(id1, id2)
    public void addEdge(long id1, long id2){
        addEdge(id1,id2,0);
    }
    

    // This works only for directed graph because for undirected graph we can end up
    // adding edges two times to allEdges
    public void addVertex(Vertex<T> vertex){
        if(allVertices.containsKey(vertex.getId())){
            return;
        }
        allVertices.put(vertex.getId(), vertex);
        for(Edge<T> edge : vertex.getEdges()){
            allEdges.add(edge);
        }
    }
    

    // Adding single vertex to the graph, basically to the set of vertices
    public Vertex<T> addSingleVertex(long id){
        if(allVertices.containsKey(id)){
            return allVertices.get(id);
        }
        Vertex<T> v = new Vertex<T>(id);
        allVertices.put(id, v);
        return v;
    }
    

    // Fetching the value of the vertex
    public Vertex<T> getVertex(long id){
        return allVertices.get(id);
    }
    

    public void addEdge(long id1,long id2, int weight){
        
        // Creating vertex1 i.e. object of class Vertex, 
        // if it does not already exists in the set of vertices
        Vertex<T> vertex1 = null;
        if(allVertices.containsKey(id1)){
            vertex1 = allVertices.get(id1);
        }else{
            vertex1 = new Vertex<T>(id1);
            allVertices.put(id1, vertex1);
        }
        
        // Creating vertex2 i.e. object of class Vertex, 
        // if it does not already exists in the set of vertices
        Vertex<T> vertex2 = null;
        if(allVertices.containsKey(id2)){
            vertex2 = allVertices.get(id2);
        }else{
            vertex2 = new Vertex<T>(id2);
            allVertices.put(id2, vertex2);
        }

        // Creating an edge between vertex1 and vertex2
        Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
        allEdges.add(edge);
        vertex1.addAdjacentVertex(edge, vertex2);
        if(!isDirected){
            vertex2.addAdjacentVertex(edge, vertex1);
        }

    }
    

    public List<Edge<T>> getAllEdges(){
        return allEdges;
    }
    
    
    public Collection<Vertex<T>> getAllVertex(){
        return allVertices.values();
    }
    

    public void setDataForVertex(long id, T data){
        if(allVertices.containsKey(id)){
            Vertex<T> vertex = allVertices.get(id);
            vertex.setData(data);
        }
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for(Edge<T> edge : getAllEdges()){
            buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}


class Vertex<T> {
    long id;
    private T data;
    private List<Edge<T>> edges = new ArrayList<>();
    private List<Vertex<T>> adjacentVertex = new ArrayList<>();
    
    Vertex(long id){
        this.id = id;
    }
    
    public long getId(){
        return id;
    }
    
    public void setData(T data){
        this.data = data;
    }
    
    public T getData(){
        return data;
    }
    
    public void addAdjacentVertex(Edge<T> e, Vertex<T> v){
        edges.add(e);
        adjacentVertex.add(v);
    }
    
    public String toString(){
        return String.valueOf(id);
    }
    
    public List<Vertex<T>> getAdjacentVertexes(){
        return adjacentVertex;
    }
    
    public List<Edge<T>> getEdges(){
        return edges;
    }
    
    public int getDegree(){
        return edges.size();
    }
    
}


class Edge<T>{
    private boolean isDirected = false;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private int weight;
    
    Edge(Vertex<T> vertex1, Vertex<T> vertex2){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    Edge(Vertex<T> vertex1, Vertex<T> vertex2,boolean isDirected,int weight){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
        this.isDirected = isDirected;
    }
    
    Edge(Vertex<T> vertex1, Vertex<T> vertex2,boolean isDirected){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }
    

    Vertex<T> getVertex1(){
        return vertex1;
    }
    
    Vertex<T> getVertex2(){
        return vertex2;
    }
    
    int getWeight(){
        return weight;
    }
    
    public boolean isDirected(){
        return isDirected;
    }


    @Override
    public String toString() {
        return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
                + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
    }
}