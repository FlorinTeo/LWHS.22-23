package frq;

public class Pokemon {
    
    // class fields exist but not relevant for this problem
    private String _name;
    private String _rarity;
    
    public Pokemon(String name, String rarity) {
        _name = name;
        _rarity = rarity;
    }
    
    // returns the name of this Pokemon, i.e: "Pikachu"
    public String getName() {
        return _name;
    }

    // returns the rarity of this Pokemon, i.e: "Common"
    public String getRarity() {
        return _rarity;
    }

    // other class methods exist but not relevant for this problem
    @Override
    public String toString() {
        return _name + " : " + _rarity;
    }
}
