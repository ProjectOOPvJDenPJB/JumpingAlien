package tests;

import static jumpingalien.tests.util.TestUtils.intArray;
import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jumpingalien.part1.facade.Facade;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Test;

public class MazubTest {

	@Test
	public void testCorrectInitialisedWithOnlySpriteArrayGiven() {
		
		
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		assertTrue(0 == alien.getXPosition());
		assertTrue(0 == alien.getYPosition());
	}
	
	@Test
	public void testSetHorizontalVelocity() {
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		double velocity;
		
		alien.setHorizontalVelocity(2);
		velocity = alien.getHorizontalVelocity();
		assertEquals(2.0,velocity,Util.DEFAULT_EPSILON);
		
		alien.setHorizontalVelocity(5);
		velocity = alien.getHorizontalVelocity();
		assertEquals(3.0,velocity,Util.DEFAULT_EPSILON);
		
		alien.setHorizontalVelocity(-5);
		velocity = alien.getHorizontalVelocity();
		assertEquals(0.0,velocity,Util.DEFAULT_EPSILON);
		//voor een of andere reden geeft die hier een fout, die geeft -5 terug terwijl die 0 zou moeten geven.
	}
	
	
	
}
