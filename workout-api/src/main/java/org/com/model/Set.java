package org.com.model;

public class Set {
    private int setNumber;
    private int weight;
    private int reps;

    public Set(int setNumber, int weight, int reps) {
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
    }

    public Set() {
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
