package com.kobylinsky.kingdom;

import java.util.ArrayList;
import java.util.List;

/**
 * Couple consist of two parents and children.
 *
 * @author bogdankobylinsky
 */
public class Couple {

    private Person parent1;
    private Person parent2;
    private List<Person> children = new ArrayList<>();

    public Couple(Person parent1, Person parent2) {
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.parent1.setCoupled(true);
        this.parent2.setCoupled(true);
    }

    public void giveBirthToOffspringUntilFemale() {
        Sex newPersonSex;
        do {
            newPersonSex = Sex.random();
            children.add(new Person(newPersonSex));
        } while (newPersonSex != Sex.F);
    }

    public int getChildrenCountBySex(Sex sex) {
        int childrenCount = 0;
        for (Person person : this.children) {
            if (person.getSex() == sex) {
                childrenCount += 1;
            }
        }
        return childrenCount;
    }

    public List<Person> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(parent1).append(" + ").append(parent2).append("] -> ").append(children);
        return stringBuilder.toString();
    }
}
