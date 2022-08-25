package Model;

import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;//no olviden importar esta librería al inicio de su programa

public class RunGenerations {

    private int generations;
    private int l;
    private int kFromL;
    private Automaton automaton;
    private int k;
    private LifePanel lifePanel;

    public RunGenerations(Automaton a, int g, int quarantine, int l, int kL, LifePanel panel) {
        this.automaton = a;
        this.generations = g;
        this.k = quarantine;
        this.l = l;
        this.kFromL = kL;
        this.lifePanel = panel;
    }

    public RunGenerations(Automaton a, int g, int quarantine, int l, LifePanel panel) {
        this.automaton = a;
        this.generations = g;
        this.k = quarantine;
        this.l = l;
        this.lifePanel = panel;
    }

    /**
     * Método que corre todas las generaciones
     */
    public void run() {
        int countIterations = 0;
        int sanos = 0, infectados = 0;
        ArrayList<Integer> infected = new ArrayList();
        ArrayList<Integer> alive = new ArrayList();
        for (int i = 0; i < generations; i++) {
            countIterations++;
            for (Student student : automaton.getStudents()) {
                if (student.isSick()) {
                    infectados++;
                } else {
                    sanos++;
                }
            }
            infected.add(infectados);
            alive.add(sanos);
            //esto es solo un método, no una clase, el método deberá ser incluido en una clase java para su uso

            //Escritura
            System.out.println("Generation: " + countIterations + ", infectados: " + infectados + ", sanos: " + sanos);
            System.out.println("\n" + "\n");
            infectados = 0;
            sanos = 0;
            if (this.automaton.getSickCounter() == this.automaton.getStudents().size()) {
                break;
            }
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(100);
            this.lifePanel.show();
            if (l != 0 && i == l) {
                this.k = kFromL;
            }
            this.automaton.setNeighbors();
            ArrayList<Student> students = new ArrayList<>();
            for (Student student : automaton.getStudents()) {
                /*
                ---------------------REGLAS----------------------
                1. Se verifica el actual estudiante
                2. Se verifican los vecinos del estudiante
                 */
                //El estuadiante debe existir, estar enfermo y que existan vecinos
                if (this.k <= 99 && student.isSick() && !student.getNeighbors().isEmpty()) {
                    ArrayList<Student> n = student.getNeighbors();
                    //Verificacion del vecino número 0
                    //El vecino debe existir y además no estar enfermo
                    if (n.get(0) != null && !n.get(0).isSick()) {
                        Random rand = new Random();
                        n.get(0).setSick(true);
                        this.automaton.incSickCounter();
                    }
                    //Verificación de los demás vecinos
                    for (int j = (this.k + 1); j < 7; j++) {
                        if (n.get(j) != null && !n.get(j).isSick()) {
                            Random rand = new Random();
                            n.get(j).setSick(true);
                            this.automaton.incSickCounter();
                        }
                    }
                }
                boolean flag = true;
                int first = 0;
                while (flag) {
                    int moveTo = student.newLocation();
                    if (!student.isBlocked() && moveTo != 8) {
                        if (first == 0) {
                            this.automaton.setCell(student.getI(), student.getJ(), null);
                            first++;
                        }
                        Student c = this.setNewLocation(student, moveTo);
                        if (c != null) {
                            this.automaton.setCell(c.getI(), c.getJ(), c);
                            students.add(student);
                            flag = false;//stop the loop
                        } else {
                            flag = true;
                        }
                    } else {
                        students.add(student);
                        flag = false;
                    }
                }
            }
            this.automaton.setCreatures(students);
            this.lifePanel.setAutomaton(this.automaton);
        }
        try {
            File f;
            f = new File("infectados.txt");
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write("");//escribimos en el archivo
            for (Integer dato : infected) {
                wr.append(dato + "\n");
            }
            //concatenamos en el archivo sin borrar lo existente
            //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
            //de no hacerlo no se escribirá nada en el archivo
            wr.close();
            bw.close();
        } catch (IOException e) {
        };
                try {
            File f;
            f = new File("sanos.txt");
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write("");//escribimos en el archivo
            for (Integer dato : alive) {
                wr.append(dato + "\n");
            }
            //concatenamos en el archivo sin borrar lo existente
            //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
            //de no hacerlo no se escribirá nada en el archivo
            wr.close();
            bw.close();
        } catch (IOException e) {
        };
        this.lifePanel.endScreen(countIterations);
    }

    public Student setNewLocation(Student student, int moveTo) {
        switch (moveTo) {
            case 0:
                if (student.getI() == 0 && student.getJ() != 0 && student.getJ() != 199) {
                    student.setI(199);
                    student.setJ(student.getJ() - 1);
                } else if (student.getI() != 0 && student.getI() != 199 && student.getJ() == 0) {
                    student.setI(student.getI() - 1);
                    student.setJ(199);
                } else if (student.getI() == 0 && student.getJ() == 0) {
                    student.setI(199);
                    student.setJ(199);
                } else if (student.getI() == 0 && student.getJ() == 199) {
                    student.setI(199);
                    student.setJ(198);
                } else if (student.getI() == 199 && student.getJ() == 0) {
                    student.setI(198);
                    student.setJ(198);
                } else {
                    student.setI(student.getI() - 1);
                    student.setJ(student.getJ() - 1);
                }
                break;
            case 1:
                if (student.getI() == 0 && student.getJ() != 0 && student.getJ() != 199) {
                    student.setI(199);
                } else if (student.getI() == 0 && student.getJ() == 0) {
                    student.setI(199);
                    student.setJ(0);
                } else if (student.getI() == 0 && student.getJ() == 199) {
                    student.setI(199);
                    student.setJ(199);
                } else {
                    student.setI(student.getI() - 1);
                }
                break;
            case 2:
                if (student.getI() == 0 && student.getJ() != 0 && student.getJ() != 199) {
                    student.setI(199);
                    student.setJ(student.getJ() + 1);
                } else if (student.getI() != 0 && student.getI() != 199 && student.getJ() == 199) {
                    student.setI(student.getI() - 1);
                    student.setJ(0);
                } else if (student.getI() == 0 && student.getJ() == 0) {
                    student.setI(199);
                    student.setJ(1);
                } else if (student.getI() == 0 && student.getJ() == 199) {
                    student.setI(199);
                    student.setJ(0);
                } else if (student.getI() == 199 && student.getJ() == 199) {
                    student.setI(198);
                    student.setJ(0);
                } else {
                    student.setI(student.getI() - 1);
                    student.setJ(student.getJ() + 1);
                }
                break;
            case 3:
                if (student.getI() != 0 && student.getI() != 199 && student.getJ() == 199) {
                    student.setJ(0);
                } else if (student.getI() == 0 && student.getJ() == 199) {
                    student.setI(0);
                    student.setJ(0);
                } else if (student.getI() == 199 && student.getJ() == 199) {
                    student.setI(199);
                    student.setJ(0);
                } else {
                    student.setJ(student.getJ() + 1);
                }
                break;
            case 4:
                if (student.getI() != 0 && student.getI() != 199 && student.getJ() == 199) {
                    student.setI(student.getI() + 1);
                    student.setJ(0);
                } else if (student.getI() == 199 && student.getJ() != 199 && student.getJ() != 0) {
                    student.setI(0);
                    student.setJ(student.getJ() + 1);
                } else if (student.getI() == 199 && student.getJ() == 0) {
                    student.setI(0);
                    student.setJ(199);
                } else if (student.getI() == 0 && student.getJ() == 199) {
                    student.setI(1);
                    student.setJ(0);
                } else if (student.getI() == 199 && student.getJ() == 199) {
                    student.setI(0);
                    student.setJ(0);
                } else {
                    student.setI(student.getI() + 1);
                    student.setJ(student.getJ() + 1);
                }
                break;
            case 5:
                if (student.getI() == 199 && student.getJ() != 199 && student.getJ() != 0) {
                    student.setI(0);
                } else if (student.getI() == 199 && student.getJ() == 199) {
                    student.setI(0);
                    student.setJ(199);
                } else if (student.getI() == 199 && student.getJ() == 0) {
                    student.setI(0);
                    student.setJ(0);
                } else {
                    student.setI(student.getI() + 1);
                    student.setJ(student.getJ());
                }
                break;
            case 6:
                if (student.getI() == 199 && student.getJ() != 199 && student.getJ() != 0) {
                    student.setI(0);
                    student.setJ(student.getJ() - 1);
                } else if (student.getI() != 0 && student.getI() != 199 && student.getJ() == 0) {
                    student.setI(student.getI() + 1);
                    student.setJ(199);
                } else if (student.getI() == 0 && student.getJ() == 0) {
                    student.setI(1);
                    student.setJ(199);
                } else if (student.getI() == 199 && student.getJ() == 199) {
                    student.setI(0);
                    student.setJ(198);
                } else if (student.getI() == 199 && student.getJ() == 0) {
                    student.setI(0);
                    student.setJ(199);
                } else {
                    student.setI(student.getI() + 1);
                    student.setJ(student.getJ() - 1);
                }
                break;
            case 7:
                if (student.getI() != 0 && student.getI() != 199 && student.getJ() == 0) {
                    student.setJ(199);
                } else if (student.getI() == 0 && student.getJ() == 0) {
                    student.setI(0);
                    student.setJ(199);
                } else if (student.getI() == 199 && student.getJ() == 0) {
                    student.setI(199);
                    student.setJ(199);
                } else {
                    student.setJ(student.getJ() - 1);
                }
                break;
            default:
        }
        if (this.automaton.isCellEmpty(student.getI(), student.getJ())) {
            return student;
        }
        return null;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }
}
