package com.kobylinsky.kingdom;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {
    List<Couple> couples = new ArrayList<>();
    List<Person> untouched = new ArrayList<>();

    public void generate(int n) {
        for (int i = 0; i < n; i++) {
            couples.add(new Couple(new Person(Sex.M), new Person(Sex.F)));
        }
    }

    public void performBirthCycle() {
        for (Couple couple : couples) {
            couple.giveBirthToOffspringUntilFemale();
        }
    }

    public void marryAllChildren() {
        List<Couple> newCouples = new ArrayList<>();
        List<Person> newUntouched = new ArrayList<>(untouched);
        for (Couple couple1 : couples) {
            for (Person childInCouple1 : couple1.children) {
                for (Couple couple2 : couples) {
                    if (couple1.equals(couple2)) {
                        continue; // Avoid incest
                    }
                    for (Person childInCouple2 : couple2.children) {
                        if (childInCouple1.doesFitTo(childInCouple2)) {
                            newCouples.add(new Couple(childInCouple1, childInCouple2));
                        }
                    }
                }
            }
        }

        for (Person untouchedChild : untouched) {
            for (Couple couple : couples) {
                for (Person childInCouple : couple.children) {
                    if (childInCouple.doesFitTo(untouchedChild)) {
                        newCouples.add(new Couple(childInCouple, untouchedChild));
                        newUntouched.remove(untouchedChild);
                    }
                }
            }
        }

        for (Couple couple : couples) {
            for (Person childInCouple : couple.children) {
                if (!childInCouple.coupled) {
                    newUntouched.add(childInCouple);
                }
            }
        }
        couples = newCouples;
        untouched = newUntouched;
    }

    public int getCount(Sex sex) {
        int ms = 0;
        for (Couple couple : couples) {
            ms = ms + 1 + couple.getChildrenCountBySex(sex);
        }
        return ms;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Couple couple : couples) {
            stringBuilder.append(couple).append(System.lineSeparator());
        }
        stringBuilder.append("Males: ").append(getCount(Sex.M)).append(System.lineSeparator());
        stringBuilder.append("Females: ").append(getCount(Sex.F)).append(System.lineSeparator());
        stringBuilder.append("Untouched: ").append(untouched.size()).append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator()).append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
