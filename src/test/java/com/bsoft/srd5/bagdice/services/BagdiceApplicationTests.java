package com.bsoft.srd5.bagdice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bsoft.srd5.bagdice.data.CommonDice;
import com.bsoft.srd5.bagdice.models.Dice;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BagdiceApplicationTests {

	@InjectMocks
	BagDiceServiceImpl bagDiceServiceImpl;

	@Mock
	DiceRandomizerService diceRandomizerService;

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
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
	void advantageRollTest() {
		when(diceRandomizerService.obtainRandomNumberForDice(any(Dice.class))).thenReturn(20,1);
		int result = bagDiceServiceImpl.rollAdvantageRoll();
		assertEquals(20, result);
	}

	@Test
	void disadvantageRollTest() {
		when(diceRandomizerService.obtainRandomNumberForDice(any(Dice.class))).thenReturn(20,1);
		int result = bagDiceServiceImpl.rollDisadvantageRoll();
		assertEquals(1, result);
	}
}
