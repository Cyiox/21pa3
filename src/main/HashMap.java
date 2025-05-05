package main;
/**
 * Jameer Gomez-Santos
 * 5/4/24
 * jameerg@brandeis.edu
 * COSI 21
 * PA3
 * Prof Antonella
 * <This class functions as a HashTable, It is named hashmap because of the PDF. It has functions for quickly acessing entrys of a graph and mainting them inside of a heap. Uses Linear probing and open addressing.>
 */
public class HashMap {
    private Entry[] contents;
    private static final double LOAD_FACTOR_LIMIT = 0.5; 
    private int size;



    /**
     * Initlizes a hashMap / Table of given size (Dynamic)
     * @param size Size of the hashMap
     */
    public HashMap(int size){
        this.contents = new Entry[size];
        this.size = 0;

    }
    
    /**
     * Searches the hashMap to see if the same "entry" already exist withen the HM, if it does overwrite that with a new value. Else add in a new entry with the given key and value
     * @param key Key of a entry to be added into the HM (Graph Node)
     * @param value Value of an entry (index in the heap)
     */
    public void set(GraphNode key, int value){
        if ((double) size / contents.length > LOAD_FACTOR_LIMIT) {
            resize();
        }
        int index = hashFunction(key.getId());
        int initalIndex = index;
         while (this.contents[index]!= null)  {
            if (this.contents[index].getKey().getId().equals(key.getId())|| contents[index].getValue() == -1) {
                this.contents[index] = new Entry(key, value);
                return;
            }
            index = (index + 1) % this.contents.length;
            if (index == initalIndex) {
                System.out.println("Table is full");
                return;
            }
        }
        this.contents[index] = new Entry(key, value);
        size++;
        


    }
    /**
     * Gets the graph node value with the given key  
     * @param g Key for the graph node
     * @return The value of the graph node assosited with the key. Return -1 if not found
     */
    public int getValue(GraphNode g){
        int index = hashFunction(g.getId());
        int initalIndex = index;
        boolean fullLoop = false;
        while (this.contents[index] != null && !fullLoop) {
            Entry entry = this.contents[index];
            if (entry != null && entry.getKey() != null && entry.getKey().getId().equals(g.getId())) {
                return entry.getValue();
            }

            index = (index + 1) % this.contents.length;
            if (index == initalIndex) {
                fullLoop = true;
            }

        }
        return -1; // Not found return
    }
    
    /**
     * Returns true if the given key existi inside of the hashmap. Else false 
     * @param g The given key
     * @return True if key currently exist withen the graph. Else false
     */
    public boolean hasKey(GraphNode g) {
        int index = hashFunction(g.getId());
        int initialIndex = index;
        boolean fullLoop = false;
        while (contents[index] != null && !fullLoop) {
            if (contents[index].getKey().getId().equals(g.getId()) && contents[index].getValue() != -1) {
                return true;
            }
            index = (index + 1) % contents.length;
            if (index == initialIndex){
                fullLoop = true;
            }
        }
        return false;
    }

    /**
     * Resizes the HM when the load factor is too high in order to maintain speed
     */
    private void resize() {
        int newCapacity = contents.length * 2; 
        Entry[] newContents = new Entry[newCapacity];

        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null && contents[i].getValue() != -1) {
                int newIndex = hashFunction(contents[i].getKey().getId()) % newCapacity;
                while (newContents[newIndex] != null) {
                    newIndex = (newIndex + 1) % newCapacity; 
                }
                newContents[newIndex] = contents[i]; 
            }
        }

        contents = newContents; // Update the reference to the resized array
    }
    
    /**
     * Takes the first 3 chars of an input and attempts to find a random value in the hashtable to index in
     * @param input key of a entry
     * @return index where entry should be placed
     */
    private int hashFunction(String input){
            // Assuming key is always 3 characters long
        return (input.charAt(0) * 31 + input.charAt(1) * 17 + input.charAt(2)) % contents.length;
        
        
    }
}
