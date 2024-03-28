import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                   mkp_interface ui = new mkp_interface();
                    ui.setVisible(true);}
            });
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.int[][] items = {{2, 1 }, {3, 2}, {4, 3}};
        int[][] items = {{6, 1}, {3, 2}, {4, 3},{2,4},{2,8},{2,7}};
        int[] capacities = {5, 6, 7};
        int numKnapsacks = 3;
        DFSMultipleKnapsack d =new DFSMultipleKnapsack();
        Node bestSol = d.search(items, capacities, numKnapsacks,items.length);
        DFSMultipleKnapsack.affiche(bestSol, bestSol, items, numKnapsacks);
        BFSOI b =new BFSOI();
//         bestSol = otherdfs.search(items, capacities, numKnapsacks);
//        otherdfs.affiche(bestSol, bestSol, items, numKnapsacks);
         bestSol = b.search(items, capacities, numKnapsacks,items.length);
        BFSOI.affiche(bestSol, bestSol, items, numKnapsacks);
    }
}