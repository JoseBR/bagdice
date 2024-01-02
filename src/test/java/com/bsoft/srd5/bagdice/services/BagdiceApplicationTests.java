package com.bsoft.srd5.bagdice.services;

import com.bsoft.srd5.bagdice.data.CommonDice;
import com.bsoft.srd5.bagdice.models.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BagdiceApplicationTests {

	@Autowired
	BagDiceService bagDiceService;

	@InjectMocks
	BagDiceServiceImpl bagDiceServiceImpl;

	@Mock
	DiceRandomizerService diceRandomizerService;

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@RepeatedTest(value=10000)
	void diceResultIsBetweenOneAndDiceValue() {
		int diceResult = bagDiceService.rollDice(CommonDice.D4);
		assertTrue(diceResult <= CommonDice.D4.getFaces() && diceResult > 0);
	}

	@Test
	void diceRollHitsMaxValue() {
		final int FACES = 20;
		int result = 0;
		for (int i=0; i<10000 && result < FACES; i++) {
			result = Integer.max(result, bagDiceService.rollDice(CommonDice.D20));
		}
		assertEquals(FACES,result);
	}

	@RepeatedTest(value=10000)
	void multipleDiceRollTest() {
		int diceResult = bagDiceService.rollMultipleDices(CommonDice.D4,CommonDice.D6);
		System.out.println("dice result: " + diceResult);
		assertTrue(diceResult <= (CommonDice.D4.getFaces() + CommonDice.D6.getFaces()));
		assertTrue(diceResult > 0);
	}

	@Test
	void multipleDiceRollMessageTest() {
		when(diceRandomizerService.obtainRandomNumberForDice(any(Dice.class))).thenReturn(4);
		int diceResult = bagDiceServiceImpl.rollMultipleDices(CommonDice.D4,CommonDice.D4);
		assertEquals(8,diceResult);
		String[] consoleOutput = outputStreamCaptor.toString().split("\n");
		assertEquals("Dice roll of D4(4) + D4(4) for a result of 8", consoleOutput[consoleOutput.length-1].trim());
	}

	@Test
	void diferentDiceRollTest() {

		when(diceRandomizerService.obtainRandomNumberForDice(CommonDice.D20)).thenReturn(20);
		when(diceRandomizerService.obtainRandomNumberForDice(CommonDice.D12)).thenReturn(12);

		int diceResult = bagDiceServiceImpl.rollMultipleDices(CommonDice.D20,CommonDice.D12);
		assertEquals(32,diceResult);

		String[] consoleOutput = outputStreamCaptor.toString().split("\n");
		assertEquals("Dice roll of D20(20) + D12(12) for a result of 32", consoleOutput[consoleOutput.length-1].trim());
	}

	@Test
	void advanteRollTest() {
		when(diceRandomizerService.obtainRandomNumberForDice(any(Dice.class))).thenReturn(20,1);
		int result = bagDiceServiceImpl.rollAdvantageRoll();
		assertEquals(20, result);
	}

	@Test
	void disadvanteRollTest() {
		when(diceRandomizerService.obtainRandomNumberForDice(any(Dice.class))).thenReturn(20,1);
		int result = bagDiceServiceImpl.rollDisadvantageRoll();
		assertEquals(1, result);
	}
}
