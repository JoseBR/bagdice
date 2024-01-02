package com.bsoft.srd5.bagdice.services;

import com.bsoft.srd5.bagdice.models.Dice;

public interface BagDiceService {

    int rollDice(Dice dice);
    int rollMultipleDices(Dice ... dices);

    int rollAdvantageRoll();
    int rollDisadvantageRoll();
}
