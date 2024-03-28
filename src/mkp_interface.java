import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mkp_interface extends JFrame {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField nbsacs;
    private JTextField nbObject;
    private JTextField proflimit;
    private JButton solveButton;
    private JTextArea output;
    private JTextArea résulta;

    private JPanel panne2;

    public mkp_interface(){
        setContentPane(panel1);
        setTitle("Knapsack Problem Solver");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // Center the window


        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                output.setText("  ");
                résulta.setText("");solveKnapsackProblem();
            }
        });
    }
    private void solveKnapsackProblem() {
        String algorithm = (String) comboBox1.getSelectedItem();
        int nbobject = Integer.parseInt(nbObject.getText());
        int nbsac = Integer.parseInt(nbsacs.getText());
        int limit = Integer.parseInt(proflimit.getText());
        readfile f =new readfile  ( nbobject ,  nbsac);
        Node bestSol;
        long executionTime;

        if(algorithm.equals("dfs")){
            DFSMultipleKnapsack d =new DFSMultipleKnapsack();
            long startTime = System.currentTimeMillis();

            bestSol = d.search(f.get_objects(),f.get_sac(),f.nbsac,limit);
            long endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            solution(bestSol,  nbsac ,  nbobject ,f,limit);

            résulta.append("le nombre de nœuds développés : " + d.get_noeud() + "\n");
            résulta.append("le temps d'exécution : " + executionTime + "Millisecond \n");
            résulta.append("profondeur maximale atteinte : " + d.get_profandeur() + "\n");

        }
        else if(algorithm.equals("bfs")) {
            BFSOI d =new BFSOI();
            long startTime = System.currentTimeMillis();

            bestSol = d.search(f.get_objects(),f.get_sac(),f.nbsac,limit);
            long endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            solution(bestSol,  nbsac ,  nbobject ,f,limit);

            résulta.append("le nombre de nœuds développés : " + d.get_noeud() + "\n");
            résulta.append("le temps d'exécution : " + executionTime + "Millisecond \n");
            résulta.append("profondeur maximale atteinte : " + d.get_profandeur() + "\n");
        }
        else{ AstarAlgo d =new AstarAlgo();
            long startTime = System.currentTimeMillis();

            bestSol = d.search(f.get_objects(),f.get_sac(),f.nbsac,limit);
            long endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            solution(bestSol,  nbsac ,  nbobject ,f,limit);

            résulta.append("le nombre de nœuds développés : " + d.get_noeud() + "\n");
            résulta.append("le temps d'exécution : " + executionTime + "Millisecond \n");
            résulta.append("profondeur maximale atteinte : " + d.get_profandeur() + "\n");}



    }
    public void solution(Node bestSol, int nbsac ,int nbobject, readfile f ,int limit){


        int[][]listOBject=f.get_objects();
        int[] poid_sac=f.get_sac();
        int nbu=0;
        int nbr=nbsac;
        int valglobale =0;



        output.setText("  ");
        //  System.out.println("Meilleures solutions trouvées :");
        if (bestSol != null) {
            for (int knapsackIndex = 0; knapsackIndex < nbsac; knapsackIndex++) {
                int poid=0;
                output.append(" Knapsack " + (knapsackIndex + 1) + ":\n\n");
                output.append("capacité du sac : " +poid_sac[knapsackIndex]+"\n");
                output.append("poids actuel du sac : " +bestSol.state[knapsackIndex][0] +"\n");
                output.append("Valeur totale du sac  : " + bestSol.state[knapsackIndex][1]+"\n");
                output.append("\nObjets sélectionnés :");
                for (int itemIndex = 0; itemIndex < limit; itemIndex++) {
                    if (bestSol.state[knapsackIndex][itemIndex + 2] == 1) {
                        output.append("\n* Objet " + (itemIndex + 1) + ": \nPoids = " + listOBject[itemIndex][0] + ", Valeur = " + listOBject[itemIndex][1]+"\n");
                        nbu=nbu+1;

                    }

                }
                if (bestSol.state[knapsackIndex][0]==0){nbr=nbr-1;}
                valglobale=valglobale+bestSol.state[knapsackIndex][1];

                output.append("\n================================\n\n");
            }
        } else {
            output.append("Aucune solution trouvée.");
        }
        résulta.append("la valeur globale : " + valglobale + "\n");
        résulta.append("le nombre d'objets utilisés : " + nbu + "\n");
        résulta.append("le nombre de sacs remplis : " + nbr + "\n");



    }

}
