package jumpingalien.part3.programs;

import java.util.HashMap;

import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.Statements.Statement;

public class Program {
	
	public Program(Statement mainStatement, HashMap<String,Type> globalVariables) {
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
	}
	
	private Statement getMainStatement() {
		return mainStatement;
	}
	
	private final Statement mainStatement;
	
	private HashMap <String, Type> getGlobalVariables() {
		HashMap<String,Type> globalVariablesClone = new HashMap<String, Type>(globalVariables);
		return globalVariablesClone;
	}
	
	private final HashMap<String, Type> globalVariables;
	
	public HashMap<String, Object> getVariableValues() {
		HashMap<String, Object> variableValuesClone = new HashMap<String, Object>(variableValues);
		return variableValuesClone;
	}
	
	private HashMap<String, Object> variableValues;
	
	public void setVariableValue(String varName, Object value) {
		if (! getGlobalVariables().containsKey(varName))
			throw new IllegalArgumentException("The variable of which the value was trying"
					+ "to be added is not a global variable in this program.");
		this.variableValues.put(varName, value);
	}
	
	public LivingCreatures getPossessedObject() {
		return this.possessedObject;
	}
	
	private LivingCreatures possessedObject;
	
	public void setPossessedObject(LivingCreatures object) {
		this.possessedObject = object;
	}
	
	public void excecute() {
		getMainStatement().execute();
		//TODO!!! Dit moet nog met excecution time etc
	}

}
