import java.time.LocalDateTime;
import generics.*;

public interface EventList {

	/**
	 * Adiciona um evento novo a lista
	 * @param newEvent evento novo a adicionar
	 * @pre newEvent!=null
	 */
	void addEvent(Event newEvent);

	/**
	 * Suspende um evento devido a brigas que envolvem colaboradores deste evento
	 * @param start data de inicio do evento, pelo qual sera identificado
	 * @param place local de filmagem do evento em questao, pelo qual sera identificado
	 * @pre o evento em questao existe
	 */
	void suspendEvent(LocalDateTime start, PlaceClass place);

	/**
	 * Grava o evento mais proximo cronologicamente
	 * @pre existem eventos
	 */
	void doEvent();

	/**
	 * Verifica se algum evento e realizado na data em questao
	 * @param date data a verificar
	 * @return <code>true</code> caso ja haja uma filmagem na data, <code>false</code> caso esta esteja livre
	 * @pre date !=null
	 */
	boolean checkDate(LocalDateTime date);

	/**
	 * Devolve o array dos eventos ja realizados
	 * @return o objeto array com os eventos passados
	 */
	Array<Event> getPastEvents();

	/**
	 * Devolve o array dos eventos futuros
	 * @return o objeto array com os eventos futuros
	 */
	Array<Event> getEvents();
}
