import generics.*;

public interface AngryCollab {

	/**
	 * Adiciona um outro colaborador a lista de inimigos
	 * @param enemy novo inimigo
	 * @pre enemy!=null
	 */
	void addEnemy(AbsCollaboratorClass enemy);

	/**
	 * Remove um colaborador da lista de inimigos
	 * @param enemy inimigo a remover
	 * @pre enemy!=null
	 */
	void removeEnemy(AbsCollaboratorClass enemy);

	/**
	 * Verifica se e um inimigo atraves do seu identificador unico, o seu nome
	 * @param name nome do inimigo a verificar
	 * @return <code>true</code> se e um inimigo <code>false</code> caso nao seja
	 *
	 */
	boolean isAnEnemy(String name);

	/**
	 * Devolve o objeto Array que contem os inimigos
	 * @return objeto Array dos inimigos
	 */
	Array<AbsCollaboratorClass> getEnemies();
	
}
