package jumpingalien.part1.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import jumpingalien.part1.facade.Facade;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Test;

import static jumpingalien.tests.util.TestUtils.*;

public class PartialFacadeTest {

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
		//de eerste sprite is een 0 terwijl die al een 8 zou moeten zijn, daardoor komen we op het laatse 1 sprite te vroeg uit.
	}

	// TODO: add more tests
	
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
	public void testCorrectInitialised() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(-1, -1, spriteArrayForSize(2, 2));
		assertArrayEquals(intArray(0, 0), facade.getLocation(alien));
	}
	//bij deze test krijgen we wel een error, nu is mijn vraag:
	//aangezien dit defensief is mag het dan geen error zijn? Want wij gooien een exception
	//het program stopt en de gebruiker ziet dat hij iets fout ingeeft.
	

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
		System.out.println( alien.getXPosition());
		assertArrayEquals(intArray(20, 0), facade.getLocation(alien));
	}
	//deze functie geeft aan dat we nog een fout hebben, bij change horizontal velocity wordt er bij uw positie altijd rekening
	//gehouden met uw versnelling. Maar als ge op max snelheid zit is dit niet meer nodig. 
	
	@Test
	public void movingOverBorderCorrect() {
		IFacade facade = new Facade();
		Mazub alien1 = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien1);
		facade.advanceTime(alien1, 0.2);
		assertArrayEquals(intArray(0, 0), facade.getLocation(alien1));
		
		Mazub alien2 = facade.createMazub(1024, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien2);
		facade.advanceTime(alien2, 0.2);
		assertArrayEquals(intArray(1024, 0), facade.getLocation(alien2));
	}
	
	
	
	
}
