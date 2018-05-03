import generics.Array;
import generics.ArrayClass;
import generics.Iterator;

public class CollabListClass implements CollabList {
    public static final String SENIOR = "SENIOR";
    public static final String JUNIOR = "JUNIOR";
    public static final String DIRECTOR = "REALIZADOR";
    public static final String TECHNICIAN = "TECNICO";
    public static final String ACTOR = "ACTOR";
    public static final String VEDETA = "VEDETA";
    public static final String NORMAL = "NORMAL";
    private ArrayClass<AbsCollaboratorClass> collabs;

    public CollabListClass() {
        collabs = new ArrayClass<AbsCollaboratorClass>();
    }

    public String getName(int index) {
        return collabs.get(index).getName();
    }

    @Override
    public AbsCollaboratorClass getCollabByName(String name) {
        return searchByName(name);
    }

    @Override
    public void addCollaborator(String type, String status, int cost, String name) {
        switch (type) {
            case ACTOR:
                if (status.equals(VEDETA)) {
                    AngryActorClass actor = new AngryActorClass(name, cost);
                    collabs.insertLast(actor);
                } else {
                    ActorClass actor = new ActorClass(name, cost);
                    collabs.insertLast(actor);
                }
                break;
            case DIRECTOR:
                if (status.equals(VEDETA)) {
                    AngryDirectorClass director = new AngryDirectorClass(name, cost);
                    collabs.insertLast(director);
                } else {
                    DirectorClass director = new DirectorClass(name, cost);
                    collabs.insertLast(director);
                }
                break;
            case JUNIOR:
            case SENIOR:
                ProducerClass producer = new ProducerClass(name, cost, type);
                collabs.insertLast(producer);
                break;
            case TECHNICIAN:
                TechnicianClass linus = new TechnicianClass(name, cost);
                collabs.insertLast(linus);
                break;
            default:
        }

    }

    public AbsCollaboratorClass searchByName(String name) {
        AbsCollaboratorClass aux;
        Iterator<AbsCollaboratorClass> itera = collabs.iterator();
        while (itera.hasNext()) {
            aux = itera.next();
            if (aux.getName().equals(name))
                return aux;
        }
        return null;
    }

    @Override
    public Iterator<AbsCollaboratorClass> getIterator() {
        return collabs.iterator();
    }

    @Override
    public int addEnemy(String vedetaName, String targetName) {
        int error = 0;
        AbsCollaboratorClass current = searchByName(vedetaName);
        AbsCollaboratorClass target = searchByName(targetName);
        if (!(current instanceof AngryCollab))
            error = -1;
        else if (target == null)
            error = -2;
        else if (((AngryCollab) current).isAnEnemy(targetName))
            error = -3;
        else
            ((AngryCollab) current).addEnemy(target);
        return error;

    }

    @Override
    public int removeEnemy(String vedetaName, String targetName) {
        int error = 0;
        AbsCollaboratorClass current = searchByName(vedetaName);
        AbsCollaboratorClass target = searchByName(targetName);
        if (!(current instanceof AngryCollab))
            error = -1;
        else if (target == null || !((AngryCollab) current).isAnEnemy(targetName))
            error = -2;
        else
            ((AngryCollab) current).removeEnemy(target);
        return error;

    }

    @Override
    public Array<AbsCollaboratorClass> getEnemies(String name) {
        if (searchByName(name) == null || !(searchByName(name) instanceof AngryCollab))
            return null;
        else
            return ((AngryCollab) searchByName(name)).getEnemies();
    }
}
