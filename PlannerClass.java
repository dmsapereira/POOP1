
import java.time.LocalDateTime;

import generics.*;

public class PlannerClass implements Planner {
	public static final String ACTOR = "ACTOR";
	public static final String SENIOR = "SENIOR";
	public static final String JUNIOR = "JUNIOR";
	public static final String TECHNICIAN = "TECNICO";
	public static final String DIRECTOR = "REALIZADOR";
	public static final String NORMAL = "NORMAL";
	public static final String VEDETA = "VEDETA";
	private CollabListClass collabs;
	private PlaceListClass places;
	private EventListClass events;

	public PlannerClass() {
		collabs = new CollabListClass();
		places = new PlaceListClass();
		events = new EventListClass();
	}

	@Override
	public int addWorker(String type, String status, int cost, String name) {
		int error = 0;
		if (collabs.getCollabByName(name) != null)
			error = 1;
		else if (!(type.equals(SENIOR) || type.equals(JUNIOR) || type.equals(ACTOR) || type.equals(TECHNICIAN)
				|| type.equals(DIRECTOR)))
			error = 2;
		else if ((type.equals(DIRECTOR) || type.equals(ACTOR)) && (!(status.equals(NORMAL) || status.equals(VEDETA))))
			error = 3;
		else if (cost < 0)
			error = 4;
		else
			collabs.addCollaborator(type, status, cost, name);
		return error;
	}

	@Override
	public Iterator<AbsCollaboratorClass> getCollabIterator() {
		return collabs.getIterator();
	}

	@Override
	public int addEnemy(String vedetaName, String targetName) {
		Iterator<Event> itera;
		Event aux;
		int error = collabs.addEnemy(vedetaName, targetName);
		int suspendCounter = 0;
		if (error == 0) {
			itera = events.getEvents().iterator();
			while (itera.hasNext()) {
				aux = itera.next();
				if (aux.collabExistence(vedetaName) && aux.collabExistence(targetName) && !aux.isOnHold()) {
					aux.suspendEvent();
					suspendCounter++;
				}
			}
			return suspendCounter;
		}
		return error;
	}

	@Override
	public int addPlace(String name, int cost) {
		int error = 0;
		if (places.alreadyExists(name))
			error = 1;
		else if (cost < 0)
			error = 2;
		else
			places.addPlace(name, cost);
		return error;
	}

	@Override
	public Iterator<PlaceClass> getPlaces() {
		return places.getPlaces();
	}

	@Override
	public int addEvent(String[] collabs, LocalDateTime start, int duration, String scenario) {
		int error = 0;
		Event current = new EventClass(convertCollabs(collabs), start, duration, places.getPlace(scenario));
		if (!places.alreadyExists(scenario))
			error = 1;
		else if (events.checkDate(start))
			error = 2;
		else if (duration <= 0)
			error = 3;
		else if (this.collabs.getCollabByName(collabs[0]) == null
				|| !(this.collabs.getCollabByName(collabs[0]) instanceof ProducerClass))
			error = 4;
		else if (this.collabs.getCollabByName(collabs[1]) == null
				|| !(this.collabs.getCollabByName(collabs[1]) instanceof DirectorClass
						|| this.collabs.getCollabByName(collabs[1]) instanceof AngryDirectorClass))
			error = 5;
		else if (this.collabs.getCollabByName(collabs[2]) == null
				|| !(this.collabs.getCollabByName(collabs[2]) instanceof TechnicianClass))
			error = 6;
		else if (!checkCollabs(collabs))
			error = 7;
		else if (checkEnemies(collabs)) {
			events.suspendEvent(start, places.getPlace(scenario));
			events.addEvent(current);
			error = 8;
		} else if (!(this.checkEventAvailability(current)))
			error = 9;
		else if (seniorClash(current)) {
			error = 10;
			events.addEvent(current);
		} else
			events.addEvent(current);
		return error;

	}

	private boolean checkCollabs(String[] collabs) {
		for (int i = 3; i < collabs.length; i++) {
			if (this.collabs.getCollabByName(collabs[i]) == null)
				return false;
		}
		return true;
	}

	private boolean checkEnemies(String[] collabs) {
		AbsCollaboratorClass current;
		for (int i = 0; i < collabs.length; i++) {
			current = this.collabs.getCollabByName(collabs[i]);
			for (int j = i; j < collabs.length; j++) {
				if (current instanceof AbsAngryCollab) {
					if (((AngryCollab) current).isAnEnemy(collabs[j]))
						return true;
				}
			}
		}
		return false;
	}

	private Array<AbsCollaboratorClass> convertCollabs(String[] collabs) {
		Array<AbsCollaboratorClass> aux = new ArrayClass<AbsCollaboratorClass>();
		for (int i = 0; i < collabs.length; i++) {
			aux.insertLast(this.collabs.getCollabByName(collabs[i]));
		}
		return aux;
	}

	private boolean checkEventAvailability(Event current) {
		Event aux;
		Iterator<Event> itera = events.getEvents().iterator();
		while (itera.hasNext()) {
			aux = itera.next();
			if (aux.getPlace().equals(current.getPlace())) {
				if (!(aux.getEnd().isBefore(current.getStart()) || aux.getStart().isAfter(current.getEnd()))) {
					return (((current.checkForSenior() && !(aux.checkForSenior()))
							|| (!(current.checkForSenior()) && aux.checkForSenior())) && current.sameCollab(aux));
				}
			} else if (!(aux.getEnd().isBefore(current.getStart()) || aux.getStart().isAfter(current.getEnd()))) {
				if (current.sameCollab(aux))
					return false;
			}
		}
		return true;
	}

	@Override
	public int removeEnemy(String vedetaName, String targetName) {
		Iterator<Event> itera;
		Event aux;
		int error = collabs.removeEnemy(vedetaName, targetName);
		int savedCounter = 0;
		if (error == 0) {
			itera = events.getEvents().iterator();
			while (itera.hasNext()) {
				aux = itera.next();
				if (aux.collabExistence(vedetaName) && aux.collabExistence(targetName) && noMoreEnemies(aux)) {
					aux.activateEvent();
					savedCounter++;
				}
			}
			return savedCounter;
		}
		return error;
	}

	private boolean noMoreEnemies(Event current) {
		AbsCollaboratorClass collab;
		Iterator<AbsCollaboratorClass> aux;
		Iterator<AbsCollaboratorClass> itera = current.getCollabs().iterator();
		while (itera.hasNext()) {
			collab = itera.next();
			if (collab instanceof AngryCollab) {
				aux = current.getCollabs().iterator();
				while (aux.hasNext()) {
					if (((AngryCollab) collab).isAnEnemy(aux.next().getName()))
						return false;
				}
			}
		}
		return true;
	}

	@Override
	public Array<AbsCollaboratorClass> getEnemies(String name) {
		return collabs.getEnemies(name);
	}

	@Override
	public Iterator<Event> getPastEventsIte() {
		Array<Event> origin = events.getPastEvents();
		sortEventList(origin);
		return origin.iterator();
	}

	@Override
	public Iterator<Event> getFutureEventsIte() {
		Array<Event> origin = events.getEvents();
		sortEventList(origin);
		return origin.iterator();
	}

	private void sortEventList(Array<Event> origin){
		Event aux;
		for (int i = 0; i < origin.size(); i++) {
			for (int j = i + 1; j < origin.size(); j++) {
				if (origin.get(i).getStart().isAfter(origin.get(j).getStart())) {
					aux = origin.get(j);
					origin.removeAt(j);
					origin.insertAt(origin.get(i), j);
					origin.removeAt(i);
					origin.insertAt(aux, i);
				}
			}
		}
	}

	@Override
	public boolean doesCollabExist(String name) {
		return collabs.getCollabByName(name) != null;
	}

	@Override
	public Iterator<Event> getPlaceEvents(String name) {
		Event aux;
		Array<Event> origin = new ArrayClass<Event>();
		Iterator<Event> itera = events.getEvents().iterator();
		while (itera.hasNext()) {
			aux = itera.next();
			if (aux.getPlace().equals(places.getPlace(name)))
				origin.insertLast(aux);
		}
		sortEventList(origin);
		return origin.iterator();
	}

	@Override
	public Iterator<Event> getCollabEvents(String name) {
		Event aux;
		Array<Event> origin = new ArrayClass<Event>();
		Iterator<Event> itera = events.getEvents().iterator();
		while (itera.hasNext()) {
			aux = itera.next();
			if (aux.collabExistence(name))
				origin.insertLast(aux);
		}
		sortEventList(origin);
		return origin.iterator();
	}

	@Override
	public Event doEvent() {
		if (events.getEvents().size() == 0)
			return null;
		else {
			events.doEvent();
			return events.getPastEvents().get(events.getPastEvents().size() - 1);
		}
	}

	@Override
	public boolean doesPlaceExist(String name) {
		return places.alreadyExists(name);
	}

	private boolean seniorClash(Event current) {
		Event aux;
		boolean result = false;
		Iterator<Event> itera = events.getEvents().iterator();
		while (itera.hasNext()) {
			aux = itera.next();
			if (aux.getPlace().equals(current.getPlace())) {
				if (!(aux.getEnd().isBefore(current.getStart()) || aux.getStart().isAfter(current.getEnd()))) {
					if (current.checkForSenior() && !(aux.checkForSenior()) && current.sameCollab(aux)) {
						postponeEvent(aux);
						result = true;
					} else if (aux.checkForSenior() && !(current.checkForSenior()) && current.sameCollab(aux)) {
						postponeEvent(current);
						result = true;
					}

				}
			}
		}
		return result;
	}

	private void postponeEvent(Event delayed) {
		Event aux;
		Iterator<Event> itera;
		boolean scheduled = false;
		while (!scheduled) {
			delayed.delayEvent(delayed.getStart().plusDays(1));
			itera = events.getEvents().iterator();
			while (itera.hasNext()) {
				aux = itera.next();
				if (aux.getEnd().isBefore(delayed.getStart()) || aux.getStart().isBefore(delayed.getEnd()))
					scheduled = true;
			}
		}
	}
}
