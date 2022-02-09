// --== CS400 File Header Information ==--
// Name: Joseph Reuss
// Email: jrreuss@wisc.edu
// Team: KA
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.NoSuchElementException;

/**
 * Tests the implementation of CS400Graph for the individual component of Project Three: the
 * implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

  private CS400Graph<Integer> graph;

  @BeforeEach
  /**
   * Instantiate graph from last week's shortest path activity.
   */
  public void createGraph() {
    graph = new CS400Graph<>();
    // insert verticies 0-9
    for (int i = 0; i < 10; i++)
      graph.insertVertex(i);
    // insert edges from Week 08. Dijkstra's Activity
    graph.insertEdge(0, 2, 1);
    graph.insertEdge(1, 7, 2);
    graph.insertEdge(1, 8, 4);
    graph.insertEdge(2, 4, 4);
    graph.insertEdge(2, 6, 3);
    graph.insertEdge(3, 1, 6);
    graph.insertEdge(3, 7, 2);
    graph.insertEdge(4, 5, 4);
    graph.insertEdge(5, 0, 2);
    graph.insertEdge(5, 1, 4);
    graph.insertEdge(5, 9, 1);
    graph.insertEdge(6, 3, 1);
    graph.insertEdge(7, 0, 3);
    graph.insertEdge(7, 6, 1);
    graph.insertEdge(8, 9, 3);
    graph.insertEdge(9, 4, 5);
  }

  /**
   * Checks the distance/total weight cost from the vertex labeled 0 to 8 (should be 15), and from
   * the vertex labelled 9 to 8 (should be 17).
   */
  @Test
  public void providedTestToCheckPathCosts() {
    System.out.println(graph.getPathCost(0, 8));
    assertTrue(graph.getPathCost(0, 8) == 15);
    System.out.println(graph.getPathCost(9, 8));
    assertTrue(graph.getPathCost(9, 8) == 17);
  }

  /**
   * Checks the ordered sequence of data within vertices from the vertex labeled 0 to 8, and from
   * the vertex labeled 9 to 8.
   */
  @Test
  public void providedTestToCheckPathContents() {
    // System.out.println(graph.shortestPath(0, 8).toString());
    assertTrue(graph.shortestPath(0, 8).toString().equals("[0, 2, 6, 3, 1, 8]"));
    // System.out.println(graph.shortestPath(9, 8).toString());
    assertTrue(graph.shortestPath(9, 8).toString().equals("[9, 4, 5, 1, 8]"));
  }

  /**
   * Checks to see if my distances were correct in the Dijkstras shortest path activity
   */
  @Test
  public void testShortestPathActivityDistances() {
    // System.out.println(graph.getPathCost(6, 5));
    assertTrue(graph.getPathCost(6, 5) == 15);
    // System.out.println(graph.getPathCost(6, 3));
    assertTrue(graph.getPathCost(6, 3) == 1);
  }

  /**
   * Checks to see if my path were correct in the Dijkstras shortest path activity for the longest
   * distance
   */
  @Test
  public void testShortestPathActivityPath() {
    // System.out.println(graph.shortestPath(6, 5).toString());
    assertTrue(graph.shortestPath(6, 5).toString().equals("[6, 3, 7, 0, 2, 4, 5]"));
  }

  /**
   * Checks to make sure the program chooses the correct shortest path when 2 very close paths (one
   * with distance 12 and one with distance 10) have similar distances
   */
  @Test
  public void testTwoVeryCloseShortestPaths() {
    assertTrue(graph.shortestPath(2, 1).toString().equals("[2, 6, 3, 1]"));
    assertTrue(graph.getPathCost(2, 1) == 10);
  }

  /**
   * Checks to make sure the program throws a NoSuchElementException when it tries to reach a node
   * that is on a separate and not connected part of the graph
   */
  @Test
  public void testVertexNotConnected() {
    CS400Graph<Integer> testGraph;
    testGraph = new CS400Graph<>();
    boolean testPass = false;
    // insert vertices 0-6
    for (int i = 0; i < 7; i++) {
      testGraph.insertVertex(i);
    }

    // This makes 0, 1, 2 separate from 3, 4, 5, 6
    testGraph.insertEdge(0, 1, 3);
    testGraph.insertEdge(0, 2, 2);
    testGraph.insertEdge(1, 2, 5);
    testGraph.insertEdge(3, 5, 1);
    testGraph.insertEdge(5, 6, 6);
    testGraph.insertEdge(4, 3, 3);
    testGraph.insertEdge(4, 5, 8);
    testGraph.insertEdge(4, 6, 5);

    // try to go from 0 to 4 which is not possible and should throw an exception
    try {
      testGraph.getPathCost(0, 4);
    } catch (NoSuchElementException e) {
      testPass = true;
    }

    assertTrue(testPass == true);
  }
}
