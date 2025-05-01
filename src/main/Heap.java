package main;
/**
 * Jameer Gomez-Santos
 * 4/22/25
 * Known Bugs: N/A
 * Cosi 21
 * This class represents the standard functionality of a Binrary heap.
 */

 //TODO: Test every method in the class somehow lol, also add in heapsort!!
public class Heap {
    private int[] contents;
    private int length; // Size of the array
    private int size; // Num of elements stored in the array

    public void buildMaxHeap(int[] toBeHeaped){
        this.size = toBeHeaped.length;
        for (int i = toBeHeaped.length/2; i  == 1; i--) {
            heapifyDown(i);
        }
    }
    public void insert(int num){
        this.size++;
    }
    public void delete(int num){
        this.size--;
    }
    //Do this like, eventually
    public void heapSort(){

    }
    public void heapifyUp(int i){
        while (i > 1 && this.contents[i] > this.contents[parent(i)]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    /**
     * Maintains the heap property by bringing a element down a heap.
     * @param i Element to be brought down the heap
     */
    public void heapifyDown(int i){
        int largest = 0;
        int left = left(i);
        int right = right(i);
        if (left < this.size && this.contents[left] > this.contents[i]) {
             largest  = left;
        }
        else {
             largest = i;
        }
        if (right <= this.size && this.contents[right] > this.contents[largest]) {
            largest = right;
        }
        if (largest != right) {
            // Swap this.contents[i] with this.contents[largest]
            swap(i, largest);
            heapifyDown(largest);
        }
    }
    /**
     * Returns the elements that is to the left of a given element in a heap
     * @param i The element we want to find the left of
     * @return The element to the left of i
     */
    public int left(int i){
        return (2*i);
    }
    /**
     * Returns the element that is to the right o fa given element in a heap
     * @param i The element we want to find the right of
     * @return The element to the right of i
     */
    public int right(int i){
        return (2*i+1);
    }
    /**
     * Returns the parent of a element in a heap if applicaple. If root then return null
     * @param i The index that we want to find the parent of.
     * @return The parent of element at index i
     */
    public int parent(int i){
        return (i - 1) / 2;
    }
    public int getMax(){
        return this.contents[0];
    }
    /**
     * Swaps the element at position 1 to the element at position 2
     * @param index1
     * @param index2
     */
    public void swap(int index1, int index2){
        int temp = this.contents[index1];
        this.contents[index1] = this.contents[index2];
        this.contents[index1] = temp;

    }
}
