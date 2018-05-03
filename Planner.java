
import java.time.LocalDateTime;

import generics.*;

public interface Planner {

    /**
     * Adiciona um colaborador novo
     *
     * @param type   especializacao do colaborador (ator, produtor, realizador ou tecnico)
     * @param status no caso de poder ser um vedeta, sera VEDETA se o for, NORMAL caso nao o seja
     * @param cost   custo horario do colaborador
     * @param name   nome do colaborador
     * @return 0-> Nao ha problemas
     * 1-> O colaborador ja existe
     * 2-> Tipo de colaborador invalido
     * 3-> Colaborador nem e vedeta nem normal (caso seja Ator ou Realizador)
     * 4-> cost<=0
     */
    int addWorker(String type, String status, int cost, String name);

    /**
     * Devolve o iterador de todos os colaboradores
     *
     * @return o objeto iterador do objeto array que contem os colaboradores
     */
    Iterator<AbsCollaboratorClass> getCollabIterator();

    /**
     * Adiciona um inimigo a um colaborador
     *
     * @param vedetaName nome do vedeta que tera um novo inimigo
     * @param targetName nome do novo inimigo
     * @return >0-> Numero de gravacoes que foram suspensas com este amuanco
     * 0-> Sem erros
     * -1-> O vedeta nao existe
     * -2-> O novo inimigo nao existe
     * -3-> Ja sao inimigos
     */
    int addEnemy(String vedetaName, String targetName);

    /**
     * Adiciona um local novo
     *
     * @param name nome do novo local
     * @param cost custo horario do novo local
     * @return 0-> Sem erros
     * 1-> Ja existe um local com o nome pedido
     * 2-> cost<=0
     */
    int addPlace(String name, int cost);

    /**
     * Devolve o iterador dos locais
     *
     * @return o objeto iterador do objeto array que contem os locais
     */
    Iterator<PlaceClass> getPlaces();

    /**
     * Marca um evento
     *
     * @param collabs  array que contem os nomes dos colaboradores
     * @param start    data de inicio
     * @param duration duracao para calcular a data do fim
     * @param scenario nome do local de gravacao
     * @return 0-> Sem erros
     * 1-> nome nao corresponde a um local
     * 2-> a data de inicio nao e valida
     * 3-> duration<=0
     * 4,5,6,7-> Um dos colaboradores nao existe ou nao e do tipo especificado (Producer, Director, Technician, Collab; respetivamente)
     * 8-> O evento foi marcado, mas foi suspenso devido a um vedeta ter um inimigo o evento
     * 9-> Ja ha um evento na data especificada
     * 10-> Houve uma remarcacao devido a prioridade de produtores seniores
     */
    int addEvent(String[] collabs, LocalDateTime start, int duration, String scenario);

    /**
     * Remove um inimigo
     *
     * @param vedetaName nome do vedeta
     * @param targetName nome do inimigo
     * @return >0-> numero de gravacoes que foram salvas
     * 0-> sem erros
     * -1-> o nome nao corresponde a um vedeta ou nem existe
     * -2-> o inimigo nao existe ou nao e inimigo deste vedeta
     */
    int removeEnemy(String vedetaName, String targetName);

    /**
     * Devolve o iterador dos inimigos de um vedeta
     *
     * @param name nome do vedeta
     * @return o objeto iterador do objeto array que contem os inimigos do vedeta
     * @pre name corresponde a um vedeta
     */
    Array<AbsCollaboratorClass> getEnemies(String name);

    /**
     * Devolve o iterador dos eventos passados
     *
     * @return o objeto iterador do objeto array que contem os eventos passados organizados cronologicamente
     */
    Iterator<Event> getPastEventsIte();

    /**
     * Devolve o iterador dos eventos futuros
     *
     * @return o objeto iterador do objeto array que contem os eventos futuros organizados cronologicamente
     */
    Iterator<Event> getFutureEventsIte();

    /**
     * Verifica se o colaborador existe
     *
     * @param name nome do colaborador
     * @return <code>true</code> se existir, <code>false</code> se nao existir nenhum colaborador com o nome especificado
     */
    boolean doesCollabExist(String name);

    /**
     * Devolve o iterador dos eventos de um local
     *
     * @return o objeto iterador do objeto array que contem os eventos do local organizados cronologicamente
     * @pre name corresponde a um evento
     */
    Iterator<Event> getPlaceEvents(String name);

    /**
     * Devolve o iterador dos eventos em que participa um colaborador especifico
     *
     * @return o objeto iterador do objeto array que contem os eventos em que o colaborador participa organizados cronologicamente
     * @pre name corresponde a um colaborador
     */
    Iterator<Event> getCollabEvents(String name);

    /**
     * Grava o evento mais proximo cronologicamente
     *
     * @return o objeto evento que foi gravado
     */
    Event doEvent();

    /**
     * Verifica se o local existe
     *
     * @param name nome do local
     * @return <code>true</code> se existir, <code>false</code> se o local nao existir
     */
    boolean doesPlaceExist(String name);

}
