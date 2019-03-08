import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Main {

    public static void main(String[] args) {
        Map<String,String> mapgeneral;
        Map<String,String> mapusuario = new HashMap<>();
        Map<String,Integer> mapcant = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        //user selects what kind of implementation he wants to use
        String decision;
        int mapDecision = 0;

        boolean selectionOk = false;
        do {
            System.out.println("Por favor ingrese una de las tres opciones de Mapa");
            System.out.println("1. HashMap");
            System.out.println("2. TreeMap");
            System.out.println("3. Linked Hash Map");
            decision = scanner.nextLine();
            try {
                mapDecision = Integer.parseInt(decision);
            } catch (NumberFormatException  e){
                System.out.println("Por favor ingrese un número de la lista");
            }
            if (mapDecision > 0 && mapDecision < 4){
                selectionOk = true;
            }
        } while (!selectionOk);

        //Now that the user has requested the map he wants, mapgeneral will have a Map instance

        //TODO: Por conveniencia, cuando el usuario selecciona un tipo de mapa ese se
        //TODO: para los tres mapas que maneja el programa
        mapgeneral = Factory.getMap("HASHMAP");
        switch (mapDecision){
            case 1:
                mapgeneral = Factory.getMap("HASHMAP");
                mapusuario = Factory.getMap("HASHMAP");
                mapcant = Factory.getMapCant("HASHMAP");
                break;
            case 2:
                mapgeneral = Factory.getMap("TREEMAP");
                mapusuario = Factory.getMap("TREEMAP");
                mapcant = Factory.getMapCant("TREEMAP");
                break;
            case 3:
                mapgeneral = Factory.getMap("LINKEDHASHMAP");
                mapusuario = Factory.getMap("LINKEDHASHMAP");
                mapcant = Factory.getMapCant("LINKEDHASHMAP");
                break;
            default:
                //do Nothing
                break;
        }

        //Para este punto en JFileChooser el archivo ya se debió haber cargado y guardado en el Map,
        //Por cuestiones prácticas voy a añadir dummy data al mapgeneral
        //TODO: Hay que borrar esto
        assert mapgeneral != null;
        mapgeneral.put("M1", "Monstruo");
        mapgeneral.put("M2", "Hechizo");
        mapgeneral.put("M3", "Trampa");
        mapgeneral.put("M4", "Trampa");
        mapgeneral.put("M5", "Trampa");
        mapgeneral.put("M6", "Monstruo");
        mapgeneral.put("M7", "Hechizo");


        String selectionString;
        int selection = -1;
        boolean wantsToContinue = true;
        do {
            System.out.println("Por favor ingrese una opción de la lista");

            selectionString = scanner.nextLine();

            try{
                selection = Integer.parseInt(selectionString);

            } catch (NumberFormatException e){
                System.out.println("Por favor ingrese un número entero");
            }

            if (selection > 0 && selection < 8){
                switch (selection){
                    case 1:
                        //user inputs the key of the desired card
                        System.out.println("Ingrese el nombre de la carta que desea agregar: ");
                        String cardToAddKey = scanner.nextLine();
                        //if mapgeneral contains the key
                        if (mapgeneral.containsKey(cardToAddKey)){
                            assert mapusuario != null;
                            //add the card to the mapusuario map
                            mapusuario.put(cardToAddKey, mapgeneral.get(cardToAddKey));
                            //add 1 to the qty of cards with that key, to the mapcant
                            assert mapcant != null;
                            //if it is not the first time the card is added to the usermap
                            if (mapcant.containsKey(cardToAddKey)){
                                //add 1 to the counter of cards with the specified cardToAddKey
                                int actualCount = mapcant.get(cardToAddKey);
                                mapcant.put(cardToAddKey, actualCount+1);
                            } else {
                                //if it's the first time the card is added, just add 1 to the counter
                                mapcant.put(cardToAddKey, 1);
                            }
                        } else{
                            //card with specified cardToAddKey is not on mapgeneral
                            System.out.println("La carta seleccionada no se encuentra en el deck de cartas");
                        }
                        break;
                    case 2:
                        //User wants to search a card by Key
                        System.out.println("Ingrese el nombre de la carta de la que desea saber el tipo");
                        String cardToShowType = scanner.nextLine();
                        if (mapgeneral.containsKey(cardToShowType)){
                            System.out.println("La carta " + cardToShowType + " es de tipo: " + mapgeneral.get(cardToShowType) );
                        } else {
                            System.out.println("La carta no existe");
                        }
                        break;
                    case 3:
                        //user wants to show the cards in mapusuario, unsorted
                        System.out.println("A continuación se muestran las cartas del usuario, de manera desordenada");
                        //create iterator to "iterate" over the keys of the mapusuario object
                        Iterator<Map.Entry<String, String>> userMapIterator = mapusuario.entrySet().iterator();
                        //while there´s still keys in the mapusuario Object
                        while (userMapIterator.hasNext()) {
                            //Save the pair as an Map.Entry Object
                            Map.Entry<String, String> pair = userMapIterator.next();

                            System.out.println(pair.getKey() + " | " + pair.getValue() + " Cantidad: " + mapcant.get(pair.getKey()) );
                        }
                        break;
                    case 4:
                        //user wants to show usermap vaules sorted by type
                        System.out.println("A continuación se muestran las cartas del usuario, ordenadas por tipo");
                        //instance of a Map type object sorted
                        Map<String, String> sortedUserMap = mapusuario
                                //access the entry Set
                                .entrySet()
                                //as a stream
                                .stream()
                                //sort with the comparingByValue Method
                                .sorted(comparingByValue())
                                .collect(
                                        //collect all the entries and set them in the LinkedHashMap object
                                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                                LinkedHashMap::new));
                        //TODO: ahora se puede iterar sobre el mapa sortedUserMap como lo hice en la opción 3 (con un Iterator).
                        //TODO: Es exactamente la misma estructura y puedes accesar a las entradas del mapcant, todo esto mediante el while que usé mas arriba (opción 3)
                        System.out.println(sortedUserMap);

                        break;
                    case 5:
                        //user wants to show the cards in mapgeneral, unsorted
                        System.out.println("A continuación se muestran las cartas del deck, desordenadas");
                        //create iterator to "iterate" over the keys of the mapusuario object
                        Iterator<Map.Entry<String, String>> cardsMapIterator = mapgeneral.entrySet().iterator();
                        //while there´s still keys in the mapusuario Object
                        while (cardsMapIterator.hasNext()) {
                            //save the pair as a Map.Entry object
                            Map.Entry<String, String> pair = cardsMapIterator.next();
                            System.out.println(pair.getKey() + " | " + pair.getValue());
                        }
                        break;
                    case 6:
                        //user wants to show mapgeneral vaules sorted by type
                        System.out.println("A continuación se muestran las cartas del deck, ordenadas por tipo");
                        //instance of a Map type object sorted

                        Map<String, String> sortedCardMap = mapgeneral

                                //access the entry Set
                                .entrySet()
                                //as a stream
                                .stream()
                                //sort with the comparingByValue Method
                                .sorted(comparingByValue())
                                .collect(
                                        //collect all the entries and set them in the LinkedHashMap object
                                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                                LinkedHashMap::new));
                        System.out.println(sortedCardMap);
                        //TODO: ahora se puede iterar sobre el mapa sortedCardMap como lo hice en la opción 3 (con un Iterator).
                        //TODO: Es exactamente la misma estructura y puedes accesar a las entradas del mapcant, todo esto mediante el while que usé mas arriba (opción 3)
                        break;
                    case 7:
                        wantsToContinue = false;
                        break;
                    default:
                        //do Nothing
                        break;
                }
            }
        } while (wantsToContinue);
    }




}
