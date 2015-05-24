package jumpingalien.part3.programs.Expressions;

import java.util.Random;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class createRandom extends UnaryExpression<Double, Double> {

	public createRandom(Expression<Double> maxValue) throws IllegalOperandException {
		super(maxValue);
	}
	
	public double generateRandom(double maxValue){
		double randomDouble = maxValue * new Random().nextDouble();
		return randomDouble;
	}

	@Override
	public Double evaluate(Program program) {
		return generateRandom(this.evaluate(program));
	}

}
