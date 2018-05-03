public class ProducerClass extends AbsCollaboratorClass {
	private String reputation;

	public ProducerClass(String name, int cost, String rep) {
		super(name, cost);
		reputation = rep;
	}

	public String getRep() { 
		return reputation;
	}
}
