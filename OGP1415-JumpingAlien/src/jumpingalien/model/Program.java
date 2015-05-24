package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.part3.programs.Type;
import jumpingalien.part3.programs.Statements.Statement;

public class Program {
	
	public Program(Statement mainStatement, Map<String, Type> globalVariables2) {
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables2;
	}
	
	private Statement getMainStatement() {
		return mainStatement;
	}
	
	private final Statement mainStatement;
	
	private HashMap <String, Type> getGlobalVariables() {
		HashMap<String,Type> globalVariablesClone = new HashMap<String, Type>(globalVariables);
		return globalVariablesClone;
	}
	
	private final Map<String, Type> globalVariables;
	
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
		getMainStatement().execute(this);
		//TODO!!! Dit moet nog met excecution time etc
	}

}
