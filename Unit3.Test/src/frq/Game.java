package frq;

public class Game {
    // The name of the winner of this game 
    // or the initial participant in the tournament.
    private String _winner;
    // The references to the previous games of the competitors 
    // or null for the initial participant in the tournament.
    private Game _prev1, _prev2;
    
    /* other data or members may exists but are not relevant */
    public Game(String teamName) {
        _winner = teamName;
        _prev1 = null;
        _prev2 = null;
    }
    
    /**
     * Returns the previous qualification games,
     * @return - array of two Game references.
     */
    public Game[] getPrevGames() {
        return new Game[] {_prev1, _prev2};
    }
    
    public Game addLeft(String teamLeft) {
        _prev1 = new Game(teamLeft);
        return _prev1;
    }
    
    public Game addRight(String teamRight) {
        _prev2 = new Game(teamRight);
        return _prev2;
    }
    
    public String toString(String indent) {
        String output = indent + _winner;
        if (_prev1 != null) {
            output += "\n" + _prev1.toString(indent + "  ");
        }
        
        if (_prev2 != null) {
            output += "\n" + _prev2.toString(indent + "  ");
        }
        
        return output;
    }
    
    public void checkValid() throws RuntimeException {
        if (_prev1 == null && _prev2 == null) {
            return;
        }
        if (_prev1 == null || _prev2 == null) {
            throw new RuntimeException("Missing one qualifying game!");
        }
        _prev1.checkValid();
        _prev2.checkValid();
        
        int count = 0;
        if (_winner.equals(_prev1._winner)) {
            count++;
        }
        if (_winner.equals(_prev2._winner)) {
            count++;
        }
        
        if (count != 1) {
            throw new RuntimeException("Invalid winner given qualifying games!");
        }
    }
    
    public int countGames() {
        return (_prev1 == null && _prev2 == null) 
                ? 0 
                : 1 + _prev1.countGames() + _prev2.countGames();
    }
    
    public int countTeams() {
        return (_prev1 == null && _prev2 == null)
                ? 1
                : _prev1.countTeams() + _prev2.countTeams();
    }
    
    public int countGames(String team) {
        int count = _winner.equals(team) ? 1 : 0;
        if (_prev1 != null) {
            count += _prev1.countGames(team);
        }
        if (_prev2 != null) {
            count += _prev2.countGames(team);
        }
        return count;
    }
}
