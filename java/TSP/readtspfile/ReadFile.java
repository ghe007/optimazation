package TSP.readtspfile;

import java.io.*;
import java.util.*;

import TSP.model.City;


// this class reads the .tsp files 
public class ReadFile {
    //this method takes the path of the file and returns a list of cities
    public static List<City> read(String path) throws IOException {

        List<City> cities = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(path));

        String line;
        boolean nodeSection = false;

        while ((line = br.readLine()) != null) {

            line = line.trim();

            if (line.equals("NODE_COORD_SECTION")) {
                nodeSection = true;
                continue;
            }

            if (line.equals("EOF")) {
                break;
            }

            if (nodeSection) {

                String[] parts = line.split("\\s+");

                int id = Integer.parseInt(parts[0]);
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);

                cities.add(new City(id, x, y));
            }
        }

        br.close();

        return cities;
    }
}
