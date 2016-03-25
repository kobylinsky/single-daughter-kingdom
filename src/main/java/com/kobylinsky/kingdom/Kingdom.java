package com.kobylinsky.kingdom;

import java.util.ArrayList;
import java.util.List;

/**
 * Kingdom consist of couples and people that are not married yet (untouched).
 *
 * @author bogdankobylinsky
 */
public class Kingdom {

    private List<Couple> couples = new ArrayList<>();
    private List<Person> untouched = new ArrayList<>();

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
            for (Person childInCouple1 : couple1.getChildren()) {
                for (Couple couple2 : couples) {
                    if (couple1.equals(couple2)) {
                        continue; // Avoid incest
                    }
                    for (Person childInCouple2 : couple2.getChildren()) {
                        if (childInCouple1.doesFitTo(childInCouple2)) {
                            newCouples.add(new Couple(childInCouple1, childInCouple2));
                        }
                    }
                }
            }
        }

        for (Person untouchedChild : untouched) {
            for (Couple couple : couples) {
                for (Person childInCouple : couple.getChildren()) {
                    if (childInCouple.doesFitTo(untouchedChild)) {
                        newCouples.add(new Couple(childInCouple, untouchedChild));
                        newUntouched.remove(untouchedChild);
                    }
                }
            }
        }

        for (Couple couple : couples) {
            for (Person childInCouple : couple.getChildren()) {
                if (!childInCouple.isCoupled()) {
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

    public boolean isNotDead() {
        return !couples.isEmpty();
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
