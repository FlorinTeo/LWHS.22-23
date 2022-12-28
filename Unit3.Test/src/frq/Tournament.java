package frq;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Tournament {
    /**
     * The chart of games played in this Tournament
     * (binary tree, with the final game in the root) 
     */
    private Game _chart;
    
    /**
     * Initializes a tournament brackets board.
     * @param teams - teams participating to the tournament.
     */
    public Tournament(String... teams) {
        if (teams.length == 0) {
            _chart = null;
            throw new RuntimeException("Invalid/Missing Tournament data");
        }
        
        Queue<String> teamQueue = new LinkedList<String>();
        teamQueue.addAll(Arrays.asList(teams));
        
        _chart = new Game(teamQueue.remove());
        Queue<Game> gameQueue = (Queue<Game>)new LinkedList<Game>();
        gameQueue.add(_chart);
        
        while(gameQueue.size() > 0) {
            Game game = gameQueue.remove();
            if (teamQueue.size() > 0) {
                gameQueue.add(game.addLeft(teamQueue.remove()));
            }
            if (teamQueue.size() > 0) {
                gameQueue.add(game.addRight(teamQueue.remove()));
            }
        }
    }
    
    /**
     * Returns the string representation of this tournament scoring board. 
     */
    @Override
    public String toString() {
        return _chart.toString("");
    }
    
    /**
     * (a) Checks whether the tournament is valid:
     * There's a non-null chart and the root (final) game in the chart is valid.
     */
    public void checkValid() {
        if (_chart == null) {
            throw new RuntimeException("Tournament not initialized!");
        }
        _chart.checkValid();
    }
    
    /**
     * (b) Provide the total number of games played in the tournament
     */
    public int countGames() {
        checkValid();
        return _chart.countGames();
    }
    
    /**
     * (c) Provide the total number of teams participating in the tournament.
     */
    public int countTeams() {
        checkValid();
        return _chart.countTeams();
    }
    
    /**
     * (d) Given the name of a participating team, 
     * provide the total number of games played by that team in the tournament.
     */
    public int countGames(String team) {
        checkValid();
        return _chart.countGames(team);
    }
}
