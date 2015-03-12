package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import jumpingalien.part1.facade.Facade;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import org.junit.Test;

import static jumpingalien.tests.util.TestUtils.*;

public class FacadeTests {

	@Test
	public void testCorrectInitialised() {
		IFacade facade = new Facade();

		Mazub alien1 = facade.createMazub(-1, -1, spriteArrayForSize(2, 2));
		assertArrayEquals(intArray(0, 0), facade.getLocation(alien1));
		
		Mazub alien2 = facade.createMazub(1028, 800, spriteArrayForSize(2, 2));
		assertArrayEquals(intArray(1024, 768), facade.getLocation(alien2));
	}
	
	@Test
	public void startJumpCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startJump(alien);
		facade.advanceTime(alien, 0.2);
		// y_new [m] = 0 + 8 [m/s] * 0.2[s] - 5 [m/s^2] * (0.2 [s])^2 =
		// 1.40[m] = 140.0 [cm], which falls into pixel (21, 140)
		assertArrayEquals(intArray(0, 140), facade.getLocation(alien));
	}
	@Test
	public void endJump_whileVerticalVelocityIsGreaterThanZero(){
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 10, spriteArrayForSize(2, 2));
		facade.startJump(alien);
		facade.endJump(alien);
		assertArrayEquals(doubleArray(0, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void endJump_whileVerticalVelocityIsSmallerThanZero(){
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 10, spriteArrayForSize(2, 2));
		alien.setVerticalVelocity(-10);
		facade.endJump(alien);
		assertArrayEquals(doubleArray(0, -10), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	@Test
	public void startMoveRightCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.1);

		// x_new [m] = 0 + 1 [m/s] * 0.1 [s] + 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 0.1045 [m] = 10.45 [cm], which falls into pixel (10, 0)

		assertArrayEquals(intArray(10, 0), facade.getLocation(alien));
	}
	
	@Test
	public void endMoveRightCorrect(){
		IFacade facade = new Facade();
		
		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.endMoveRight(alien);
		assertTrue(alien.getHorizontalVelocity() == 0);
		
		facade.startMoveLeft(alien);
		facade.endMoveRight(alien);
		assertTrue(alien.getHorizontalVelocity() == 1);
	}
	
	@Test
	public void startMoveLeftCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(200, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		facade.advanceTime(alien, 0.1);

		// x_new [m] = 2 - 1 [m/s] * 0.1 [s] - 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 1.8955 [m] = 189.55 [cm], which falls into pixel (189, 0)

		assertArrayEquals(intArray(189, 0), facade.getLocation(alien));
	}
	
	@Test
	public void endMoveLeftCorrect(){
		IFacade facade = new Facade();
		
		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		facade.endMoveLeft(alien);
		assertTrue(alien.getHorizontalVelocity() == 0);
		
		facade.startMoveRight(alien);
		facade.endMoveLeft(alien);
		assertTrue(alien.getHorizontalVelocity() == 1);
	}

	@Test
	public void startMoveRightMaxSpeedAtRightTime() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2 / 9);
		}

		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void startMoveLeftMaxSpeedAtRightTime() {
		IFacade facade = new Facade();
	
		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2 / 9);
		}
	
		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
		}

	@Test
	public void testAccellerationZeroWhenNotMoving() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}

	@Test
	public void testWalkAnimationLastFrame() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);

		facade.startMoveRight(alien);

		facade.advanceTime(alien, 0.005);
		System.out.println( facade.getCurrentSprite(alien));
		for (int i = 0; i < m; i++) {
			facade.advanceTime(alien, 0.075);
			System.out.println( facade.getCurrentSprite(alien));
		}
		assertEquals(sprites[8+m], facade.getCurrentSprite(alien));
	}

	@Test
	public void testAccellerationZeroWhenMovingAndJumping() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.startJump(alien);
		assertArrayEquals(doubleArray(0.9, -10), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void movingRightWhileDuckingCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startDuck(alien);
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.2);
		// x_new [m] = 0 + 1 [m/s] * 0.2 [s] =
		// 0.20 [m] = 20 [cm], which falls into pixel (20, 0)
		assertArrayEquals(intArray(20, 0), facade.getLocation(alien));
	}
	
	@Test
	public void movingLeftWhileDuckingCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(30, 0, spriteArrayForSize(2, 2));
		facade.startDuck(alien);
		facade.startMoveLeft(alien);
		facade.advanceTime(alien, 0.2);
		// x_new [m] = 0.30 - 1 [m/s] * 0.2 [s] =
		// 0.10 [m] = 30 [cm], which falls into pixel (10, 0)
		assertArrayEquals(intArray(10, 0), facade.getLocation(alien));
	}
	
	@Test
	public void movingOverLeftBorderCorrect() {
		IFacade facade = new Facade();
		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		facade.advanceTime(alien, 0.2);
		assertArrayEquals(intArray(0, 0), facade.getLocation(alien));
	}
	
	@Test
	public void movingOverRigthBorderCorrect() {
		IFacade facade = new Facade();
		Mazub alien = facade.createMazub(1024, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.2);
		assertArrayEquals(intArray(1024, 0), facade.getLocation(alien));
	}
	
	@Test
	public void advanceTimeMovingCorrect() {
		IFacade facade = new Facade();
	
		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.1);
		// x_new [m] = 0 + 1 [m/s] * 0.1 [s] + 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 0.1045 [m] = 10.45 [cm], which falls into pixel (10, 0)
		assertArrayEquals(intArray(10, 0), facade.getLocation(alien));
		//horizontal_velocity_new = 1 [m/s] + 0.9 [m/s^2] * 0.1 [s] = 1.09 [m/s]
		assertArrayEquals(doubleArray(1.09, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
		assertArrayEquals(doubleArray(0.9, 0.0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}
		
		@Test
		public void advanceTimeMovingAndJumpingCorrect() {
		IFacade facade = new Facade();
		
		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.startJump(alien);
		facade.advanceTime(alien, 0.2);
		// x_new [m] = 0 + 1 [m/s] * 0.2 [s] + 1/2 0.9 [m/s^2] * (0.2 [s])^2 =
				// 0.218 [m] = 21.8 [cm], which falls into pixel (21, 0)
		// y_new [m] = 0 + 8 [m/s] * 0.2[s] - 5 [m/s^2] * (0.2 [s])^2 =
				// 1.40[m] = 140.0 [cm], which falls into pixel (21, 140)
		assertArrayEquals(intArray(21, 140), facade.getLocation(alien));
		//horizontal_velocity_new = 1 [m/s] + 0.9 [m/s^2] * 0.2 [s]
		//vertical_velocity_new = 8 [m/s] - 10 [m/s^2] * 0.2 [s] = 6.0 [m/s]
		assertArrayEquals(doubleArray(1.18, 6.00), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
		assertArrayEquals(doubleArray(0.9, -10.0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
		}
		
		@Test
		public void advanceTimeMovingAndDuckingCorrect() {
		IFacade facade = new Facade();
		
		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startDuck(alien);
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.2);
		// x_new [m] = 0 + 1 [m/s] * 0.2 [s] =
		// 0.20 [m] = 20 [cm], which falls into pixel (20, 0)
		assertArrayEquals(intArray(20, 0), facade.getLocation(alien));
		//horizontal_velocity_new = 1 [m/s] 
		assertArrayEquals(doubleArray(1.00, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
		//Acceleration = 0 [m/s^2]
		assertArrayEquals(doubleArray(0, 0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
		}
	
	
}
