import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
    private HashMap map;

    @Before
    public void setUp() {
        map = new HashMap(10);
    }

    @test
    public void testSetAndGetValue() {
        GraphNode node = new GraphNode("A1");
        map.set(node, 5);
        assertEquals(5, map.getValue(node));
    }

    @Test
    public void testUpdateValue() {
        GraphNode node = new GraphNode("A1");
        map.set(node, 5);
        map.set(node, 10);
        assertEquals(10, map.getValue(node));
    }

    @Test
    public void testHasKeyTrue() {
        GraphNode node = new GraphNode("B2");
        map.set(node, 7);
        assertTrue(map.hasKey(node));
    }

    @Test
    public void testHasKeyFalse() {
        GraphNode node = new GraphNode("C3");
        assertFalse(map.hasKey(node));
    }

    @Test
    public void testOverwriteDeletedSlot() {
        GraphNode node = new GraphNode("D4");
        map.set(node, 15);
        map.set(node, -1); // simulate deletion
        map.set(node, 8);  // reinsert
        assertEquals(8, map.getValue(node));
    }

    @Test
    public void testCollisionHandling() {
        GraphNode node1 = new GraphNode("A1");
        GraphNode node2 = new GraphNode("A2"); // ensure this collides by design

        map.set(node1, 1);
        map.set(node2, 2);

        assertEquals(1, map.getValue(node1));
        assertEquals(2, map.getValue(node2));
    }
}
