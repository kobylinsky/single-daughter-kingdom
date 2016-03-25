package com.kobylinsky.kingdom;

/**
 * @author bogdankobylinsky
 */
public class Person {
    private static long nextId;
    private long id;
    private Sex sex;
    private boolean coupled;

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

    public Sex getSex() {
        return sex;
    }

    public void setCoupled(boolean coupled) {
        this.coupled = coupled;
    }

    public boolean isCoupled() {
        return coupled;
    }

    @Override
    public String toString() {
        return sex.toString() + id + (coupled ? "+" : "-");
    }
}
