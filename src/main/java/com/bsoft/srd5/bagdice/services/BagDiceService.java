package com.bsoft.srd5.bagdice.services;

import com.bsoft.srd5.bagdice.models.Dice;

import java.util.List;

public interface BagDiceService {

    int rollDice(Dice dice);
    int rollMultipleDices(Dice ... dices);

    int rollAdvantageRoll();
}
