/**
 * 
 */
package jumpingalien.part3.programs;

import java.util.List;
import java.util.Map;

import jumpingalien.model.GameObject;
import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.*; //Because we need it all
import jumpingalien.part3.programs.Statements.*;

/**
 * @author Joren
 *
 */
public class ProgramFactory implements IProgramFactory<Expression<?>, Statement, Type, Program> {
	//dit geeft een fout om dat nog niet alle methodes juist zijn geïmplementeerd, 

	@Override
	public Expression<?> createReadVariable(String variableName, Type variableType,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Double> createDoubleConstant(double value, SourceLocation sourceLocation) {
		return new DoubleLiteral(value);
	}

	@Override
	public Expression<Boolean> createTrue(SourceLocation sourceLocation) {
		return new BooleanTrue();
	}

	@Override
	public Expression<Boolean> createFalse(SourceLocation sourceLocation) {
		return new BooleanFalse();
	}

	@Override
	public Expression<Object> createNull(SourceLocation sourceLocation) {
		return new ObjectNull();
	}

	@Override
	public Expression<Object> createSelf(SourceLocation sourceLocation) {
		return new ObjectSelf();
	}

	@Override
	public Expression<jumpingalien.part3.programs.IProgramFactory.Direction> createDirectionConstant(
			jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		return new DirectionExpression(value); 
	}

	@Override
	public Expression<Double> createAddition(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Addition(left,right);
	}

	@Override
	public Expression<Double> createSubtraction(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Addition(left,new NegateDoubleLiteral(right));
	}

	@Override
	public Expression<Double> createMultiplication(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Multiplication(left,right);
	}

	@Override
	public Expression<Double> createDivision(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Multiplication(left,new Inverse(right));
	}

	@Override
	public Expression<Double> createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new SquareRoot(expr);
	}

	@Override
	public Expression<?> createRandom(Expression maxValue, SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Expression<Boolean> createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Conjunction(left,right);
	}

	@Override
	public Expression<Boolean> createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Expression<Boolean> createNot(Expression expr, SourceLocation sourceLocation) {
		return new NegateBoolean(expr);
	}

	@Override
	public Expression<Boolean> createLessThan(Expression left, Expression right, SourceLocation sourceLocation) {
		return new LessThan(left,right);
	}

	@Override
	public Expression<Boolean> createLessThanOrEqualTo(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Disjunction(new LessThan(left,right),new Equals(left,right));
	}

	@Override
	public Expression<Boolean> createGreaterThan(Expression left, Expression right, SourceLocation sourceLocation) {
		return new LessThan(right,left);
	}

	@Override
	public Expression<Boolean> createGreaterThanOrEqualTo(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Disjunction(new LessThan(right,left),new Equals(left,right));
	}

	@Override
	public Expression<Boolean> createEquals(Expression left, Expression right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		//Ik ga hier gewoon verder op onze huidige implementatie van 
		return new Equals(left,right);
	}

	@Override
	public Expression<Boolean> createNotEquals(Expression left, Expression right, SourceLocation sourceLocation) {
		return new NegateBoolean(new Equals(left,right));
	}

	@Override
	public Expression<Double> createGetX(Expression expr, SourceLocation sourceLocation) {
		return new GetX(expr);
	}

	@Override
	public Expression<Double> createGetY(Expression expr, SourceLocation sourceLocation) {
		return new GetY(expr);
	}

	@Override
	public Expression<Integer> createGetWidth(Expression expr, SourceLocation sourceLocation) {
		return new GetWidth(expr);
	}

	@Override
	public Expression<Integer> createGetHeight(Expression expr, SourceLocation sourceLocation) {
		return new GetHeight(expr);
	}

	@Override
	public Expression<Integer> createGetHitPoints(Expression expr, SourceLocation sourceLocation) {
		return new GetHP(expr);
	}

	@Override
	public E createGetTile(E x, E y, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		//TODO create GetTile class
		return null;
	}

	@Override
	public E createSearchObject(E direction, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		//TODO create search object class
		return null;
	}

	@Override
	public Expression<Boolean> createIsMazub(Expression expr, SourceLocation sourceLocation) {
		return new IsMazub(expr);
	}

	@Override
	public Expression<Boolean> createIsShark(Expression expr, SourceLocation sourceLocation) {
		return new IsShark(expr);
	}

	@Override
	public Expression<Boolean> createIsSlime(Expression expr, SourceLocation sourceLocation) {
		return new IsSlime(expr);
	}

	@Override
	public Expression<Boolean> createIsPlant(Expression expr, SourceLocation sourceLocation) {
		return new IsPlant(expr);
	}

	@Override
	public Expression<Boolean> createIsDead(Expression expr, SourceLocation sourceLocation) {
		return new IsDead(expr);
	}

	@Override
	public Expression<Boolean> createIsTerrain(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IsTerrain(expr);
	}

	@Override
	public Expression<Boolean> createIsPassable(Expression expr, SourceLocation sourceLocation) {
		return new IsPassable(expr);
	}

	@Override
	public Expression<Boolean> createIsWater(Expression expr, SourceLocation sourceLocation) {
		return new IsWater(expr);
	}

	@Override
	public Expression<Boolean> createIsMagma(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IsMagma(expr);
	}

	@Override
	public Expression<Boolean> createIsAir(Expression expr, SourceLocation sourceLocation) {
		return new IsAir(expr);
	}

	@Override
	public Expression<Boolean> createIsMoving(Expression expr, Expression direction, SourceLocation sourceLocation) {
		//TODO klopt niet in de class is moving, direction is nodig als argument
		return new IsMoving(expr);
	}

	@Override
	public Expression<Boolean> createIsDucking(Expression expr, SourceLocation sourceLocation) {
		return new IsDucking(expr);
	}

	@Override
	public Expression<Boolean> createIsJumping(Expression expr, SourceLocation sourceLocation) {
		return new IsJumping(expr);
	}

	@Override
	public Statement createAssignment(String variableName, Type variableType, Expression value,
			SourceLocation sourceLocation) {
		// TODO Vanaf hier weet ik het nie
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
