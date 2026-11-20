import java.util.*;
public class PopulationDirectorySystem {

    private final Map<String, Map<String, Long>> directory;

    public PopulationDirectorySystem() {
        this.directory = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        prepopulateSampleData();
    }

    private void prepopulateSampleData() {
        addOrUpdateCityDirect("Andhra Pradesh", "Visakhapatnam", 2358000);
        addOrUpdateCityDirect("Andhra Pradesh", "Vijayawada", 1143000);
        addOrUpdateCityDirect("Andhra Pradesh", "Guntur", 743000);

        addOrUpdateCityDirect("Maharashtra", "Mumbai", 12442373);
        addOrUpdateCityDirect("Maharashtra", "Pune", 3124458);
        addOrUpdateCityDirect("Maharashtra", "Nagpur", 2405665);

        addOrUpdateCityDirect("Karnataka", "Bengaluru", 8443675);
        addOrUpdateCityDirect("Karnataka", "Mysuru", 920550);

        addOrUpdateCityDirect("Tamil Nadu", "Chennai", 7088000);
        addOrUpdateCityDirect("Tamil Nadu", "Coimbatore", 1601438);
    }

    private void addOrUpdateCityDirect(String stateName, String cityName, long population) {
        directory.putIfAbsent(stateName.trim(), new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
        directory.get(stateName.trim()).put(cityName.trim(), population);
    }

    public void addState(String stateName) {
        if (stateName == null || stateName.trim().isEmpty()) {
            System.out.println("[Error] State name cannot be blank.");
            return;
        }
        stateName = stateName.trim();
        if (directory.containsKey(stateName)) {
            System.out.println("[Alert] State '" + stateName + "' already exists in directory.");
        } else {
            directory.put(stateName, new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
            System.out.println("[Success] State '" + stateName + "' successfully added!");
        }
    }

    public void addOrUpdateCity(String stateName, String cityName, long population) {
        if (stateName == null || cityName == null || stateName.trim().isEmpty() || cityName.trim().isEmpty()) {
            System.out.println("[Error] State name and City name cannot be empty.");
            return;
        }
        if (population < 0) {
            System.out.println("[Error] Population cannot be negative.");
            return;
        }

        stateName = stateName.trim();
        cityName = cityName.trim();

        directory.putIfAbsent(stateName, new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
        Map<String, Long> cityMap = directory.get(stateName);

        boolean isUpdate = cityMap.containsKey(cityName);
        cityMap.put(cityName, population);

        if (isUpdate) {
            System.out.println("[Updated] Successfully updated population of '" + cityName + "' under '" + stateName + "' to " + String.format("%,d", population) + ".");
        } else {
            System.out.println("[Added] Successfully registered '" + cityName + "' under '" + stateName + "' with population " + String.format("%,d", population) + ".");
        }
    }

    public void getCityPopulation(String stateName, String cityName) {
        if (stateName == null || cityName == null || stateName.trim().isEmpty() || cityName.trim().isEmpty()) {
            System.out.println("[Error] Invalid state or city name.");
            return;
        }

        stateName = stateName.trim();
        cityName = cityName.trim();

        if (!directory.containsKey(stateName)) {
            System.out.println("[Not Found] State '" + stateName + "' does not exist in the directory.");
            return;
        }

        Map<String, Long> cityMap = directory.get(stateName);
        if (!cityMap.containsKey(cityName)) {
            System.out.println("[Not Found] City '" + cityName + "' is not registered under '" + stateName + "'.");
            return;
        }

        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("State      : " + stateName);
        System.out.println("City       : " + cityName);
        System.out.printf("Population : %,d%n", cityMap.get(cityName));
        System.out.println("------------------------------------------------");
    }

    public void getStateSummary(String stateName) {
        if (stateName == null || stateName.trim().isEmpty()) {
            System.out.println("[Error] State name cannot be blank.");
            return;
        }

        stateName = stateName.trim();
        if (!directory.containsKey(stateName)) {
            System.out.println("[Not Found] State '" + stateName + "' does not exist in the directory.");
            return;
        }

        Map<String, Long> cityMap = directory.get(stateName);
        if (cityMap.isEmpty()) {
            System.out.println("[Info] No cities registered yet under state '" + stateName + "'.");
            return;
        }

        System.out.println();
        System.out.println("================================================");
        System.out.println("STATE SUMMARY: " + stateName.toUpperCase());
        System.out.println("================================================");
        System.out.printf("%-22s | %-15s%n", "City Name", "Population");
        System.out.println("-----------------------+------------------------");

        long totalPopulation = 0;
        for (Map.Entry<String, Long> entry : cityMap.entrySet()) {
            System.out.printf("%-22s | %,15d%n", entry.getKey(), entry.getValue());
            totalPopulation += entry.getValue();
        }

        System.out.println("-----------------------+------------------------");
        System.out.printf("%-22s | %,15d%n", "TOTAL POPULATION", totalPopulation);
        System.out.println("================================================");
    }

    public void searchCityGlobally(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            System.out.println("[Error] Search term cannot be empty.");
            return;
        }

        cityName = cityName.trim();
        boolean found = false;

        System.out.println();
        System.out.println("GLOBAL SEARCH RESULTS FOR: '" + cityName + "'");
        System.out.println("================================================");

        for (Map.Entry<String, Map<String, Long>> stateEntry : directory.entrySet()) {
            String state = stateEntry.getKey();
            Map<String, Long> cityMap = stateEntry.getValue();

            if (cityMap.containsKey(cityName)) {
                System.out.printf(" * Found in State: %-15s | Population: %,d%n", state, cityMap.get(cityName));
                found = true;
            }
        }

        if (!found) {
            System.out.println("[Not Found] No records found for city '" + cityName + "' across any Indian state.");
        }
        System.out.println("================================================");
    }

    public void filterCitiesByPopulationThreshold(long threshold) {
        System.out.println();
        System.out.println("CITIES WITH POPULATION EXCEEDING: " + String.format("%,d", threshold));
        System.out.println("=========================================================");
        System.out.printf("%-20s | %-18s | %-15s%n", "City Name", "State Name", "Population");
        System.out.println("---------------------+--------------------+--------------");

        boolean matched = false;
        for (Map.Entry<String, Map<String, Long>> stateEntry : directory.entrySet()) {
            String state = stateEntry.getKey();
            for (Map.Entry<String, Long> cityEntry : stateEntry.getValue().entrySet()) {
                if (cityEntry.getValue() > threshold) {
                    System.out.printf("%-20s | %-18s | %,14d%n",
                            cityEntry.getKey(), state, cityEntry.getValue());
                    matched = true;
                }
            }
        }

        if (!matched) {
            System.out.println("[Info] No cities found matching the threshold criteria.");
        }
        System.out.println("=========================================================");
    }

    public void displayAllRecords() {
        if (directory.isEmpty()) {
            System.out.println("[Info] Directory is currently empty.");
            return;
        }

        System.out.println();
        System.out.println("ALL REGISTERED STATES AND CITIES");
        System.out.println("=========================================================");
        for (Map.Entry<String, Map<String, Long>> stateEntry : directory.entrySet()) {
            System.out.println("State: " + stateEntry.getKey());
            if (stateEntry.getValue().isEmpty()) {
                System.out.println("   (No cities added)");
            } else {
                for (Map.Entry<String, Long> cityEntry : stateEntry.getValue().entrySet()) {
                    System.out.printf("   +-- %-18s : %,d%n", cityEntry.getKey(), cityEntry.getValue());
                }
            }
        }
        System.out.println("=========================================================");
    }

    public static void main(String[] args) {
        PopulationDirectorySystem system = new PopulationDirectorySystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("   WELCOME TO INDIA STATE & CITY POPULATION DIRECTORY SYSTEM ");
        while (running) {
            System.out.println();
            System.out.println("====================== MAIN MENU ======================");
            System.out.println("1. Add New State");
            System.out.println("2. Add or Update City Population");
            System.out.println("3. Query 1: City Lookup (Get Population)");
            System.out.println("4. Query 2: State-Level Aggregation (Summary & Total)");
            System.out.println("5. Query 3: Global City Search (Find State & Population)");
            System.out.println("6. Query 4: Threshold Filtering (Cities > Threshold)");
            System.out.println("7. Display All Records");
            System.out.println("8. Exit Application");
            System.out.print("Enter your choice (1-8): ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.print("Enter State Name: ");
                    String newState = scanner.nextLine();
                    system.addState(newState);
                    break;

                case "2":
                    System.out.print("Enter State Name: ");
                    String stateForCity = scanner.nextLine();
                    System.out.print("Enter City Name: ");
                    String cityName = scanner.nextLine();
                    System.out.print("Enter Population: ");
                    try {
                        long population = Long.parseLong(scanner.nextLine().trim());
                        system.addOrUpdateCity(stateForCity, cityName, population);
                    } catch (NumberFormatException e) {
                        System.out.println("[Error] Invalid number format. Please enter a valid numerical value.");
                    }
                    break;

                case "3":
                    System.out.print("Enter State Name: ");
                    String q1State = scanner.nextLine();
                    System.out.print("Enter City Name: ");
                    String q1City = scanner.nextLine();
                    system.getCityPopulation(q1State, q1City);
                    break;

                case "4":
                    System.out.print("Enter State Name: ");
                    String q2State = scanner.nextLine();
                    system.getStateSummary(q2State);
                    break;

                case "5":
                    System.out.print("Enter City Name to search: ");
                    String q3City = scanner.nextLine();
                    system.searchCityGlobally(q3City);
                    break;

                case "6":
                    System.out.print("Enter Population Threshold: ");
                    try {
                        long threshold = Long.parseLong(scanner.nextLine().trim());
                        system.filterCitiesByPopulationThreshold(threshold);
                    } catch (NumberFormatException e) {
                        System.out.println("[Error] Invalid number. Please enter a numerical threshold.");
                    }
                    break;

                case "7":
                    system.displayAllRecords();
                    break;

                case "8":
                    System.out.println();
                    System.out.println("Exiting System. Thank you for using India Population Directory System!");
                    running = false;
                    break;

                default:
                    System.out.println("[Invalid Selection] Please choose a number between 1 and 8.");
                    break;
            }
        }

    }
}