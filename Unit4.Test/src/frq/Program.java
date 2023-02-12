package frq;
import java.util.*;

public class Program {

    /**
     * Adds a given Pokemon to the rarity map
     * @param map - rarity map.
     * @param pokemon - pokemon to be added to the map.
     */
    public static void
    addPokemon(Map<String, Map<String, ArrayList<Pokemon>>> map, Pokemon pokemon) {
        Map<String, ArrayList<Pokemon>> rarityGroup = map.get(pokemon.getRarity());
        // if a rarityGroup had not been defined for this rarity..
        if (rarityGroup == null) {
            // ..build it now and map it to the pokemon's rarity
            rarityGroup = new TreeMap<String, ArrayList<Pokemon>>();
            map.put(pokemon.getRarity(), rarityGroup);
        }
        // here the rarityGroup is guaranteed to exist
        // if a nameGroup had not been defined for this name..
        ArrayList<Pokemon> nameGroup = rarityGroup.get(pokemon.getName());
        if (nameGroup == null) {
            // ..build it now and map it to the pokemon's name
            nameGroup = new ArrayList<Pokemon>();
            rarityGroup.put(pokemon.getName(), nameGroup);
        }
        // here the nameGroup is guaranteed to exist
        // Just add the pokemon to this group
        nameGroup.add(pokemon);
    }
    
    /**
     * Builds a rarity map from a given list of Pokemons.
     * @param pokemons - list of Pokemons to be mapped.
     * @return map of Pokemons organized by rarity first, then by name.
     */
    public static Map<String, Map<String, ArrayList<Pokemon>>> 
    rarityMap(ArrayList<Pokemon> pokemons) {
        Map<String, Map<String, ArrayList<Pokemon>>> map = 
                new HashMap<String, Map<String, ArrayList<Pokemon>>>();
        for(Pokemon pokemon : pokemons) {
            addPokemon(map, pokemon);
        }
        return map;
    }
    
    public static void main(String[] args) {
        Pokemon p1 = new Pokemon("Pikachu", "Common");
        Pokemon p2 = new Pokemon("Bulbasaur", "Rare");
        Pokemon p3 = new Pokemon("Bulbasaur", "Common");
        Pokemon p4 = new Pokemon("Bulbasaur", "Common");
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>(Arrays.asList(p1, p2, p3, p4));
        Map<String, Map<String, ArrayList<Pokemon>>> map = rarityMap(pokemons);
        System.out.println(map);
    }
}
