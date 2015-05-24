/**
 * 
 */
package jumpingalien.part3.programs;

import java.util.List;
import java.util.Map;

import jumpingalien.model.GameObject;
import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Program;
import jumpingalien.model.Tile;
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
	public Expression<Tile> createGetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		return new GetTile(x, y);
	}

	@Override
	public Expression<GameObject> createSearchObject(Expression direction, SourceLocation sourceLocation) {
		return new SearchObject(direction);
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
		return new Assignment(variableName, variableType, value);
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
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new Print(value);
	}

	@Override
	public Statement createStartRun(Expression direction, SourceLocation sourceLocation) {
		return new StartRun(direction);
	}

	@Override
	public Statement createStopRun(Expression direction, SourceLocation sourceLocation) {
		return new StopRun(direction);
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		return new StartJump();
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		return new StopJump();
	}

	@Override
	public S createStartDuck(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		return new StopDuck();
	}

	@Override
	public Statement createWait(Expression duration, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		return new Skip();
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new Sequence(statements);
	}

	@Override
	public Type getDoubleType() {
		return Type.DOUBLE;
	}

	@Override
	public Type getBoolType() {
		return Type.BOOLEAN;
	}

	@Override
	public Type getGameObjectType() {
		return Type.OBJECT;
	}

	@Override
	public Type getDirectionType() {
		return Type.DIRECTION;
	}

	@Override
	public Program createProgram(Statement mainStatement, Map<String, Type> globalVariables) {
		return new Program(mainStatement, globalVariables);
	}

}
