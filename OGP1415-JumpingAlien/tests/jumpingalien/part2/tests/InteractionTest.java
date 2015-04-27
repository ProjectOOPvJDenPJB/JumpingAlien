package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Interaction;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.Test;

public class InteractionTest {
	
	private Sprite[] defaultSprites;
	private School school,school2,school3;
	private Slime slime1,slime2,slime3,TheChosenSlime;
	private World world;
	private Shark shark;
	private Mazub mazub;
	private Plant plant;

	
	
	@Before
	public void setUp() {
		this.defaultSprites = spriteArrayForSize(2,2);
		this.school = new School();
		this.school2 = new School();
		this.school3 = new School();
		this.world = new World(10,100,100,500,500,700,700);
		this.slime1 = new Slime(100,100, 0,0,0,defaultSprites,null,school,100);
		this.slime2 = new Slime(102,102,0,0,0,defaultSprites,null,school,100);
		this.slime3 = new Slime(202,202,0,0,0,defaultSprites,null,school2,100);
		this.TheChosenSlime = new Slime(302,302,0,0,0,defaultSprites,null,school3,100);
		this.shark = new Shark(110, 110, 0, 0, null,defaultSprites,100);
		this.mazub = new Mazub(80,80,defaultSprites,5,3,null,100);
		this.plant = new Plant(40,40,0.5,defaultSprites,null,1);
		
		world.addSlime(slime1);
		world.addSlime(slime2);
		world.addSlime(slime3);
		world.addSlime(TheChosenSlime);
		world.addShark(shark);
		world.setMazub(mazub);
		world.addPlant(plant);

	}
	
	@Test
	//the width of a slime == 2
	//the height of a slime == 2
	public void collidesWithCreature_True(){
		slime2.setXPosition(101);
		slime2.setYPosition(101);
		assertTrue(Interaction.collidesWithCreature(slime1, slime2));
		slime2.setYPosition(99);
		assertTrue(Interaction.collidesWithCreature(slime1, slime2));
		slime2.setXPosition(99);
		assertTrue(Interaction.collidesWithCreature(slime1, slime2));
		slime2.setYPosition(101);
		assertTrue(Interaction.collidesWithCreature(slime1, slime2));


	}
	
	@Test
	//the width of a slime == 2
	//the height of a slime == 2
	public void collidesWithCreature_False(){
		assertFalse(Interaction.collidesWithCreature(slime1, slime2));
		slime2.setXPosition(100);
		assertFalse(Interaction.collidesWithCreature(slime1, slime2));
		slime2.setXPosition(102);
		slime2.setYPosition(102);
		assertFalse(Interaction.collidesWithCreature(slime1, slime2));
		slime2.setXPosition(99);
		slime2.setYPosition(98);
		assertFalse(Interaction.collidesWithCreature(slime1, slime2));
	}
	
	@Test
	public void interactWithMovementBlockingCreature_true(){
		slime2.setXPosition(100);
		slime2.setYPosition(100);
		assertTrue(Interaction.interactWithMovementBlockingCreature(slime1, slime1.getWorld()));
		assertTrue(Interaction.interactWithMovementBlockingCreature(slime2, slime2.getWorld()));
		slime2.setXPosition(102);
		slime2.setYPosition(102);
		shark.setXPosition(101);
		shark.setYPosition(101);
		assertTrue(Interaction.interactWithMovementBlockingCreature(slime1, slime1.getWorld()));
		assertTrue(Interaction.interactWithMovementBlockingCreature(shark, shark.getWorld()));
	}
	
	@Test
	public void interactWithMovementBlockingCreature_false(){
		assertFalse(Interaction.interactWithMovementBlockingCreature(shark, shark.getWorld()));
		assertFalse(Interaction.interactWithMovementBlockingCreature(slime1, slime1.getWorld()));
		assertFalse(Interaction.interactWithMovementBlockingCreature(slime2, slime2.getWorld()));
	}
	
	@Test
	public void interactWithPlant$Mazub(){
		Interaction.interactWithPlant(mazub, plant);
		assertEquals(mazub.getHP(),150);
		assertEquals(plant.getHP(),0);
	}
	
	@Test
	public void interactWithPlant$Rest(){
		Interaction.interactWithPlant(shark, plant);
		assertEquals(shark.getHP(),100);
		assertEquals(plant.getHP(),1);
		
		Interaction.interactWithPlant(slime1, plant);
		assertEquals(slime1.getHP(),100);
		assertEquals(plant.getHP(),1);
	}
	
	@Test
	public void interactWithSlime$NotSLime(){
		Interaction.interactWithSlime(mazub, slime1);
		assertEquals(mazub.getHP(),50);
		assertEquals(slime1.getHP(),50);
		
		Interaction.interactWithSlime(mazub, slime1);
		assertEquals(mazub.getHP(),50);
		assertEquals(slime1.getHP(),50);
	}
	
	@Test
	public void interactWithShark$NotShark(){
		Interaction.interactWithShark(mazub, shark);
		assertEquals(mazub.getHP(),50);
		assertEquals(shark.getHP(),50);
		
		Interaction.interactWithShark(mazub, shark);
		assertEquals(mazub.getHP(),50);
		assertEquals(shark.getHP(),50);
	}
	
	@Test
	public void changeSchool_OneSmallerSchool(){
		Interaction.changeSchool(slime1, slime3);
		assertEquals(slime1.getSchool(),school);
		assertEquals(slime3.getSchool(),school);
		
		Interaction.changeSchool(TheChosenSlime, slime1);
		assertEquals(slime1.getSchool(),school);
		assertEquals(TheChosenSlime.getSchool(),school);
	}

	@Test
	public void changeSchool_equallyLargeSchool(){
		Interaction.changeSchool(TheChosenSlime, slime3);
		assertEquals(slime3.getSchool(),school2);
		assertEquals(TheChosenSlime.getSchool(),school3);

	}
}
