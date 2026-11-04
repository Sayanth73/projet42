package unil.rush.domain;

import java.util.*;

import static unil.rush.domain.Faculty.HEC;


public class LeaderBoard {
    private List<Run> runs;
    private Map<UUID, Player> players;

    public LeaderBoard(List<Run> runs, Map<UUID, Player> players) {
        this.runs = runs;
        this.players = players;
    }

    public List<Run> top(int n)
    {
        List<Run> sorted = new ArrayList <>(this.runs);
        // .sort si la règle renvoie un nb + c'est arg2 qui passe devant si nb - c'est arg1
        sorted.sort((run1,run2) -> run2.getScore() - run1.getScore());
        List<Run> sortedn = new ArrayList <>();
        int i = 0;
        for (;i < n && i < sorted.size(); i++)
        {
            sortedn.add(sorted.get(i));
        }
        return(sortedn);
    }
    public List<Run> filterByFaculty(Faculty fac)
    {
        List<Run> empty = new ArrayList <>();
        for (int i = 0; i < this.runs.size(); i++)
        {
            Player player = players.get(runs.get(i).getPlayerID());
            if ((player != null && player.getFaculty() == fac))
                empty.add(this.runs.get(i));
        }
        return empty;
    }
    public List<Run> topByFaculty(Faculty fac, int n)
    {
        List<Run> empty = new ArrayList <>();
        empty = filterByFaculty(fac);
        LeaderBoard lead = new LeaderBoard(empty,this.players);
        return(lead.top(n));
    }

    public static void  main(String[] args)
    {
        Player player1 = new Player("Bigboss23", Faculty.HEC, "Noob0101010");
        Player player2 = new Player("bigboss2", Faculty.SSP, "SASASASASASAASA");
        System.out.println(player1.getPlayerID());
        player1.setPlayerID(UUID.randomUUID());
        player2.setPlayerID(UUID.randomUUID());
        System.out.println(player1.getPlayerID());
        Run run1 = new Run(player1.getPlayerID());
        Run run3 = new Run(player2.getPlayerID());
        run3.start();
        run3.setScore(9999);
        run1.start();
        Run run2 = new Run(player1.getPlayerID());
        run2.start();
        run2.setScore(1000);
        List<Run> leaderboard = new ArrayList<>();
        leaderboard.add(run1);
        leaderboard.add(run2);
        leaderboard.add(run3);
        Map<UUID, Player> players = new HashMap<>();
        players.put(player1.getPlayerID(),player1);
        players.put(player2.getPlayerID(),player2);
        LeaderBoard leaderBoard2 = new LeaderBoard(leaderboard, players);
        List<Run> top2 = leaderBoard2.topByFaculty(Faculty.SSP, 3);

        leaderBoard2.topByFaculty(HEC,3);
        for (Run run : top2) {
            System.out.println(run.getScore());
        }
        top2 = leaderBoard2.topByFaculty(Faculty.SSP, 3);
        for (Run run : top2) {
            System.out.println(run.getScore());
        }
        players.put(player2.getPlayerID(), player2);

        System.out.println("--- Top HEC ---");
        for (Run run : leaderBoard2.topByFaculty(Faculty.HEC, 3)) {
            System.out.println(run.getScore());
        }

        System.out.println("--- Top SSP ---");
        for (Run run : leaderBoard2.topByFaculty(Faculty.SSP, 3)) {
            System.out.println(run.getScore());
        }

        System.out.println("--- Top global ---");
        for (Run run : leaderBoard2.top(3)) {
            System.out.println(run.getScore());
        }

    }
}
