public class Graph
{
  //------------ Protected Attributes ----------------------------
  //    The representation of a graph consists of an array
  //    of nodes that map nodes (just integers) to array 
  //    indexes. The adjacency matrix _matrix sets matrix[i][j]
  //    to true if there is an edge between _nodes[i] and 
  //    _nodes[j].
  //  
  //    The operations must maintain the following invariant:
  //        _nodes[i] is defined iff i < _allocated
  //        _allocated <= _order

  protected int     _order;      // The number of nodes allowed 
  protected int     _allocated;  // The next free space in the node array
  protected int     _nodes[]; // A list of the actual nodes
  protected boolean _matrix[][]; // The adjacency matrix

  //------------- Protected Methods -------------------------------

  protected static boolean[][] _allocate(int n)
  {
    return new boolean[n][n];
  }

  protected static int[] _nodes(int n)
  {
    return new int[n];
  }

  /**
   * Lookup the index of node in the _nodes array
   */
  protected int _lookup(int m)
  {
    int index = 0;
    while (index < _allocated && _nodes[index] != m) {
      index = index + 1;
    }
 
    if (index == _allocated) {
      return _order + 1;
    }
    else {
      return index;
    }
  }

  /**
   * Create a matrix of size n and initial all of the state variables
   * so that the invariant is maintained.
   */
  public Graph(int n) 
  {
    _order = n;
    _allocated = 0;
    _nodes = _nodes(n);
    _matrix = _allocate(n);
  }


  /**
   * Add the node n to the graph. If the graph is full, do nothing.
   */
  public void addNode(int n) 
  {
    //if the graph is not full...
    if (_allocated <= _order) {
      _nodes[_allocated] = n;
      _allocated = _allocated + 1;
    }
  }

  /**
   * Add an edge (m, n) to the graph. Edges are specified by pairs of
   * nodes. An edge be added if and only if m and n have already
   * been added to graph as nodes.
   */
  public void addEdge(int m, int n)
  {
    //get the indices of m and n
    int mIndex = _lookup(m);
    int nIndex = _lookup(n);

    //if the indices are valid, add the edges
    if (mIndex < _order && nIndex < _order)
    {
      _matrix[mIndex][nIndex] = true;
      _matrix[nIndex][mIndex] = true;
    }
  }

  /**
   * Delete node n from the graph if and only if it is not part of
   * some edge in the graph and it exists as a node in the graph.
   */
  public void deleteNode(int n)
  {
    //get the index of the node
    int nIndex = _lookup(n);

    //if the index is valid, try to remove it
    if (nIndex < _order)
    {
      //find whether the node is part of an edge
      boolean isEdge = false;    
      for (int i = 0; i < _allocated; i++) {
	isEdge = _matrix[nIndex][i] || _matrix[i][nIndex];
      }

      //if this is not part of an edge, delete the node by copying
      //all elements to the right of it in the array one index to the
      //left.
      if (!isEdge) {
	for (int i = nIndex; i < _allocated-1; i++) {
	  _nodes[i] = _nodes[i+1];
	}
	_nodes[--_allocated] = 0;
      }
    }
  }


  /**
   * Delete the edge (m, n) from the graph.
   */
  public void deleteEdge(int m, int n)
  {
    //get the indices of m and n
    int mIndex = _lookup(m);
    int nIndex = _lookup(n);

    if (mIndex < _order && nIndex < _order) {
      _matrix[mIndex][nIndex] = false;
    }
  } 
}
