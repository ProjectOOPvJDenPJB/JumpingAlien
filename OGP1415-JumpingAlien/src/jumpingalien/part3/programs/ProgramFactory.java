/**
 * 
 */
package jumpingalien.part3.programs;

import java.util.List;
import java.util.Map;

import jumpingalien.model.GameObject;
import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.Expressions.*; //Because we need it all

/**
 * @author Joren
 *
 */
public class ProgramFactory<E, S, T, P> implements IProgramFactory<E, S, T, P> {

	@Override
	public E createReadVariable(String variableName, T variableType,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Double> createDoubleConstant(double value, SourceLocation sourceLocation) {
		// TODO iets met sourceLocation
		return new DoubleLiteral(value);
	}

	@Override
	public Expression<Boolean> createTrue(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new BooleanTrue();
	}

	@Override
	public Expression<Boolean> createFalse(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new BooleanFalse();
	}

	@Override
	public Expression<Object> createNull(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ObjectNull();
	}

	@Override
	public Expression<Object> createSelf(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ObjectSelf();
	}

	@Override
	public Expression<jumpingalien.part3.programs.IProgramFactory.Direction> createDirectionConstant(
			jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new DirectionExpression(value); 
	}

	@Override
	public Expression<Double> createAddition(Expression<Double> left, Expression<Double> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Addition(left,right);
	}

	@Override
	public Expression<Double> createSubtraction(Expression<Double> left, Expression<Double> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Addition(left,new NegateDoubleLiteral(right));
	}

	@Override
	public Expression<Double> createMultiplication(Expression<Double> left, Expression<Double> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Multiplication(left,right);
	}

	@Override
	public Expression<Double> createDivision(Expression<Double> left, Expression<Double> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Multiplication(left,new Inverse(right));
	}

	@Override
	public Expression<Double> createSqrt(Expression<Double> expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new SquareRoot(expr);
	}

	@Override
	public E createRandom(E maxValue, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Boolean> createAnd(Expression<Boolean> left, Expression<Boolean> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Conjunction(left,right);
	}

	@Override
	public Expression<Boolean> createOr(Expression<Boolean> left, Expression<Boolean> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Boolean> createNot(Expression<Boolean> expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new NegateBoolean(expr);
	}

	@Override
	public Expression<Boolean> createLessThan(Expression<Double> left, Expression<Double> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new LessThan(left,right);
	}

	@Override
	public Expression<Boolean> createLessThanOrEqualTo(Expression<Double> left, Expression<Double> right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Disjunction(new LessThan(left,right),new Equals(left,right));
	}

	@Override
	public Expression<Boolean> createGreaterThan(Expression<Double> left, Expression<Double> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new LessThan(right,left);
	}

	@Override
	public Expression<Boolean> createGreaterThanOrEqualTo(Expression<Double> left, Expression<Double> right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Disjunction(new LessThan(right,left),new Equals(left,right));
	}

	@Override
	public Expression<Boolean> createEquals(Expression<Double> left, Expression<Double> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		//Ik ga hier gewoon verder op onze huidige implementatie van 
		return new Equals(left,right);
	}

	@Override
	public Expression<Boolean> createNotEquals(Expression<Double> left, Expression<Double> right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new NegateBoolean(new Equals(left,right));
	}

	@Override
	public Expression<Double> createGetX(Expression<GameObject> expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new GetX(expr);
	}

	@Override
	public Expression<Double> createGetY(Expression<GameObject> expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new GetY(expr);
	}

	@Override
	public Expression<Integer> createGetWidth(Expression<GameObject> expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new GetWidth(expr);
	}

	@Override
	public Expression<Integer> createGetHeight(Expression<GameObject> expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new GetHeight(expr);
	}

	@Override
	public Expression<Integer> createGetHitPoints(Expression<LivingCreatures> expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new GetHP(expr);
	}

	@Override
	public E createGetTile(E x, E y, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createSearchObject(E direction, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsMazub(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsShark(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsSlime(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsPlant(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsDead(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsTerrain(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsPassable(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsWater(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsMagma(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsAir(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsMoving(E expr, E direction, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsDucking(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createIsJumping(E expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createAssignment(String variableName, T variableType, E value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createWhile(E condition, S body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createForEach(
			String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			E where,
			E sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			S body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createBreak(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createIf(E condition, S ifBody, S elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createPrint(E value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createStartRun(E direction, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createStopRun(E direction, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createStartJump(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createStopJump(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createStartDuck(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createStopDuck(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createWait(E duration, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createSkip(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createSequence(List<S> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getDoubleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getBoolType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getGameObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getDirectionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public P createProgram(S mainStatement, Map<String, T> globalVariables) {
		// TODO Auto-generated method stub
		return null;
	}

}
