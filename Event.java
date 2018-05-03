
import java.time.LocalDateTime;

import generics.Array;

public interface Event {

    /**
     * Devolve a data de inicio do evento
     *
     * @return a data de inicio do evento
     */
    LocalDateTime getStart();

    /**
     * Devolve a data de fim do evento
     *
     * @return a data de fim do evento
     */
    LocalDateTime getEnd();

    /**
     * Devolve o o lugar onde o evento decorre
     *
     * @return local do evento
     */
    PlaceClass getPlace();

    /**
     * Atrasa o evento
     *
     * @param newDate nova data de inicio
     * @pre newDate!=null
     */
    void delayEvent(LocalDateTime newDate);

    /**
     * Activa o evento que poderia ja estar desativado devido a brigas
     */
    void activateEvent();

    /**
     * Suspende o evento por consequencia de brigas entre colaboradores
     */
    void suspendEvent();

    /**
     * Verifica se o evento esta suspenso por brigas
     *
     * @return <code>true</code> se estiver suspenso, <code>false</code> caso esteja ativo
     */
    boolean isOnHold();

    /**
     * Verifica se este evento tem algum colaborador em comum com outro evento
     *
     * @param target evento com o qual se ira comparar os colaboradores
     * @return <code>true</code> caso haja, no minimo, um colaborador em comum, <code>false</code> caso nao hajam sobreposicoes de colaboradores
     * @pre target!=null
     */
    boolean sameCollab(Event target);

    /**
     * Verifica se o colaborador existe neste evento
     * @param collab colaborador a procurar
     * @return <code>true</code> caso o colaborador esteja a paerticipar no evento, <code>false</code> caso nao esteja presente
     * @pre collab!=null && collab e um colaborador registado
     */
    boolean collabExistence(AbsCollaboratorClass collab);

    /**
     Verifica se o colaborador existe neste evento
     * @param name nome do colaborador a procurar
     * @return <code>true</code> caso o colaborador esteja a participar no evento, <code>false</code> caso nao esteja presente
     * @pre name e o nome de um colaborador registado
     */
    boolean collabExistence(String name);

    /**
     * Verifica se o produtor obrigatorio a cada evento e um Senior
     * @return <code>true</code> caso o produtor principal seja um Senior, <code>false</code> caso seja Junior
     */
    boolean checkForSenior();

    /**
     * Devolve o orcamento do projeto
     * @return orcamento do projeto
     */
    int getBudget();

    /**
     * Devolve o produtor principal do Evento
     * @return objeto produtor principal
     */
    AbsCollaboratorClass getProd();

    /**
     * Devolve o realizador principal do Evento
     * @return objeto diretor principal
     */
    AbsCollaboratorClass getDir();

    /**
     * Devolve o o objeto Array dos colaboradores do evento
     * @return objeto, do tipo Array, enemies
     */
    Array<AbsCollaboratorClass> getCollabs();

}
