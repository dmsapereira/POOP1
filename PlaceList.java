import generics.Iterator;

public interface PlaceList {

	/**
	 * Adiciona um local novo
	 * @param name nome do local
	 * @param cost custo horario do local
	 * @return <code>true</code> se for adicionado com sucesso, <code>false</code> caso o local ja exista
	 * @pre cost>0
	 */
	 boolean addPlace(String name, int cost);

	/**
	 * Devolve o local com o nome indicado
	 * @param name nome do local
	 * @return objeto local com o nome indicado
	 */
	PlaceClass getPlace(String name);

	/**
	 * Devolve o iterador dos locais
	 * @return o objeto iterador do objeto array dos locais
	 */
	 Iterator<PlaceClass> getPlaces();

	/**
	 * Verifica se o local ja existe
	 * @param name nome do local a verificar
	 * @return <code>true</code> se ja existir um local com o nome, <code>false</code> caso o nome esteja disponivel
	 */
	boolean alreadyExists(String name);
	 

}
