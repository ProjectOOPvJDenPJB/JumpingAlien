package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.Arrays;

import jumpingalien.model.School;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.Test;

public class SchoolTest {

	private Sprite[] defaultSprites;
	private World defaultWorld;
	private Slime slime1, slime2;
	private School preschool;
	
	@Before
	public void setUp() {
		this.defaultWorld = new World(10,10,10,10,10,9,9);
		this.defaultSprites = spriteArrayForSize(2,2);
		this.preschool = new School();
		this.slime1 = new Slime(1, 1, 0, 0, 0, defaultSprites, defaultWorld,preschool,70);
		this.slime2 = new Slime(5, 5, 0, 0, 0, defaultSprites, defaultWorld,preschool,70);
	}
	
	@Test
	public void extendedConstructor$LegalCase() {	
		School school = new School();
		assertTrue(school.getAmountSlimes() == 0);
	}
	
	@Test
	public void TestAddingSlimes(){
		School school = new School();
		school.addSlime(slime1);
		assertTrue(school.getAmountSlimes() == 1);
		assertTrue(school.getSlimes().contains(slime1));
		assertTrue(slime1.getSchool() == school);
		assertTrue(slime2.getHP()==71);
		assertTrue(slime1.getHP() == 69);
		
		school.addSlime(slime2);
		assertTrue(school.getAmountSlimes() == 2);
		assertTrue(school.getSlimes().contains(slime2));
		assertTrue(slime2.getSchool() == school);
		assertTrue(slime2.getHP()==72);
		assertTrue(slime1.getHP() == 68);
	}
	
	@Test 
	public void testRemovingSlime(){
		preschool.removeSlime(slime1);
		assertFalse(preschool.getSlimes().contains(slime1));
		assertTrue(slime1.getSchool() == null);
	}
}
