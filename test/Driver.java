public class Driver
{
  public static void main(String [] args)
  {
    Graph g = new Graph(5);

    //test 1: nodes can be added
    g.addNode(1);
    g.addNode(2);

    assert contains(g._nodes, 1);
    assert contains(g._nodes, 2);
    assert !contains(g._nodes, 3);

    //test 2: edges can be added
    g.addEdge(1, 2);
    int mIndex = g._lookup(1);
    int nIndex = g._lookup(2);
    assert g._matrix[mIndex][nIndex];

    //test 3: nodes can be deleted
    g.addNode(3);
    assert contains(g._nodes, 3);
    g.deleteNode(3);
    assert !contains(g._nodes, 3);

    //test 4: nodes part of edges cannot be deleted
    g.deleteNode(1);
    assert contains(g._nodes, 1);

    //test 5: edges can be deleted
    assert g._matrix[mIndex][nIndex];
    g.deleteEdge(1, 2);
    assert !g._matrix[mIndex][nIndex];
  }

  /**
   * Returns true if and only if the target is in the array.
   */
  protected static boolean contains(int [] array, int target)
  {
    boolean found = false;

    for (int i = 0; i < array.length; i++) {
      if (array[i] == target) {
	found = true;
	break;
      }
    }

    return found;
  }
}
