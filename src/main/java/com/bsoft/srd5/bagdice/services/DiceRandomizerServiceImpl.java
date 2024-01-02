package com.bsoft.srd5.bagdice.services;

import com.bsoft.srd5.bagdice.models.Dice;
import org.springframework.stereotype.Service;

import java.util.random.RandomGenerator;

@Service
public class DiceRandomizerServiceImpl implements DiceRandomizerService {

    private final RandomGenerator generator;
    public DiceRandomizerServiceImpl() {
        this.generator = RandomGenerator.getDefault();
    }
    @Override
    public Integer obtainRandomNumberForDice(Dice dice) {
        return generator.nextInt(dice.getFaces())+1;
    }
}
