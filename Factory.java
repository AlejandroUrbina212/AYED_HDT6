import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to create a different list depending on the user´s request (as a string)
 * @author DIANA
 */
public class Factory {
    /**
     * Returns an instance of the map selected by the user (as a String)
     * @param map the string of the map the user will choose
     * @return one of three posible instance of a Map-type Object
     */
    public static Map<String,String> getMap(String map){
        switch (map) {
            case "HASHMAP":
                return new HashMap<>();
            case "TREEMAP":
                return new TreeMap<>();
            case "LINKEDHASHMAP":
                return new LinkedHashMap<>();
            default:
                break;
        }
        return null;
    }

    /**
     * Returns an instance of a map for control of the quantity of cards the user has
     * @param map the string of the desired map
     * @return the instance of the Map-type object according to the param
     */
    public static Map<String,Integer> getMapCant(String map){
        switch (map) {
            case "HASHMAP":
                return new HashMap<>();
            case "TREEMAP":
                return new TreeMap<>();
            case "LINKEDHASHMAP":
                return new LinkedHashMap<>();
            default:
                break;
        }
        return null;
    }
}
