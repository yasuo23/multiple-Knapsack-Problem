
import javax.swing.text.AsyncBoxView;
import java.util.*;

public class AstarAlgo {
    int nbnoeud_dev;
    int profondeur;
    public AstarAlgo(){}
    public int get_noeud(){
        return this.nbnoeud_dev;

    }
    public int get_profandeur (){
        return this.profondeur;

    }



    // Méthode pour effectuer le parcours en profondeur
    public  Node search(int[][] items, int[] capacities, int numKnapsacks,int limit) {
        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));

        List<Node> closed = new ArrayList<>();
        Node bestSol = null;
        int levelmax=0;

        int[][] initialState = new int[numKnapsacks][2+ limit];
        Node initialNode = new Node(initialState, 0, 0,0);

        open.add(initialNode);
        int nbnoeud=0;
        while (!open.isEmpty()) {
            Node currentNode = open.poll();
            nbnoeud=nbnoeud+1;
            closed.add(currentNode);
//            affiche(currentNode,  bestSol, items,  numKnapsacks) ;
            int nb_child=0;

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
                        //g(n)+h(n) =>MTM;
                        int cost =currentNode.value+items[currentNode.level][1];

                        Node childNode = new Node(childState, currentNode.level + 1, currentNode.value + items[currentNode.level][1],cost);

                        if (!closed.contains(childNode)) {
                            open.add(childNode);
                            nb_child=1+nb_child;


                        }
                        currentState = deepCopy(currentNode.state);
                    }


                }
                Node childNode = new Node(currentState, currentNode.level + 1, currentNode.value,currentNode.value+items[currentNode.level+1][1]);
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
    public static int greedyKnapsack(int [][] items, int[] capacities) {

        // Tri des articles par profit/poids décroissant
        Arrays.sort(items, (a, b) -> Double.compare((double) b[1] / b[0], (double) a[1] / a[0]));

        // Tri des sacs par capacité croissante
        Arrays.sort(capacities);

        // Appliquer une heuristique greedy pour le problème de sac à dos
        int totalValue = 0;
        for (int i = 0; i < items.length; i++) {
            int maxItemValue = 0;
            for (int j = 0; j < capacities.length; j++) {
                if (items[i][0] <= capacities[j]) {
                    maxItemValue = Math.max(maxItemValue, items[i][1]);
                }
            }
            totalValue += maxItemValue;
        }
        return totalValue;
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

    // Classe Node

}
