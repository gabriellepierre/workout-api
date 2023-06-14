package org.com.model;

import java.util.List;

public class Exercice {
    private String name;
    private List<String> musclesEngaged;
    private List<Set> sets;

    public Exercice(String name, List<String> musclesEngaged, List<Set> sets) {
        this.name = name;
        this.musclesEngaged = musclesEngaged;
        this.sets = sets;
    }

    public Exercice() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMusclesEngaged() {
        return musclesEngaged;
    }

    public void setMusclesEngaged(List<String> musclesEngaged) {
        this.musclesEngaged = musclesEngaged;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }
}
