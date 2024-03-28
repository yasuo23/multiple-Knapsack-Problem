import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class otherdfs {

    // Méthode pour effectuer le parcours en profondeur
    public static Node search(int[][] items, int[] capacities, int numKnapsacks) {
        Stack<Node> open = new Stack<>();
        List<Node> closed = new ArrayList<>();
        Node bestSol = null;

        int[][] initialState = new int[numKnapsacks][2 + items.length];
        Node initialNode = new Node(initialState, 0, 0,0);

        open.push(initialNode);

        while (!open.isEmpty()) {
            Node currentNode = open.pop();
            closed.add(currentNode);

            int nb_child=0;
            for (int j= 0; j < items.length; j++) {


                int[][] currentState = deepCopy(currentNode.state); // Deep copy currentState

                for (int i = 0; i < capacities.length; i++) {
                    if (currentState[i][0] + items[j][0] <= capacities[i]&&!contient(currentState ,capacities.length,j+2 ) ){
                        int[][] childState=deepCopy(currentNode.state);
                        childState[i][0] += items[j][0];
                        childState[i][1] += items[j][1];
                        childState[i][j + 2] = 1;

                        Node childNode = new Node(childState, currentNode.level + 1, currentNode.value + items[j][1],0);

                        if (!closed.contains(childNode)) {
                            open.push(childNode);

                            nb_child=nb_child+1;

                        }
                        currentState = deepCopy(currentNode.state);
                    }


                }}



            if (currentNode.level  == items.length ||nb_child==0 ){
                if(evaluate(currentNode) > evaluate(bestSol)) {

                    bestSol = currentNode;
                }}}


        // Return the best solution
        return bestSol;
    }

    public static void affiche(Node currentNode, Node bestSol, int[][] items, int numKnapsacks) {
        System.out.println("Meilleures solutions trouvées :");
        if (currentNode != null) {
            for (int knapsackIndex = 0; knapsackIndex < numKnapsacks; knapsackIndex++) {
                System.out.println("Knapsack " + (knapsackIndex + 1) + ":");
                System.out.println("Valeur totale : " + currentNode.state[knapsackIndex][1]);
                System.out.println("Objets sélectionnés :");
                for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
                    if (currentNode.state[knapsackIndex][itemIndex + 2] == 1) {
                        System.out.println("Objet " + (itemIndex + 1) + ": Poids = " + items[itemIndex][0] + ", Valeur = " + items[itemIndex][1]);
                    }
                }
            }
        } else {
            System.out.println("Aucune solution trouvée.");
        }
    }

    // Méthode pour évaluer la valeur d'un nœud
    public static int evaluate(Node node) {
        if (node == null) {
            return 0;
        }
        return node.value;
    }
    public static boolean contient(int[][]curentState, int nbsac, int item) {
        int i = 0;
        boolean contient=false;
        while (i < nbsac && contient==false) {
            if(curentState[i][item]==1){contient=true;}i++;
        }

            return contient;
        }



    // Méthode pour créer une copie profonde d'un tableau 2D
    public static int[][] deepCopy(int[][] original) {
        if (original == null) return null;
        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    // Classe Node

}
