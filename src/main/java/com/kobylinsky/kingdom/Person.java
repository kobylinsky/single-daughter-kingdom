package com.kobylinsky.kingdom;

/**
 * @author bogdankobylinsky
 */
public class Person {
    private static long nextId;
    long id;
    Sex sex;
    boolean coupled;

    public Person(Sex sex) {
        nextId++;
        this.id = nextId;
        this.sex = sex;
        this.coupled = false;
    }

    public boolean doesFitTo(Person candidateForMarraige) {
        return sex != candidateForMarraige.sex &&
                !coupled && !candidateForMarraige.coupled;
    }

    @Override
    public String toString() {
        return sex.toString() + id + (coupled ? "+" : "-");
    }
}
