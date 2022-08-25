package Model;

import biuoop.GUI;
import javax.swing.JFrame;
import java.awt.*;
import java.util.*;
import java.util.Random;

public class COVID19Contagion {
    public static void main(String[] args) {
        int n, l=0, k;
        float p,k_1;
        Automaton automaton = new Automaton();
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese cantidad de estudiantes:");
        n = input.nextInt();
        System.out.println("Ingrese tasa de contagio(0<=P<=1):");
        p = input.nextFloat();
        System.out.println("Ingresar tasa de vacunación (0<=K<=1):");
        k_1 = input.nextFloat();
        System.out.println("K_1 es: "+k_1);
        k_1 = k_1*100;
        k = (int) k_1;
        System.out.println("K_1 después es: "+k_1);
        System.out.println("K es: "+k);
        int[] sick = new int[2];
        sick[0] = rand.nextInt(199);
        sick[1] = rand.nextInt(199);
        Student creatureSick = new Student(sick[0], sick[1], p);
        creatureSick.setSick(true);
        automaton.setCell(creatureSick.getI(), creatureSick.getJ(), creatureSick);
        automaton.addCreature(creatureSick);
        for (int i = 0; i < n - 1; i++) {
            int row = rand.nextInt(199), column = rand.nextInt(199);
            while (automaton.getAutomaton()[row][column] != null) {
                row = rand.nextInt(199);
                column = rand.nextInt(199);
            }
            Student creature = new Student(row, column, p);
            automaton.setCell(row, column, creature);
            automaton.addCreature(creature);
        }
        GUI gui = new GUI("COVID19", 1400, 1000);
        LifePanel lifePanel = new LifePanel(gui, automaton, l, k);
        if (l != 0) {
            RunGenerations runGenerations = new RunGenerations(automaton, 1000, 0, l, k, lifePanel);
            runGenerations.run();
        } else {
            RunGenerations runGenerations = new RunGenerations(automaton, 1000, k, l, lifePanel);
            runGenerations.run();
        }
    }
}