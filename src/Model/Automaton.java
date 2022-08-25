package Model;

import java.awt.List;
import java.util.*;

public class Automaton {
    private Student[][] automaton;
    private ArrayList<Student> students;
    private int sickCounter;


    public Automaton() {
        this.automaton = new Student[200][200];
        this.students = new ArrayList<>();
        this.sickCounter = 1;

    }

    public Student[][] getAutomaton() {
        return this.automaton;
    }

    public void setAutomaton(Student[][] automaton) {
        this.automaton = automaton;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setCreatures(ArrayList<Student> creatures) {
        this.students = creatures;
    }

    public void setCell(int row, int column, Student c) {
        this.automaton[row][column] = c;
    }

    public void addCreature(Student c) {
        this.students.add(c);
    }

    public boolean isCellEmpty(int row, int column) {
        if (this.automaton[row][column] == null) {
            return true;
        }
        return false;
    }


    public void incSickCounter() {
        this.sickCounter++;
    }

    public int getSickCounter() {
        return sickCounter;
    }

    public void setNeighbors() {

        for (Student student : this.students) {
            ArrayList<Student> n = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                n.add(j, null);
            }
            boolean[] isChecked = new boolean[8];
            for (int j = 0; j < 8; j++) {
                isChecked[j] = false;
            }
            int i = 0;
            if (student.getI() == 0) {
                if (student.getJ() == 0) {
                    if (!this.isCellEmpty(199, 199)) {
                        // neighbor = new Creature(199, 199, p);
                        n.set(0, this.automaton[199][199]);
                        i++;
                    }
                    isChecked[0] = true;
                    if (!this.isCellEmpty(199, 0)) {
                        // neighbor = new Creature(199, 0, p);
                        n.set(1, this.automaton[199][0]);
                        i++;
                    }
                    isChecked[1] = true;
                    if (!this.isCellEmpty(199, 1)) {
                        //  neighbor = new Creature(199, 1, p);
                        n.set(2, this.automaton[199][1]);
                        i++;
                    }
                    isChecked[2] = true;
                    if (!this.isCellEmpty(1, 199)) {
                        //neighbor = new Creature(1, 199, p);
                        n.set(6, this.automaton[1][199]);
                        i++;
                    }
                    isChecked[6] = true;
                    if (!this.isCellEmpty(0, 199)) {
                        n.set(7, this.automaton[0][199]);
                        i++;
                    }
                    isChecked[7] = true;
                    //Top right corner
                } else if (student.getJ() == 199) {
                    if (!this.isCellEmpty(199, 198)) {
                        n.set(0, this.automaton[199][199]);
                        i++;
                    }
                    isChecked[0] = true;
                    if (!this.isCellEmpty(199, 199)) {
                        n.set(1, this.automaton[199][199]);
                        i++;
                    }
                    isChecked[1] = true;
                    if (!this.isCellEmpty(199, 0)) {
                        n.set(2, this.automaton[199][0]);
                        i++;
                    }
                    isChecked[2] = true;
                    if (!this.isCellEmpty(0, 0)) {
                        n.set(3, this.automaton[0][0]);
                        i++;
                    }
                    isChecked[3] = true;
                    if (!this.isCellEmpty(1, 0)) {
                        n.set(4, this.automaton[1][0]);
                        i++;
                    }
                    isChecked[4] = true;
                } else {
                    if (!this.isCellEmpty(199, student.getJ() - 1)) {
                        n.set(0, this.automaton[199][student.getJ() - 1]);
                        i++;
                    }
                    isChecked[0] = true;
                    if (!this.isCellEmpty(199, student.getJ())) {
                        n.set(1, this.automaton[199][student.getJ()]);
                        i++;
                    }
                    isChecked[1] = true;
                    if (!this.isCellEmpty(199, student.getJ() + 1)) {
                        n.set(2, this.automaton[199][student.getJ() + 1]);
                        i++;
                    }
                    isChecked[2] = true;
                }
            } else if (student.getJ() == 199) {
                if (student.getI() == 199) {
                    if (!this.isCellEmpty(198, 0)) {
                        n.set(2, this.automaton[198][0]);
                        i++;
                    }
                    isChecked[2] = true;
                    if (!this.isCellEmpty(199, 0)) {
                        n.set(3, this.automaton[199][0]);
                        i++;
                    }
                    isChecked[3] = true;
                    if (!this.isCellEmpty(0, 0)) {
                        n.set(4, this.automaton[0][0]);
                        i++;
                    }
                    isChecked[4] = true;
                    if (!this.isCellEmpty(0, 199)) {
                        n.set(5, this.automaton[0][199]);
                        i++;
                    }
                    isChecked[5] = true;
                    if (!this.isCellEmpty(0, 198)) {
                        n.set(6, this.automaton[0][198]);
                        i++;
                    }
                    isChecked[6] = true;

                    //right col inner
                } else {
                    if (!this.isCellEmpty(student.getI() - 1, 0)) {
                        n.set(2, this.automaton[student.getI() - 1][0]);
                        i++;
                    }
                    isChecked[2] = true;
                    if (!this.isCellEmpty(student.getI(), 0)) {
                        n.set(3, this.automaton[student.getI()][0]);
                        i++;
                    }
                    isChecked[3] = true;
                    if (!this.isCellEmpty(student.getI() + 1, 0)) {
                        n.set(4, this.automaton[student.getI() + 1][0]);
                        i++;
                    }
                    isChecked[4] = true;
                }
            } else if (student.getI() == 199) {
                if (student.getJ() == 0) {
                    if (!this.isCellEmpty(198, 198)) {
                        n.set(0, this.automaton[198][198]);
                        i++;
                    }
                    isChecked[0] = true;
                    if (!this.isCellEmpty(0, 199)) {
                        n.set(4, this.automaton[0][199]);
                        i++;
                    }
                    isChecked[4] = true;
                    if (!this.isCellEmpty(0, 0)) {
                        n.set(5, this.automaton[0][0]);
                        i++;
                    }
                    isChecked[5] = true;
                    if (!this.isCellEmpty(0, 199)) {
                        n.set(6, this.automaton[0][199]);
                        i++;
                    }
                    isChecked[6] = true;
                    if (!this.isCellEmpty(199, 199)) {
                        n.set(7, this.automaton[199][199]);
                        i++;
                    }
                    isChecked[7] = true;
                } else {
                    if (!this.isCellEmpty(0, student.getJ() + 1)) {
                        n.set(4, this.automaton[0][student.getJ() + 1]);
                        i++;
                    }
                    isChecked[4] = true;
                    if (!this.isCellEmpty(0, student.getJ())) {
                        n.set(5, this.automaton[0][student.getJ()]);
                        i++;
                    }
                    isChecked[5] = true;
                    if (!this.isCellEmpty(0, student.getJ() - 1)) {
                        n.set(6, this.automaton[0][student.getJ() - 1]);
                        i++;
                    }
                    isChecked[6] = true;
                }
                //Left Col
            } else if (student.getJ() == 0) {
                if (!this.isCellEmpty(student.getI() - 1, 199)) {
                    n.set(0, this.automaton[student.getI() - 1][199]);
                    i++;
                }
                isChecked[0] = true;
                if (!this.isCellEmpty(student.getI() + 1, 199)) {
                    n.set(6, this.automaton[student.getI() + 1][199]);
                    i++;
                }
                isChecked[6] = true;
                if (!this.isCellEmpty(student.getI(), 199)) {
                    n.set(7, this.automaton[student.getI()][199]);
                    i++;
                }
                isChecked[7] = true;
            }
            //inner matrix cells, and the neighbors of the margins that have'nt been checked
            if (!isChecked[0] && !this.isCellEmpty(student.getI() - 1, student.getJ() - 1)) {
                n.set(0, this.automaton[student.getI() - 1][student.getJ() - 1]);
                i++;
            }
            if (!isChecked[1] && !this.isCellEmpty(student.getI() - 1, student.getJ())) {
                n.set(1, this.automaton[student.getI() - 1][student.getJ()]);
                i++;
            }
            if (!isChecked[2] && !this.isCellEmpty(student.getI() - 1, student.getJ() + 1)) {
                n.set(2, this.automaton[student.getI() - 1][student.getJ() + 1]);
                i++;
            }
            if (!isChecked[3] && !this.isCellEmpty(student.getI(), student.getJ() + 1)) {
                n.set(3, this.automaton[student.getI()][student.getJ() + 1]);
                i++;
            }
            if (!isChecked[4] && !this.isCellEmpty(student.getI() + 1, student.getJ() + 1)) {
                n.set(4, this.automaton[student.getI() + 1][student.getJ() + 1]);
                i++;
            }
            if (!isChecked[5] && !this.isCellEmpty(student.getI() + 1, student.getJ())) {
                n.set(5, this.automaton[student.getI() + 1][student.getJ()]);
                i++;
            }
            if (!isChecked[6] && !this.isCellEmpty(student.getI() + 1, student.getJ() - 1)) {
                n.set(6, this.automaton[student.getI() + 1][student.getJ() - 1]);
                i++;
            }
            if (!isChecked[7] && !this.isCellEmpty(student.getI(), student.getJ() - 1)) {
                n.set(7, this.automaton[student.getI()][student.getJ() - 1]);
                i++;
            }
            student.setNeighbors(n);
            if (i == 8) {
                student.setBlocked(true);
            }
        }
    }
}