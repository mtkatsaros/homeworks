package hw4;
import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;    // the number of items in the list
  
  public void add(int index, E obj)
  { // Fill Here 
	  Node<E> data = new Node<E>(obj);
	  Node<E> temp = head;
	  for(int i = 0; i < index; i++)
		  temp = temp.next;
	  data.next = temp.next;
	  temp.next.prev = data;
	  temp.next = data;
	  data.prev = temp;
   }
  public void addFirst(E obj) { // Fill Here 
	  if(head == null)
		  head = new Node<E>(obj);
	  else {
		  Node<E> temp = new Node<E>(obj);
		  temp.next = head;
		  head.prev = temp;
		  head = temp;
	  }
  }
  public void addLast(E obj) { // Fill Here
	  if(head == null)
		  head = new Node<E>(obj);
	  else {
		  Node<E> temp = new Node<E>(obj);
		  temp.prev = tail;
		  tail.next = temp;
		  tail = temp;
	  }
  }

  public E get(int index) 
  { 	ListIterator<E> iter = listIterator(index); 
      	return iter.next();
  }  
  public E getFirst() { return head.data;  }
  public E getLast() { return tail.data;  }

  public int size() {  
	  if(head != null) {
		  int count = 0;
		  for(Node<E> temp = head; temp.next != null; temp = temp.next) 
			  count++;
		  return count;
	  }
	  return -1; 
  } // Fill Here

  public E remove(int index)
  {     E returnValue = null;
        ListIterator<E> iter =  listIterator(index);
        if (iter.hasNext())
        {   returnValue = iter.next();
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;
  }

  public Iterator<E> iterator() { return new ListIter(0);  }
  public ListIterator<E> listIterator() { return new ListIter(0);  }
  public ListIterator<E> listIterator(int index){return new ListIter(index);}
  public ListIterator<E> listIterator(ListIterator<E> iter)
  {     return new ListIter( (ListIter) iter);  }

  // Inner Classes
  private static class Node<E>
  {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)  //constructor
        {   data = dataItem;   }
  }  // end class Node

  public class ListIter implements ListIterator<E> 
  {
        private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   // 

    public ListIter(int i)  // constructor for ListIter class
    {   if (i < 0 || i > size)
        {     throw new IndexOutOfBoundsException("Invalid index " + i); }
        lastItemReturned = null;
 
        if (i == size)     // Special case of last item
        {     index = size;     nextItem = null;      }
        else          // start at the beginning
        {   nextItem = head;
            for (index = 0; index < i; index++)  nextItem = nextItem.next;   
        }// end else
    }  // end constructor

    public ListIter(ListIter other)
    {   nextItem = other.nextItem;
        index = other.index;    }

    public boolean hasNext() {   
    	if (nextItem == null)
    		return false;
    	return true;    
    } // Fill Here
    public boolean hasPrevious(){  
    	if(index != 0)
    		return true;
    	return false;   
    } // Fill Here
    public int previousIndex() {  return index - 1;    } // Fill Here
    public int nextIndex() {  
    	if(nextItem == null)
    		return -1;
    	return index + 1;    
    } // Fill here
    public void set(E o)  {
    	if(lastItemReturned != null)
    		lastItemReturned.data = o;
    }  // not implemented
    public void remove(){
    	//if previous() was called
    	if(nextItem.data == lastItemReturned.data) {
    		nextItem.next.prev = nextItem.prev;
    		nextItem.prev.next = nextItem.next;
    		nextItem = nextItem.next;
    	}
    	//else if next() was called
    	else if(nextItem.data == lastItemReturned.next.data) {
    		nextItem.prev = lastItemReturned.prev;
    	}
    	lastItemReturned = null;
    		
    }      // not implemented

    public E next()
    {  
    	lastItemReturned = nextItem;
    	nextItem = nextItem.next;
    	index++;
        return lastItemReturned.data; // Fill Here 
    }

    public E previous() {
    	nextItem = nextItem.prev;
    	lastItemReturned = nextItem;
    	index--;
    	return lastItemReturned.data; // Fill Here 
    }

    public void add(E obj) {
    	Node<E> temp = nextItem;
    	while(temp.next != null)
    		temp = temp.next;
    	temp.next = new Node<E>(obj);
    	temp.next.prev = temp;
    		
    	
    // Fill Here
    }
  }// end of inner class ListIter
}// end of class DoubleLinkedList
