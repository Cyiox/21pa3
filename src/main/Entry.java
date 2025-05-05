package main;
/**
 * Jameer Gomez-Santos
 * 5/4/24
 * jameerg@brandeis.edu
 * COSI 21
 * PA3
 * Prof Antonella
 * <This class functions as data structure, It ensures that Graph nodes are paired with their position in the heap for the hashMap class.>
 */
public class Entry {
    private GraphNode key;
    private int value;

    public Entry(GraphNode key, int value){
        this.key = key;
        this.value = value;
    }
    public GraphNode getKey(){
        return this.key;
    }
    public int getValue(){
        return this.value;
    }
    public void setKey(GraphNode key){
        this.key = key;
    }
    public void setValue(int value){
        this.value = value;
    }
}
