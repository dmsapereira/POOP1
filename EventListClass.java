
import java.time.LocalDateTime;

import generics.*;

public class EventListClass implements EventList {
    private Array<Event> events;
    private Array<Event> pastEvents;

    public EventListClass() {
        events = new ArrayClass<Event>();
        pastEvents = new ArrayClass<Event>();
    }

    @Override
    public void addEvent(Event newEvent) {
        events.insertLast(newEvent);
    }

    @Override
    public void suspendEvent(LocalDateTime start, PlaceClass place) {
        Iterator<Event> aux = events.iterator();
        while (aux.hasNext()) {
            Event eventAux = aux.next();
            if (eventAux.getStart().isEqual(start) && (eventAux.getPlace().equals(place)))
                eventAux.suspendEvent();

        }

    }

    @Override
    public Array<Event> getPastEvents() {
        return pastEvents;
    }

    @Override
    public Array<Event> getEvents() {
        return events;
    }

    @Override
    public void doEvent() {
        int i = 0;
        int tracker;
        LocalDateTime min;
        Event aux;
        Iterator<Event> itera = events.iterator();
        min = itera.next().getStart();
        tracker = 0;
        while (itera.hasNext()) {
            aux = itera.next();
            if (aux.getStart().isBefore(min)) {
                min = aux.getStart();
                tracker = i;
            }
            i++;
        }
        pastEvents.insertLast(events.get(tracker));
        events.removeAt(tracker);
    }

    @Override
    public boolean checkDate(LocalDateTime date) {
        if (pastEvents.get(0) == null)
            return false;
        else
            return (pastEvents.get(pastEvents.size() - 1).getEnd().isAfter(date));
    }

}
