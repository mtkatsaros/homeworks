package hw5;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class CircularArrayQueue<E> implements Queue<E>{
	
	private E[] elements;
	private int capacity, front, back;
	
	public CircularArrayQueue(int size){
		capacity = size;
		elements = (E[])new Object[capacity];
		front = 0;
		back = 0;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		//use flag to check if full
		boolean flag = true;
		for(int i = 0; i < capacity; i++) 
			if(elements[i] == null) flag = false;
		if(flag)
			throw new IllegalStateException();
		
		//if empty
		if (elements[back] == null) 
			elements[back] = e;	
		//else if back at last index
		else if(back == capacity - 1) {
			back = 0;
			elements[back] = e;
		}
		else elements[++back] = e;
		return true;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		if (elements[front] == null)
			throw new NoSuchElementException(); 
		return elements[front];
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		//use flag to check if full
		boolean flag = true;
		for(int i = 0; i < capacity; i++) 
			if(elements[i] == null) flag = false;
		if(flag)
			return false;
		add(e);
		return true;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return elements[front];
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		if(peek() == null)
			return null;
		E temp = peek();
		elements[front] = null;
		front = (front == (capacity - 1)) ? 0 : (front + 1);
		return temp;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		if (peek() == null)
			throw new NoSuchElementException();
		return poll();
	}

	/*
	 * pollFew() and removeFew() work however the tests for only one do not. 
	 * I cannot wrap my head around why this is the case. 
	 */

}
