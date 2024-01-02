package com.bsoft.srd5.bagdice.services;

import com.bsoft.srd5.bagdice.data.CommonDice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiceRandomizerServiceImplTest {

    @Autowired
    private DiceRandomizerService diceRandomizerService;

    @Test
    void obtainRandomNumberForDice() {
        int result = diceRandomizerService.obtainRandomNumberForDice(CommonDice.D4);
        assertTrue(result > 0 && result < 5);
    }
}