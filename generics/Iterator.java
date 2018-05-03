package generics;

public interface Iterator<E> {
	/**
	 * Inicializa o iterador.
	 */
	void init();

	/**
	 * Verifica se existem mais elementos a iterar.
	 * @return <code>true</code> se houver mais elementos a iterar, 
	 * ou <code>false</code> caso contrario.
	 */
	boolean hasNext();

	/**
	 * Devolve o proximo elemento a iterar, avancando com o iterador.
	 * @pre hasNext()
	 * @return o proximo elemento.
	 */
	E next();
}
