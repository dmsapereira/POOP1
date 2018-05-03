import generics.*;

public class PlaceListClass implements PlaceList {
	private ArrayClass<PlaceClass> places;

	public PlaceListClass() {
		places = new ArrayClass<PlaceClass>();
	}

	@Override
	public boolean addPlace(String name, int cost) {
		PlaceClass newPlace;
		if (alreadyExists(name))
			return false;
		newPlace = new PlaceClass(name, cost);
		places.insertLast(newPlace);
		return false;
	}

	@Override
	public PlaceClass getPlace(String name) {
		for (int i = 0; i < places.size(); i++) {
			if (places.get(i).getName().equals(name))
				return places.get(i);
		}
		return null;
	}

	@Override
	public boolean alreadyExists(String name) {
		Iterator<PlaceClass> aux = places.iterator();
		while (aux.hasNext()) {
			if (aux.next().getName().equals(name))
				return true;
		}
		return false;
	}

	@Override
	public Iterator<PlaceClass> getPlaces() {
		return places.iterator();
	}

}
