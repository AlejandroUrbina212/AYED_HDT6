/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package HDT6;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author DIANA
 */
public class Factory {
    public Map<String,String> getMap(String map){
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
    public Map<String,Integer> getMapCant(String map){
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
