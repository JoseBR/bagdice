package com.bsoft.srd5.bagdice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bsoft.srd5.bagdice.data.CommonDice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


class DiceRandomizerServiceImplTest {

    private static DiceRandomizerService diceRandomizerService;

    @BeforeAll
    static void beforeAll() {

        diceRandomizerService = new DiceRandomizerServiceImpl();
    }

    @Test
    void obtainRandomNumberForDice() {
        int result = diceRandomizerService.obtainRandomNumberForDice(CommonDice.D4);
        assertTrue(result > 0 && result < 5);
    }

    @Test
    void randomNumberHitsMaxValue() {

        final int FACES = 20;
        int result = 0;
        for (int i = 0; i < 10000 && result < FACES; i++) {
            result = Integer.max(result, diceRandomizerService.obtainRandomNumberForDice(CommonDice.D20));
        }
        assertEquals(FACES, result);
    }

    @RepeatedTest(value = 100)
    void randomResultIsBetweenOneAndDiceValue() {

        int diceResult = diceRandomizerService.obtainRandomNumberForDice(CommonDice.D4);
        assertTrue(diceResult <= CommonDice.D4.getFaces() && diceResult > 0);
    }
}