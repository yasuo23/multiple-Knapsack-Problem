
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readfile  {
    int nbobject ;
    int nbsac;
    int[][] objects;
    int[]sacs;
    public readfile  (int nbobject , int nbsac){
        this.nbsac=nbsac;
        this.nbobject=nbobject;


    }

   public int[] get_sac(){
       extract ();
       return this.sacs;
   }
    public int[][] get_objects(){
        extract ();
        return this.objects;
    }


    public  generatedata
    public  void extract (){
            // Specify the path to the .txt file
            String fileName = "donnée.txt";

            // Define arrays to store the data
            int [][] items = new int[this.nbobject][2]; // 5 items, each with weight and profit
            int [] capacities = new int[this.nbsac]; // 7 capacities

            // Open the file
            try {
                File file = new File(fileName);
                Scanner scanner = new Scanner(file);

                // Read and process each line from the file
                int itemIndex = 0;
                int capacityIndex = 0;
                while (scanner.hasNextLine()&& (itemIndex<nbobject||capacityIndex<this.nbsac)) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split(" "); // Assuming tab-delimited data

                    // Extract data and store in appropriate arrays
//                    if (tokens.length == 3) {
//                        int weight =  Integer.parseInt((tokens[0]));
//                        int profit = Integer.parseInt((tokens[1]));
//                        int  capacity = Integer.parseInt((tokens[2]));
                    if (tokens.length != 3) {
                        System.err.println("Invalid data format on line: " + line);
                        continue; // Skip to the next line
                    }

                    // Extract data and store in appropriate arrays
                    int weight = Integer.parseInt(tokens[0]);
                    int profit = Integer.parseInt(tokens[1]);
                    int capacity = Integer.parseInt(tokens[2]);
                    System.out.println("hi");
                    if(itemIndex<this.nbobject) {
                        // Store item data
                        items[itemIndex][0] = weight;
                        items[itemIndex][1] = profit;
                        itemIndex++;
                    }
                        // Store capacity data
                    if(capacityIndex<this.nbsac){
                        capacities[capacityIndex] = capacity;
                        capacityIndex++;}
                  //  }
                }

                // Close the scanner
                scanner.close();
                this.objects=items;
                this.sacs=capacities;

                // Print the data (for verification)
                System.out.println("Items:");
                for (int [] item : items) {
                    System.out.println(item[0] + "\t" + item[1]);
                }
                System.out.println("\nCapacities:");
                for (int  capacity : capacities) {
                    System.out.println(capacity);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        }

    }


