package main;
/**
 * Jameer Gomez-Santos
 * 4/22/25
 * Known Bugs: N/A
 * Cosi 21
 * This class represents the standard functionality of a Binrary heap.
 */

public class Heap {
    private GraphNode[] contents;
    private int size; // Num of elements stored in the array


    /**
     * Initlizes a new binary heap of a given size (non changeable)
     * @param size Size of the new binary heap
     */
    public Heap(int size){
        this.contents = new GraphNode[size];
        this.size = 0;
    }
    /**
     * Builds a heap out of a given array of graph nodes.
     * @param toBeHeaped array of graph nodes to be heaped.
     */
    public void buildMinHeap(GraphNode[] toBeHeaped){
        this.size = toBeHeaped.length;
        for (int i = 0; i < toBeHeaped.length; i++) {
            contents[i] = toBeHeaped[i];
        }
        for (int i = toBeHeaped.length / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
         }        
        }
        /**
         * inserts a graph node into the heap
         * @param num Graph node to be inserted into the heap
         * @return Position of last element in the heap
         */
    public int insert(GraphNode num){
        if (this.size >= this.contents.length){
            System.err.println("Heap is full");
            return -1;
        }
        contents[size] = num;
        heapifyUp(size);
        this.size++;
        return size - 1;
    }

    /**
     * Brings a element at index i up the heap to its correct position.
     * @param i Element to be brought up the heap
     */
    public void heapifyUp(int i){
        while (i > 0 && this.contents[i].priority <this.contents[parent(i)].priority) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    /**
     * Maintains the heap property by bringing a element down a heap.
     * @param i Element to be brought down the heap
     */
    public void heapifyDown(int i) {
        int smallest = i;
        int left = left(i);
        int right = right(i);
    
        if (left < size && contents[left].priority < contents[smallest].priority) {
            smallest = left;
        }
        if (right < size && contents[right].priority < contents[smallest].priority) {
            smallest = right;
        }
    
        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }
    
    /**
     * Returns the elements that is to the left of a given element in a heap
     * @param i The element we want to find the left of
     * @return The element to the left of i
     */
    public int left(int i){
        return (2*i+1);
    }
    /**
     * Returns the element that is to the right o fa given element in a heap
     * @param i The element we want to find the right of
     * @return The element to the right of i
     */
    public int right(int i){
        return (2*i+2);
    }
    /**
     * Returns the parent of a element in a heap if applicaple. If root then return null
     * @param i The index that we want to find the parent of.
     * @return The parent of element at index i
     */
    public int parent(int i){
        return (i - 1) / 2;
    }
    public GraphNode getMin(){
        return this.contents[0];
    }
    /**
     * Returns the current size of the heap (num of elements stored)
     * @return size of the heap
     */
    public int getSize(){
        return this.size;
    }
    /**
     * Sets the size of the heap
     * @param newSize Size of the heap
     */
    public void setSize(int newSize){
        this.size = newSize;
    }
    /**
     * Gets a graph node from a particular index in the heap
     * @param index index in which the graphnode is located
     * @return Graph node at index
     */
    public GraphNode getContent(int index){
        return this.contents[index];
    }
    /**
     * Swaps the element at position 1 to the element at position 2
     * @param index1 Element to be swapped
     * @param index2 Other element to be swapped
     */
    public void swap(int index1, int index2){
        GraphNode temp = this.contents[index1];
        this.contents[index1] = this.contents[index2];
        this.contents[index2] = temp;

    }
    /**
     * Returns true if the heap has no elements inside of it
     * @return True if the heap has no elements in it.
     */
    public boolean isEmpty(){
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }
}
