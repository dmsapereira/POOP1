package generics;

public class ArrayClass<E> implements Array<E> {
	private static final int DEFAULT_SIZE = 50;

	/**
	 * O array generico
	 */
	private E [] array;
	
	/**
	 * O numero de elementos actual
	 */
	private int counter;

	/**
	 * Construtor que define um array com dimensao inicial de 50
	 */
	@SuppressWarnings("unchecked")
	public ArrayClass() {
		array = (E []) new Object[DEFAULT_SIZE];
		counter = 0;
	}
	
	
	/**
	 * Construtor que define um array com dimensao inicial de <code>capacity</code>
	 * @param capacity dimensao inicial do array
	 * @pre capacity > 0
	 */
	@SuppressWarnings("unchecked")
	public ArrayClass(int capacity) {
		array = (E []) new Object[capacity];
		counter = 0;
	}

	@Override
	public void insertLast(E elem) {
		if (counter == array.length)  // se necessario, aumentar vector
			resize();
		array[counter++] = elem;  
	}

	@Override
	public void insertAt(E elem, int pos) {
		if (counter == array.length) 
			resize();
		for(int i = counter-1; i >= pos; i--)
			array[i+1] = array[i];
		array[pos] = elem;
		counter++;
	}
	
	@Override
	public E removeLast() {
		return array[--counter];
	}

	@Override
	public E removeAt(int pos) {
		E elem = array[pos];
		for(int i = pos; i < counter-1; i++)
			array[i] = array[i+1];
		counter--;
		return elem;
	}
	
	@Override
	public boolean remove(E e) {
		int pos = searchIndexOf(e);
		if (pos == -1) return false;
		for(int i = pos; i < counter-1; i++)
			array[i] = array[i+1];
		counter--;
		return true;
	}

	@Override
	public int size() {
		return counter; 
	}

	@Override
	public int searchIndexOf(E elem) {
		boolean found = false;
		int i=0;
		while(i < counter && !found)
			if(array[i].equals(elem)) 
				found = true;
			else i++;
		if (found) return i;
		else return -1;
	}
	
	public E get(int pos) {
		return array[pos];
	}
	

	@Override
	public Iterator<E> iterator() { 
		return new IteratorClass<E>(array,counter);
	}

	/**
	 * Metodo auxiliar para duplicar o tamanho do vector. 
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		E[] tmp = (E []) new Object[counter * 2];
		for (int i = 0; i < counter; i++)
			tmp[i] = array[i];
		array = tmp;
	}
}
