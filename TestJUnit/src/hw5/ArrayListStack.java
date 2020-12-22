package hw5;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<E> implements StackInterface<E> {
	
	private ArrayList<E> arr = new ArrayList<E>();

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		if(arr.size() < 1)
			return true;
		return false;
	}

	@Override
	public E peek() throws EmptyStackException{
		// TODO Auto-generated method stub
		if(empty())
			throw new EmptyStackException();
		return arr.get(arr.size() - 1);
	}

	@Override
	public E pop() throws EmptyStackException{
		// TODO Auto-generated method stub
		if(empty())
			throw new EmptyStackException();
		E data = arr.get(arr.size() - 1);
		arr.remove(data);
		return data;
	}

	@Override
	public E push(E obj) {
		// TODO Auto-generated method stub
		arr.add(obj);
		return obj;
	}

}
