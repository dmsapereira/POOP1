package generics;

public interface Array<E>{
	/**
	 * Insere o elemento <code>e</code> na ultima posicao do array
	 * @param e elemento a inserir no array
	 */
	void insertLast(E e);
	
	/**
	 * Insere o elemento <code>e</code> na posicao <code>pos</code> do array
	 * @param e elemento a inserir no array
	 * @param pos posicao do array a inserir o elemento
	 * @pre pos < size()
	 */
	void insertAt(E e, int pos);
	
	/**
	 * Remove o ultimo elemento do array
	 * @pre size() > 0
	 */
  E removeLast();
  
	/**
	 * Remove o elemento da posicao <code>pos</code> do array
	 * @param pos posicao do elemento a remover do array
	 * @pre pos < size()
	 */
	E removeAt(int pos);
	
	/**
	 * Remove a primeira ocorrencia do elemento <code>e</code> do array
	 * @param <code>e</code> elemento a procurar, partindo da posicao inicial do array
	 * @return <code>true</code> se o elemento existe e foi removida a primeira ocorrencia do array,
	 * 	     <code>false</code>  se o elemento nao existe
	 */
	boolean remove(E e);

	/**
	 * Procura a posicao do elemento <code>e</code> no array
	 * @param e elemento a procurar do array
	 * @return a posicao do elemento no array,
	 * 			<code>-1</code> caso o elemento nao exista
	 */
	int searchIndexOf(E e);
	
	/**
	 * Devolve o elemento na posicao <code>pos</code> no array
	 * @param pos posicao do array do elemento a devolver
	 * @return o elemento na posicao <code>pos</code>,
	 * @pre pos < size()
	 */
	E get(int pos);
	
	/**
	 * Devolve um iterador para os elementos do array
	 * @return iterador para os os elementos do array
	 */
	Iterator<E> iterator();
	
	/**
	 * Devolve o numero de elementos no array
	 * @return o numero de elementos no array
	 */
	int size();
}
