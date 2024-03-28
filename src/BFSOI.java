import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BFSOI {


    int nbnoeud_dev;
    int profondeur;
    public BFSOI(){}
    public int get_noeud(){
        return this.nbnoeud_dev;

    }
    public int get_profandeur (){
        return this.profondeur;

    }
    public  Node search(int[][] items, int[] capacities, int numKnapsacks , int limit ) {
       // int limit=items.length;

        List<Node> open = new ArrayList<>();
        List<Node> closed = new ArrayList<>();
        Node bestSol = null;
        int levelmax=0;

        int nbnoeud=0;
        int[][] initialState = new int[numKnapsacks][2 + limit];
        Node initialNode = new Node(initialState, 0, 0,0);

        open.add(initialNode);

        while (!open.isEmpty()) {
            Node currentNode = open.getFirst();
                    open.removeFirst();
            closed.add(currentNode);
            int nb_child=0;
            nbnoeud=nbnoeud+1;

            // Vérifier si l'état actuel est une solution finale et mettre à jour la meilleure solution
            if(currentNode.level>levelmax){levelmax=currentNode.level;}

            if (currentNode.level < limit) {
                int[][] currentState = deepCopy(currentNode.state); // Deep copy currentState

                for (int i = 0; i < capacities.length; i++) {
                    if (currentState[i][0] + items[currentNode.level][0] <= capacities[i]) {
                        int[][] childState=deepCopy(currentNode.state);
                        childState[i][0] += items[currentNode.level][0];
                        childState[i][1] += items[currentNode.level][1];
                        childState[i][currentNode.level + 2] = 1;

                        Node childNode = new Node(childState, currentNode.level + 1, currentNode.value + items[currentNode.level][1],0);

                        if (!closed.contains(childNode)) {
                            open.add(childNode);
                            nb_child=1+nb_child;


                        }
                        currentState = deepCopy(currentNode.state);
                    }


                }
                Node childNode = new Node(currentState, currentNode.level + 1, currentNode.value,0 );
                open.add(childNode);
                nb_child=1+nb_child;

            }
            if (currentNode.level  == limit || nb_child==0){
                if(evaluate(currentNode) > evaluate(bestSol)) {

                    bestSol = currentNode;
                }}
        }
        this.nbnoeud_dev=nbnoeud;
        this.profondeur=levelmax;
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

    // Méthode pour créer une copie profonde d'un tableau 2D
    public static int[][] deepCopy(int[][] original) {
        if (original == null) return null;
        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
