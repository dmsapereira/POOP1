package generics;

public class IteratorClass<E> implements Iterator<E> {
	private E[] vector;
	private int counter;
	private int current;

	public IteratorClass(E[] vector, int counter) { 
		this.vector = vector;
		this.counter = counter;
		init();
	}

	@Override
	public void init() {
		current = 0;
	}

	@Override
	public boolean hasNext() {
		return current < counter; 
	}

	@Override
	public E next() {
		return vector[current++];
	}

}
