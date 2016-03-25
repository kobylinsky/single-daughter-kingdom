package com.kobylinsky.kingdom;

import org.junit.Test;

public class SingleDaughterKingdomTest {

    private static final int INITIAL_COUPLES_AMOUNT = 100;
    private static final int MAX_REBIRTH_ITERATIONS = 50000;

    /**
     * 1. Generating a kingom.
     * 2. Performing an initial birth cycle.
     * 3. Performing reproduction until the kingdom will be dead or we will exceed MAX_REBIRTH_ITERATIONS;
     */
    @Test
    public void testDaughtersPercent() {
        Kingdom kingdom = new Kingdom();
        kingdom.generate(INITIAL_COUPLES_AMOUNT);
        System.out.println(kingdom);

        kingdom.performBirthCycle();
        System.out.println(kingdom);

        for (int i = 1; i <= MAX_REBIRTH_ITERATIONS && kingdom.isNotDead(); i++) {
            System.out.println("Iteration #" + i);
            kingdom.marryAllChildren();
            kingdom.performBirthCycle();
            System.out.println(kingdom);
        }
    }

}
