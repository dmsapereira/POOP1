import generics.*;

public interface CollabList {

	/**
	 * devolve o nome de um colaborador
	 * @param index o indice do colaborador na lista em questao
	 * @return o nome do colaborador encontrado
	 * @pre index<counter
	 */
	String getName(int index);

	/**
	 * Devolve o objeto do colaborador com o nome em questao
	 * @param name nome a procurar
	 * @return o objeto colaborador com o nome em questao
	 */
	AbsCollaboratorClass getCollabByName(String name);

	/**
	 * Adiciona um colaborador novo
	 * @param type especializacao do colaborador (ator, produtor, realizador ou tecnico)
	 * @param status no caso de poder ser um vedeta, sera VEDETA se o for, NORMAL caso nao o seja
	 * @param cost custo horario do colaborador
	 * @param name nome do colaborador
	 */
	void addCollaborator(String type, String status, int cost, String name);

	/**
	 * Devolve o iterador da lista de colaboradores
	 * @return o objeto iterador do objeto array de colaboradores
	 */
	Iterator<AbsCollaboratorClass> getIterator();

	/**
	 * Adiciona um inimigo a um colaborador
	 * @param vedetaName nome do vedeta que tera um novo inimigo
	 * @param targetName nome do novo inimigo
	 * @return 	>0-> Placeholder para o numero de gravacoes que poderao vir a ser suspensas pelo amuanco
	 * 			 0-> Sem erros
	 * 			-1-> O vedeta nao existe
	 * 			-2-> O novo inimigo nao existe
	 * 			-3-> Ja sao inimigos
	 */
	int addEnemy(String vedetaName, String targetName);

	/**
	 * Remove um inimigo de um colaborador
	 * @param vedetaName nome do vedeta
	 * @param targetName nome de inimigo a remover
	 * @return  >0-> Placeholder para o numero de gravacoes que poderao vir a ser salvas pela reconciliacao
	 * 			-1-> O vedeta nao existe
	 * 			-2-> O novo inimigo nao existe ou nao e inimigo do vedeta
	 */
	int removeEnemy(String vedetaName, String targetName);

	/**
	 * Devolve os inimigos do vedeta
	 * @param name nome do vedeta
	 * @return o objeto array que contem os inimigos do vedeta
	 * @pre o colaborador e um vedeta e existe
	 */
	Array<AbsCollaboratorClass> getEnemies(String name);

}
