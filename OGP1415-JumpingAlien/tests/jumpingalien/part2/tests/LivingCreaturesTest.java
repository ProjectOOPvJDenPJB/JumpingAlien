package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Mazub;
import jumpingalien.tests.util.TestUtils;

import org.junit.Test;

public class LivingCreaturesTest {

	@Test
	public void isValidInitialVelocity_True(){
		assertTrue(LivingCreatures.isValidInitialVelocity(3, 5));
	}
	
	@Test
	public void isValidInitialVelocity_False(){
		assertFalse(LivingCreatures.isValidInitialVelocity(3, 2));
		assertFalse(LivingCreatures.isValidInitialVelocity(0, 2));
	}	
	
	@Test
	public void isValidHorizontalAcceleration_True() {
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(0));
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(800));
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(27));
	}
	
	@Test
	public void isValidHorizontalAcceleration_False() {
		assertFalse(LivingCreatures.isValidHorizontalAcceleration(-0.1));
		assertFalse(LivingCreatures.isValidHorizontalAcceleration(-9));
		assertFalse(LivingCreatures.isValidHorizontalAcceleration(-531));
	}
	
	@Test
	public void isValidVerticalAcceleration_True(){
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(0));
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(-10));
	}
	
	@Test
	public void isValidVerticalAcceleration_False(){
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(5));
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(-5));
	}
	
	@Test
	public void isValidTimerValue_True(){
		assertTrue(LivingCreatures.isValidTimerValue(5));
		assertTrue(LivingCreatures.isValidTimerValue(0));
	}
	
	@Test
	public void isValidTimerValue_False(){
		assertTrue(LivingCreatures.isValidTimerValue(-5));
	}	
	
	@Test
	public void isValidTimeInterval_True() {
		assertTrue(LivingCreatures.isValidTimeInterval(0));
		assertTrue(LivingCreatures.isValidTimeInterval(0.2));
		assertTrue(LivingCreatures.isValidTimeInterval(0.12));
	}
	
	@Test
	public void isValidTimeInterval_False() {
		assertFalse(LivingCreatures.isValidTimeInterval(-0.1));
		assertFalse(LivingCreatures.isValidTimeInterval(-3));
		assertFalse(LivingCreatures.isValidTimeInterval(0.21));
		assertFalse(LivingCreatures.isValidTimeInterval(6));
	}
	
	@Test
	public void isValidMaximumHorizontalVelocity_True(){
		assertTrue(LivingCreatures.isValidMaximumHorizontalVelocity(3, 2));
	}
	
	@Test
	public void isValidMaximumHorizontalVelocity_False(){
		assertFalse(LivingCreatures.isValidMaximumHorizontalVelocity(2, 3));
	}
	
	
	@Test
	public void isValidSize_True() {
		assertTrue(Mazub.isValidSize(0));
		assertTrue(Mazub.isValidSize(8));
		assertTrue(Mazub.isValidSize(87));
	}
	
	@Test
	public void isValidSize_False() {
		assertFalse(Mazub.isValidSize(-1));
		assertFalse(Mazub.isValidSize(-8));
	}
}
