package com.bsoft.srd5.bagdice.services;

import com.bsoft.srd5.bagdice.data.CommonDice;
import com.bsoft.srd5.bagdice.models.Dice;
import java.util.Arrays;
import org.springframework.stereotype.Service;
@Service
public class BagDiceServiceImpl implements BagDiceService {

    final DiceRandomizerService diceRandomizerService;

    public BagDiceServiceImpl() {
        this.diceRandomizerService = new DiceRandomizerServiceImpl();
    }

    @Override
    public int rollDice(Dice dice) {
        int result = diceRandomizerService.obtainRandomNumberForDice(dice);
        System.out.println("D" + dice.getFaces() + " rolled " + result);
        return result;
    }

    @Override
    public int rollMultipleDices(Dice... dices) {
        StringBuilder diceRolledMessage = new StringBuilder("Dice roll of");
        int[] diceResults = new int[dices.length];
        if (dices.length == 0) {
            throw new IllegalArgumentException("No hay ningun dado");
        }
        for (int i=0; i<dices.length; i++) {
            diceResults[i] = rollDice(dices[i]);
            diceRolledMessage.append(" ")
                    .append(dices[i])
                    .append("(")
                    .append(diceResults[i])
                    .append(")");
            if (i<dices.length-1) {
                diceRolledMessage.append(" +");
            }
        }
        diceRolledMessage.append(" for a result of ").append(Arrays.stream(diceResults).sum());
        System.out.println(diceRolledMessage);
        return Arrays.stream(diceResults).sum();
    }

    @Override
    public int rollAdvantageRoll() {
        int firstResult = rollDice(CommonDice.D20);
        int secondResult = rollDice(CommonDice.D20);

        return Integer.max(firstResult,secondResult);
    }

    public int rollDisadvantageRoll() {
        int firstResult = rollDice(CommonDice.D20);
        int secondResult = rollDice(CommonDice.D20);

        return Integer.min(firstResult,secondResult);
    }

}
