package main;

/**
 * Jameer Gomez-Santos
 * 5/4/24
 * jameerg@brandeis.edu
 * COSI 21
 * PA3
 * Prof Antonella
 * <This class functions as a min-priorty queue. It contains methods such as extract max and heapify.>
 */
public class MinPriorityQueue {
    private Heap heap;
    private HashMap hashMap;


    /**
     * Initlizes a new MinPQ with a given size
     * @param size Size of the min-PQ (not dynamic)
     */
    public MinPriorityQueue(int size){
        this.heap = new Heap(size);
        this.hashMap = new HashMap(size);
    }
    /**
     * Returns true if the PQ contains no elements
     * @return true if no elements are contained
     */
    public boolean isEmpty(){
        if (this.heap.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Inserts a given node into the priority queue, then re-structures the queue to maintain heap property.
     * @param node Node to be inserted into the queue
     */
    public void insert(GraphNode node){
        int index = this.heap.insert(node);
        if (index != -1) {
            this.hashMap.set(node, index);
        }
    }
    /**
     * Extracts the min element out of a priortiy queue
     * @return Min element from the priority queue
     */
    public GraphNode pullHighestPriorityElement() {
        GraphNode min = this.heap.getMin();
        int endIndex = this.heap.getSize() - 1;
        PQSwap(0, endIndex);
        heap.setSize(heap.getSize() - 1);
    
        // -2 is being used to represent the condition of "has been visted" for a graph node
        hashMap.set(min, -2);
    
        heap.heapifyDown(0);
        return min;
    }
    
    
    /**
     * Maintains heap property when a priorty is changed
     * @param g Element that has had its priorty changed
     */
    public void rebalance(GraphNode g) {
        int index = this.hashMap.getValue(g);
        if (index == -1 || index == -2) {
            return;  // Don't rebalance if not in heap or already visited
        }
        heapify(index);
        this.hashMap.set(g, index);
    }
    /**
     * Ensures a element is where it is supposed to be in a heap. 
     * @param index Index of element in order to be sorted in the heap
     */
    public void heapify(int index){
        this.heap.heapifyDown(index);
        this.heap.heapifyUp(index);
    }
    /**
     * Returns true if the node given is inside of the PQ
     * @param g Element to be fouind in the PQ
     * @return True if node is found, else false
     */
    public boolean contains(GraphNode g){
        int index = hashMap.getValue(g);
        return index != -1;

    }
    /**
     * Swaps the index of two elements in a PQ, Makes sure to also update their position in the HashTable / HashMap class
     */
    private void PQSwap(int i, int j) {
    GraphNode nodeI = heap.getContent(i);
    GraphNode nodeJ = heap.getContent(j);
    heap.swap(i, j);
    hashMap.set(nodeI, j);
    hashMap.set(nodeJ, i);
}
/**
 * Returns true if a node has been visted already (already put into a PQ)
 * @param g Node to be checked
 * @return True if already put into the priority queue.
 */ 
public boolean isVisited(GraphNode g) {
    return this.hashMap.getValue(g) == -2;
}
}
