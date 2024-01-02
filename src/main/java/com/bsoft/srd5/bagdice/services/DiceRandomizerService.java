package com.bsoft.srd5.bagdice.services;

import com.bsoft.srd5.bagdice.models.Dice;

public interface DiceRandomizerService {
    Integer obtainRandomNumberForDice(Dice dice);
}
