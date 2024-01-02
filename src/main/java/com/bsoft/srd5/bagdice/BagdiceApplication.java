package com.bsoft.srd5.bagdice;

import com.bsoft.srd5.bagdice.data.CommonDice;
import com.bsoft.srd5.bagdice.services.BagDiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BagdiceApplication {

	@Autowired
	static BagDiceServiceImpl bagDiceService;

	public static void main(String[] args) {
		SpringApplication.run(BagdiceApplication.class, args);
		bagDiceService = new BagDiceServiceImpl();
		bagDiceService.rollDice(CommonDice.D4);
	}

}
