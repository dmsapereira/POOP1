

public class PlaceClass implements Place {
	private String name;
	private int cost;

	public PlaceClass(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getCost() {
		return cost;
	}
}
