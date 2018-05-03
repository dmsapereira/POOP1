public abstract class AbsCollaboratorClass implements Collaborator {
	private String name;
	private int salary;
	// EventList aqui

	public AbsCollaboratorClass(String name, int salary) {
		this.name = name;
		this.salary = salary; 
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getPay() {
		// TODO Auto-generated method stub
		return salary;
	}
}
