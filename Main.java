import java.time.LocalDateTime;
import java.util.Scanner;

import generics.Iterator;


/**
 * @author 52886- Bruno Ramos
 * 52890- David Pereia
 */
public class Main {

    private static final String FORMAT_DATE = "%d %d %d";
    private static final String FORMAT_DATE_TIME = "%d %d %d %d %d\n";

    public static final String AJUDA = "AJUDA";
    public static final String EXIT = "SAI";
    public static final String REGISTA = "REGISTA";
    public static final String STAFF = "STAFF";
    public static final String AMUA = "AMUA";
    public static final String RECON = "RECONCILIA";
    public static final String AMUANCOS = "AMUANCOS";
    public static final String MARCA = "MARCA";
    public static final String GRAVA = "GRAVA";
    public static final String LOCAL = "LOCAL";
    public static final String COLABORADOR = "COLABORADOR";
    public static final String REALIZADAS = "REALIZADAS";
    public static final String PREVISTAS = "PREVISTAS";
    public static final String ACTOR = "ACTOR";
    public static final String DIRECTOR = "REALIZADOR";
    private static final String SCENARIOS = "CENARIOS";
    private static final String SCENARIO = "CENARIO";

    /**
     * Le o input do utilizador e escolhe a operacao
     * @return o input do utilizador
     */
    private static String readCommand(Scanner in, PlannerClass system) {
        String option;
        option = in.next().toUpperCase();
        in.nextLine();
        switch (option) {
            case AJUDA:
                printHelp();
                break;
            case REGISTA:
                registerWorker(in, system);
                break;
            case STAFF:
                printStaff(system);
                break;
            case AMUA:
                amua(in, system);
                break;
            case RECON:
                reconcile(in, system);
                break;
            case AMUANCOS:
                listBl(in, system);
                break;
            case MARCA:
                schedule(in, system);
                break;
            case GRAVA:
                record(system);
                break;
            case SCENARIOS:
                listPlaces(system);
                break;
            case SCENARIO:
                createPlace(in, system);
                break;
            case PREVISTAS:
                listFuture(system);
                break;
            case REALIZADAS:
                listaPast(system);
                break;
            case COLABORADOR:
                listColabSched(in, system);
                break;
            case LOCAL:
                listLocalSched(in, system);
                break;

            default:

        }
        return option;

    }

    /**
     * Executa a operacao de GRAVA
     */
    private static void record(PlannerClass system) {
        Event aux = system.doEvent();
        if (aux == null)
            System.out.println("Nenhuma gravacao agendada.");
        else {
            System.out.printf(FORMAT_DATE, aux.getStart().getYear(), aux.getStart().getMonthValue(),
                    aux.getStart().getDayOfMonth());
            System.out.print("; " + aux.getPlace().getName() + "; " + aux.getProd().getName() + "; "
                    + aux.getDir().getName() + ". ");
            if (aux.isOnHold())
                System.out.println("Cancelada!");
            else
                System.out.println("Gravada!");
        }
    }

    /**
     * Lista os eventos de um local, organizados cronologicamente
     */
    private static void listLocalSched(Scanner in, PlannerClass system) {
        String scenario = in.nextLine().trim();
        Iterator<Event> itera = system.getPlaceEvents(scenario);
        Event aux;
        int sum = 0;
        if (!system.doesPlaceExist(scenario))
            System.out.println("Local desconhecido.");
        else if (!itera.hasNext())
            System.out.println("Nenhuma gravacao prevista em " + scenario + ".");
        else {
            while (itera.hasNext()) {
                aux = itera.next();
                System.out.printf(FORMAT_DATE, aux.getStart().getYear(), aux.getStart().getMonthValue(),
                        aux.getStart().getDayOfMonth());
                System.out.println("; " + aux.getProd().getName() + "; " + aux.getDir().getName() + ".");
                sum += aux.getBudget();
            }
            System.out.println(sum + " euros orcamentados.");
        }
    }

    /**
     * Lista os eventos de um colaborador
     */
    private static void listColabSched(Scanner in, PlannerClass system) {
        String collab = in.nextLine().trim();
        Iterator<Event> itera = system.getCollabEvents(collab);
        Event aux;
        int sum = 0;
        if (!system.doesCollabExist(collab))
            System.out.println("Colaborador desconhecido.");
        else if (!itera.hasNext())
            System.out.println("Nenhuma gravacao prevista com " + collab + ".");
        else {
            while (itera.hasNext()) {
                aux = itera.next();
                if (aux.collabExistence(collab)) {
                    System.out.printf(FORMAT_DATE, aux.getStart().getYear(), aux.getStart().getMonthValue(),
                            aux.getStart().getDayOfMonth());
                    System.out.println("; " + aux.getProd().getName() + "; " + aux.getDir().getName() + ".");
                    sum += aux.getBudget();
                }

            }
            System.out.println(sum + " euros orcamentados.");
        }
    }

    /**
     * Lista os eventos que ja foram gravados
     */
    private static void listaPast(PlannerClass system) {
        Iterator<Event> itera = system.getPastEventsIte();
        Event aux;
        int sum = 0;
        if (!itera.hasNext())
            System.out.println("Nenhuma gravacao realizada.");
        else {
            while (itera.hasNext()) {
                aux = itera.next();
                System.out.printf(FORMAT_DATE, aux.getStart().getYear(), aux.getStart().getMonthValue(),
                        aux.getStart().getDayOfMonth());
                System.out.print("; " + aux.getPlace().getName() + "; " + aux.getProd().getName() + "; "
                        + aux.getDir().getName() + ".");
                if (aux.isOnHold())
                    System.out.println(" Cancelada!");
                else {
                    System.out.println();
                    sum += aux.getBudget();
                }
            }
            System.out.println(sum + " euros gastos.");
        }
    }

    /**
     * Lista os eventos previstos
     */
    private static void listFuture(PlannerClass system) {
        Iterator<Event> itera = system.getFutureEventsIte();
        Event aux;
        int sum = 0;
        if (!itera.hasNext())
            System.out.println("Nenhuma gravacao prevista.");
        else {
            while (itera.hasNext()) {
                aux = itera.next();
                System.out.printf(FORMAT_DATE, aux.getStart().getYear(), aux.getStart().getMonthValue(),
                        aux.getStart().getDayOfMonth());
                System.out.print("; " + aux.getPlace().getName() + "; " + aux.getProd().getName() + "; "
                        + aux.getDir().getName() + ".");
                if (aux.isOnHold())
                    System.out.println(" Suspensa!");
                else
                    System.out.println();
                sum += aux.getBudget();

            }
            System.out.println(sum + " euros orcamentados.");
        }
    }

    /**
     * Lista os locais de gravacao
     */
    private static void listPlaces(PlannerClass system) {
        Iterator<PlaceClass> itera = system.getPlaces();
        if (!itera.hasNext()) {
            System.out.println("Nao existem localizacoes registadas.");
        } else {
            Place aux;
            while (itera.hasNext()) {
                aux = itera.next();
                System.out.println(aux.getName() + " " + aux.getCost() + ".");
            }
        }

    }

    /**
     * Cria um novo local de gravacao
     */
    private static void createPlace(Scanner in, PlannerClass system) {
        String name = in.nextLine().trim();
        int cost = in.nextInt();
        switch (system.addPlace(name, cost)) {
            case 0:
                System.out.println("Cenario registado.");
                break;
            case 1:
                System.out.println("Localizacao ja tinha sido registada.");
                break;
            case 2:
                System.out.println("Acha que eles nos pagam para gravar la?");
                break;
            default:
        }

    }

    /**
     * Marca um novo evento
     */
    private static void schedule(Scanner in, PlannerClass system) {
        int year, month, day, hr, min;
        int dur;
        String[] collabs;
        LocalDateTime start;
        String place = in.nextLine().trim();
        year = in.nextInt();
        month = in.nextInt();
        day = in.nextInt();
        hr = in.nextInt();
        min = in.nextInt();
        dur = in.nextInt();
        in.nextLine();
        start = LocalDateTime.of(year, month, day, hr, min);
        String producer = in.nextLine().trim();
        String director = in.nextLine().trim();
        String technician = in.nextLine().trim();
        int nCollabs = in.nextInt();
        in.nextLine();
        collabs = new String[3 + nCollabs];
        collabs[0] = producer;
        collabs[1] = director;
        collabs[2] = technician;
        for (int i = 3; i < nCollabs + 3; i++) {
            collabs[i] = in.nextLine().trim();
        }
        switch (system.addEvent(collabs, start, dur, place)) {
            case 1:
                System.out.println("Local desconhecido.");
                break;
            case 2:
                System.out.println("Data de gravacao invalida.");
                break;
            case 3:
                System.out.println("Duracao invalida.");
                break;
            case 4:
                System.out.println("Produtor desconhecido.");
                break;
            case 5:
                System.out.println("Realizador desconhecido.");
                break;
            case 6:
                System.out.println("Tecnico desconhecido.");
                break;
            case 7:
                System.out.println("Colaborador desconhecido.");
                break;
            case 8:
                System.out.println("Gravacao pendente de uma birra.");
                break;
            case 9:
                System.out.println("Gravacao nao agendada por conflito de datas.");
                break;
            case 10:
                System.out.println("Gravacao prioritaria agendada provocou mudancas noutra(s) gravacao(oes).");
                break;
            case 0:
                System.out.println("Gravacao agendada com sucesso!");
                break;
            default:
        }

    }

    /**
     * Adiciona um inimigo a um vedeta
     */
    private static void amua(Scanner in, PlannerClass system) {
        String vedeta, target;
        vedeta = in.nextLine().trim();
        target = in.nextLine().trim();
        int error = system.addEnemy(vedeta, target);
        switch (error) {
            case -1:
                System.out.println(vedeta + " nao e uma vedeta.");
                break;
            case -2:
                System.out.println(target + " nao e um colaborador.");
                break;
            case -3:
                System.out.println("Que falta de paciencia para divas...");
                break;
            default:
                System.out.println(
                        vedeta + " colocou " + target + " na sua lista negra, suspendendo " + error + " gravacoes.");
                break;
        }
    }

    /**
     * Lista os inimigos de um vedeta
     */
    private static void listBl(Scanner in, PlannerClass system) {
        String vedetaName = in.nextLine().trim();
        if (system.getEnemies(vedetaName).iterator() == null)
            System.out.println("Mas quem e " + vedetaName + ".");
        else {
            Iterator<AbsCollaboratorClass> itera = system.getEnemies(vedetaName).iterator();
            while (itera.hasNext())
                System.out.println(itera.next().getName());
        }
    }

    /**
     * Reconcilia o vedeta com um dos seus inimigos
     */
    private static void reconcile(Scanner in, PlannerClass system) {
        String vedetaName = in.nextLine().trim();
        String targetName = in.nextLine().trim();
        int error = system.removeEnemy(vedetaName, targetName);
        switch (error) {
            case -1:
                System.out.println(vedetaName + " nao e uma vedeta.");
                break;
            case -2:
                System.out.println("Nao existe zanga com " + targetName + ".");
                break;
            default:
                System.out.println(vedetaName + " <3 " + targetName + ". " + error + " gravacoes salvas!");
        }
    }

    /**
     * Regista um novo colaborador
     */
    private static void registerWorker(Scanner in, PlannerClass system) {
        String status = "";
        String type = in.next().toUpperCase();
        if (type.equals(ACTOR) || type.equals(DIRECTOR))
            status = in.next().toUpperCase();
        int cost = in.nextInt();
        String name = in.nextLine().trim();
        switch (system.addWorker(type, status, cost, name)) {
            case 0:
                System.out.println("Colaborador registado com sucesso!");
                break;
            case 1:
                System.out.println("Ja existe um colaborador com o mesmo nome.");
                break;
            case 2:
                System.out.println("Tipo de colaborador desconhecido.");
                break;
            case 3:
                System.out.println("Notoriedade invalida.");
                break;
            case 4:
                System.out.println("Acha mesmo que este colaborador vai pagar para trabalhar?");
                break;
            default:

        }

    }

    /**
     * Lista todos os colaboradores
     */
    private static void printStaff(PlannerClass system) {
        Iterator<AbsCollaboratorClass> itera = system.getCollabIterator();
        if (!itera.hasNext())
            System.out.println("Nao existem colaboradores registados.");
        else {
            while (itera.hasNext()) {
                AbsCollaboratorClass current = itera.next();
                if (current instanceof ActorClass)
                    System.out.print("actor normal ");
                else if (current instanceof AngryActorClass)
                    System.out.print("actor vedeta ");
                else if (current instanceof DirectorClass)
                    System.out.print("realizador normal ");
                else if (current instanceof AngryDirectorClass)
                    System.out.print("realizador vedeta ");
                else if (current instanceof TechnicianClass)
                    System.out.print("tecnico ");
                else if (current instanceof ProducerClass) {
                    System.out.print("produtor " + ((ProducerClass) current).getRep().toLowerCase() + " ");
                }
                System.out.println(current.getName() + " " + current.getPay());
            }
        }

    }

    /**
     * Faz print aos comandos disponiveis
     */
    private static void printHelp() {
        System.out.println("regista - regista um novo colaborador\r\n" + "staff - lista os colaboradores registados\r\n"
                + "cenario - regista um novo local para gravacoes\r\n"
                + "cenarios - lista os locais para gravacoes registados\r\n" + "marca - marca uma nova gravacao\r\n"
                + "amua - vedeta deixa de trabalhar com colaborador\r\n"
                + "reconcilia - vedeta faz as pazes com colaborador\r\n"
                + "realizadas - lista as gravacoes realizadas\r\n" + "previstas - lista as gravacoes previstas\r\n"
                + "local - lista as gravacoes previstas para um local\r\n"
                + "colaborador - lista as gravacoes previstas para um colaborador\r\n"
                + "grava - executa a proxima gravacao agendada\r\n"
                + "amuancos - lista os colaboradores com quem uma vedeta esta amuada\r\n" + "ajuda - Mostra a ajuda\r\n"
                + "sai - Termina a execucao do programa");

    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        PlannerClass system = new PlannerClass();
        String option;
        do {
            System.out.print("> ");
            option = readCommand(in, system);
        } while (!option.equals(EXIT));
        System.out.println("Ate a proxima");
        in.close();

    }

}
