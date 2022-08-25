package Model;

import java.util.ArrayList;
import java.util.Random;

public class Student {

    private int i, j;
    private boolean isSick, isBlocked;
    private float infectionProb;
    private ArrayList<Student> neighbors;


    public Student(int row, int column, float p) {
        this.i = row;
        this.j = column;
        this.isSick = false;
        isBlocked = false;
        this.infectionProb = p;
        this.neighbors = new ArrayList<>(9);
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }


    public boolean isSick() {
        return isSick;
    }

    public float getInfectionProb() {
        return infectionProb;
    }

    public ArrayList<Student> getNeighbors() {
        return neighbors;
    }


    public boolean isBlocked() {
        return isBlocked;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setSick(boolean sick) {
        isSick = sick;
    }


    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setInfectionProb(float infectionProb) {
        this.infectionProb = infectionProb;
    }

    public void setNeighbors(ArrayList<Student> n) {
        this.neighbors = n;
    }

    public int newLocation() {
        if (this.isBlocked) {
            return 8;
        }
        Random rand = new Random();
        int location = rand.nextInt(8);
        while (location != 8 && !this.neighbors.isEmpty() && this.neighbors.get(location) != null) {
            location = rand.nextInt(8);
        }
        return location;
    }
}