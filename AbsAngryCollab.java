import generics.*;

public abstract class AbsAngryCollab extends AbsCollaboratorClass implements AngryCollab {
	private Array<AbsCollaboratorClass> enemies;

	public AbsAngryCollab(String name, int salary) {
		super(name, salary);
		enemies = new ArrayClass<AbsCollaboratorClass>();
	}

	@Override
	public void addEnemy(AbsCollaboratorClass enemy) {
		enemies.insertLast(enemy);
	}

	@Override
	public void removeEnemy(AbsCollaboratorClass enemy) {
		enemies.remove(enemy);
	}

	@Override
	public boolean isAnEnemy(String name) {
		Iterator<AbsCollaboratorClass> itera = enemies.iterator();
		while (itera.hasNext()) {
			if (itera.next().getName().equals(name))
				return true;
		}
		return false;
	}

	@Override
	public Array<AbsCollaboratorClass> getEnemies() {
		return enemies;
	}

}
