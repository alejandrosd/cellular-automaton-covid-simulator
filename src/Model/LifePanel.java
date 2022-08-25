package Model;

import java.awt.*;

import biuoop.DrawSurface;
import biuoop.GUI;

public class LifePanel {

    private GUI gui;
    private Automaton automaton;
    private int vaccine, level;

    public LifePanel(GUI g, Automaton a, int l, int k) {
        this.gui = g;
        this.automaton = a;
        this.level = l;
        this.vaccine = k;
    }

    public void show() {
        DrawSurface d = this.gui.getDrawSurface();
        d.fillRectangle(0, 0, 1400, 1000);
        d.setColor(Color.gray);
        for (int i = 0; i < this.automaton.getAutomaton().length; i++) {
            d.drawLine(0, i * 5, 1000, i * 5);
            d.drawLine(i * 5, 0, i * 5, 1000);
        }
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                Student c = this.automaton.getAutomaton()[i][j];
                if (c != null) {
                    if (c.isSick()) {
                        d.setColor(Color.red);
                    } else {
                        d.setColor(Color.green);
                    }
                    d.fillRectangle(i * 5, j * 5, 5, 5);
                }
            }
        }
        this.gui.show(d);
    }

    public void endScreen(int generations) {
        DrawSurface d = this.gui.getDrawSurface();
        d.fillRectangle(0, 0, 1400, 1000);
        d.setColor(Color.cyan);
        d.drawText(10, 320, "Número de individuos: " + this.automaton.getStudents().size() + ".", 45);
        d.drawText(10, 380, "Tasa de contagio: "
                + this.automaton.getStudents().get(0).getInfectionProb()*100 + "%.", 45);
        d.drawText(10, 440, "Tasa de vacunación: " + this.vaccine + "%.", 45);
        d.setColor(Color.red);
        d.drawText(100, 520, this.automaton.getSickCounter() + " estudiantes enfermos " + "después de "
                + generations + " generaciones.", 45);

        this.gui.show(d);
    }

    public void setAutomaton(Automaton automaton) {
        this.automaton = automaton;
    }
}
