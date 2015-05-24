package jumpingalien.part3.programs.Expressions;

import java.util.Collection;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class SearchObject extends UnaryExpression<GameObject, Direction> {

	public SearchObject(Expression<Direction> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public GameObject evaluate(Program program) {
		GameObject possessedObject = program.getPossessedObject();
		Collection<GameObject> gameObjects = possessedObject.getWorld().getAllObjects();
		Direction direction = getOperand().evaluate(program);
		if ((direction == Direction.RIGHT) || (direction == Direction.LEFT)) {
			return searchHorizontal(possessedObject, gameObjects, direction);
		}
		else
			return searchVertical(possessedObject, gameObjects, direction);
	}
	
	private GameObject searchHorizontal(GameObject possessedObject, Collection<GameObject> gameObjects, Direction direction) {
		double xPos = possessedObject.getXPosition();
		double yPos = possessedObject.getYPosition();
		int height = possessedObject.getHeight();
		double distanceToClosestObject = possessedObject.getWorld().getPixelWidth();
		GameObject closestObject = null;
		for (GameObject gameObject : gameObjects) {
			if (!((yPos > gameObject.getYPosition() + gameObject.getHeight()) || (yPos + height < gameObject.getYPosition()))) {
				if (direction == Direction.RIGHT) {
					if (gameObject.getXPosition() - xPos < distanceToClosestObject) {
						closestObject = gameObject;
						distanceToClosestObject = gameObject.getXPosition() - xPos;
					}
				}
				else {
					if (xPos - gameObject.getXPosition() < distanceToClosestObject) {
						closestObject = gameObject;
						distanceToClosestObject = xPos - gameObject.getXPosition();
					}
				}
			}
					
		}
		return closestObject;
	}
	
	private GameObject searchVertical(GameObject possessedObject, Collection<GameObject> gameObjects, Direction direction) {
		double xPos = possessedObject.getXPosition();
		double yPos = possessedObject.getYPosition();
		int width = possessedObject.getWidth();
		double distanceToClosestObject = possessedObject.getWorld().getPixelHeight();
		GameObject closestObject = null;
		for (GameObject gameObject : gameObjects) {
			if (!((xPos > gameObject.getXPosition() + gameObject.getWidth()) || (xPos + width < gameObject.getXPosition()))) {
				if (direction == Direction.UP) {
					if (gameObject.getYPosition() - yPos < distanceToClosestObject) {
						closestObject = gameObject;
						distanceToClosestObject = gameObject.getYPosition() - yPos;
					}
				}
				else {
					if (yPos - gameObject.getYPosition() < distanceToClosestObject) {
						closestObject = gameObject;
						distanceToClosestObject = yPos - gameObject.getYPosition();
					}
				}
			}
		}
		return closestObject;
	}

}
