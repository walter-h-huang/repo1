// Added new comment as only change


/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        clear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void clear( )
    {
        beginMarker = new Node<AnyType>( null, null, null );
        endMarker = new Node<AnyType>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<AnyType>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );
        
        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );
    
        return new String( sb );
    }
    
    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
    
    

    /*
     * 
     * My own additions to the MyLinkedList class
     * - swap, reverse, erase, insertList, shift methods
     * 
     */
    
    // Swap method - swaps two nodes given their indices
    // @throws IndexOutOfBoundsException if index is not between 0 and
    // size(), inclusive
    public void swap(int idx1, int idx2)
    {    	
    	// Indices are the same, no swap necessary
    	if (idx1 == idx2)
    	{
    		return;
    	}
    	
    	// If indices out of bound, caught here
    	Node<AnyType> node1 = getNode(idx1);
    	Node<AnyType> node2 = getNode(idx2);

    	// Nodes are next to each other, 1 then 2
    	if ((idx1 + 1) == (idx2))
    	{
    		swapExecute(node1, node2);
    		
        	node1.prev = node2;
        	node2.next = node1;
    	}
    	// Nodes are next to each other, 2 then 1
    	else if ((idx2 + 1) == (idx1))
    	{
    		swapExecute(node2, node1);
    		
        	node2.prev = node1;
        	node1.next = node2;
    	}
    	// All else
    	else
    	{
        	Node<AnyType> temp1 = node1.next;
        	Node<AnyType> temp2 = node2.prev;
    		
    		swapExecute(node1, node2);
    		
    		node1.prev = temp2;
    		temp2.next = node1;
    		node2.next = temp1;
    		temp1.prev = node2;
    	}
    }
    
    // Base method for swapping nodes - used by swap()
    private void swapExecute(Node<AnyType> node1, Node<AnyType> node2)
    {    	
    	node1.next = node2.next;
    	node1.next.prev = node1;
    	node2.prev = node1.prev;
    	node2.prev.next = node2;
    }
    
    
    // reverse method
    // @returns new MyLinkedList with reverse order elements
    public MyLinkedList<AnyType> reverse(MyLinkedList<AnyType> oldList)
    {
    	MyLinkedList<AnyType> newList = new MyLinkedList<AnyType>();
    	
    	// Add to head of newList the ith term in oldList
    	for (int i = 0; i < oldList.size(); i++)
    	{
    		newList.add(0, oldList.get(i));
    	}
    	
    	return newList;
    }
    
    
    // erase method
    // receives index and number of elements to erase
    // @throws IndexOutOfBoundsException if any combo of the above
    // parameters reaches a point out of bounds
    public void erase(int index, int numbErase)
    {
    	// Verify if parameters fall within range of list
    	this.get(index);
    	this.get(index + numbErase - 1);
    	
    	// Set nodes before and after the section to be deleted
    	Node<AnyType> prev = this.getNode(index).prev;
    	Node<AnyType> next = this.getNode(index + numbErase - 1).next;
    	
    	prev.next = next;
    	next.prev = prev;
    	
    	// Update size accordingly
    	this.theSize = this.theSize - numbErase;
    }
    
    
    
    // insertList method
    // Receives a List and index (insertion point)
    // Assuming index is within bounds, inserts the whole list into
    // current list
    // @throws IndexOutOfBoundsException if try to insert outside of list
    public void insertList(MyLinkedList<AnyType> insList, int index)
    {
    	// Verify index is within bounds
    	if ((this.size() < index) || (index < 0))
    	{
    		throw new IndexOutOfBoundsException("Received index: " 
    				+ index + " outside of list bounds");
    	}
    	else
    	{
    		// Insert
	    	for (int i = insList.size() - 1; i >= 0; i--)
	    	{
	    		this.add(index, insList.get(i));
	    	}
    	}
    	
    	// Update size
    	this.theSize = this.theSize + insList.size();
    }
    
    
    
    // shift method
    // Receives integer: if positive, take that many nodes from the head
    // and place at the end; if negative, take that many nodes from the
    // tail and place at the beginning, ie.
    // +2: abcde -> cdeab
    // -3: abcde -> cdeab
    public void shift(int nodeShift)
    {
    	// No shift required
    	if (nodeShift == 0)
    	{
    		return;
    	}
    	// Positive
    	else if (nodeShift > 0)
    	{
    		for (int i = 0; i < nodeShift; i++)
    		{
    			this.add(this.size() - 1, this.remove(0));
    		}
    	}
    	// Negative
    	else
    	{
    		for (int i = 0; i > nodeShift; i--)
    		{
    			this.add(0, this.remove(this.size() - 1));
    		}
    	}
    }
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<Integer>( );
        
        for( int i = 0; i < 10; i++ )
            lst.add( i );
        for( int i = 20; i < 30; i++ )
            lst.add( 0, i );
        
        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );
        
        System.out.println( lst );
        
        
        java.util.Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )
        {
            itr.next( );
            itr.remove( );
            System.out.println( lst );
        }
        
        
        /*
         * 
         * Added code for demonstrating 
         * swap, reverse, erase, insertList, shift methods
         * 
         */
        
        // Demo swap
        System.out.println("\nDemonstrating swap");
        
        for( int i = 0; i < 10; i++ )
            lst.add( i );
        
        lst.swap(0, 0);
        System.out.println(lst);
        
        lst.swap(0, 1);
        System.out.println(lst);
        
        lst.swap(1, 0);
        System.out.println(lst);
        
        lst.swap(0, 9);
        System.out.println(lst);
        
        lst.swap(9, 0);
        System.out.println(lst);

        
        
        
        // Demo reverse
        System.out.println("\nDemonstrating reverse");
        MyLinkedList<Integer> reverseList = lst.reverse(lst);
        System.out.println(reverseList);
        
        
        
        // Demo erase
        System.out.println("\nDemonstrating erase");
        System.out.println("Erasing from index 0, 5 elements");
        reverseList.erase(0, 5);
        System.out.println(reverseList);
        
        
        
        // Demo insertList
        System.out.println("\nDemonstrating insertList");
        System.out.println("Inserting current reverseList into lst");
        lst.insertList(reverseList, 5);
        System.out.println(lst);
        
        
        // Demo shift
        System.out.println("\nDemonstrating shift");
        System.out.println("Before:");
        System.out.println(reverseList);
        System.out.println("After shift +2:");
        reverseList.shift(+2);
        System.out.println(reverseList);
        
        System.out.println("After shift -2:");
        reverseList.shift(-2);
        System.out.println(reverseList);
    }
}
