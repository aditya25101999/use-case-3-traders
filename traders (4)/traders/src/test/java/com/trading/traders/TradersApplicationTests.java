package com.trading.traders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.trading.traders.model.TradersModel;
import com.trading.traders.repository.TradersRepo;

@SpringBootTest
class TradersApplicationTests {

	@Autowired
	private TradersRepo tradersrepo;
	
	@Test
	void testcreateTrader() {
		TradersModel tm=new TradersModel();
		tm.setName("ashu");
		tm.setEmail("kane@gmail.com");
		tm.setBalance(0);

		String s1=tradersrepo.checkName(tm.getEmail());
		assertNull(s1);
		tradersrepo.save(tm);
		String s2=tradersrepo.checkName("kane@gmail.com");
		System.out.println(tradersrepo.checkName(tm.getEmail()));
		assertNotNull(s2);
	}
	
	@Test
	void testgetAllTraders()
	{	
		List<TradersModel> tradList=tradersrepo.findAll();
		assertTrue(tradList.size()>0);
	}
	@Test
	void testgetabRecordByEmail() {
//		System.out.println(tradersrepo.getRecordByEmail("kane@gmail.com").size());
		assertTrue(tradersrepo.getRecordByEmail("kane@gmail.com").size()==1);
		assertTrue(tradersrepo.getRecordByEmail("virat@gmail.com").size()==0);
	}
	
	@Test
	void testupdatenamebyemail() {
		assertTrue(tradersrepo.checkUpdated("pinky","kane@gmail.com")==1);
		assertTrue(tradersrepo.checkName("kane@gmail.com")=="pinky");
		assertTrue(tradersrepo.checkUpdated("pinky","kkkk@gmail.com")==0);
	}
	@Test
	void testaddmoney() {
		assertTrue(tradersrepo.addAmount(1000,"kane@gmail.com")==1);
		assertTrue(tradersrepo.checkBal("kane@gmail.com")==1000);
	}

}
