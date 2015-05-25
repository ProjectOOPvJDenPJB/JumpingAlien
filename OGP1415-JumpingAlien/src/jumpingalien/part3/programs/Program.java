package jumpingalien.part3.programs;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.Statements.Statement;

public class Program {
	
	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
		setVariableValues(globalVariables);
	}
	
	private Statement getMainStatement() {
		return mainStatement;
	}
	
	private final Statement mainStatement;
	
	public HashMap <String, Type> getGlobalVariables() {
		HashMap<String,Type> globalVariablesClone = new HashMap<String, Type>(globalVariables);
		return globalVariablesClone;
	}
	
	private final Map<String, Type> globalVariables;
	
	public HashMap<String, Object> getVariableValues() {
		HashMap<String, Object> variableValuesClone = new HashMap<String, Object>(variableValues);
		return variableValuesClone;
	}
	
	private HashMap<String, Object> variableValues;
	
	protected void setVariableValues(Map<String, Type> map) {
		this.variableValues = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			if (map.get(key) == Type.DOUBLE) {
				this.variableValues.put(key, 0.0);
			}
			else if (map.get(key) == Type.BOOLEAN) {
				this.variableValues.put(key, false);
			}
			else if (map.get(key) == Type.OBJECT) {
				this.variableValues.put(key, null);
			}
			else if (map.get(key) == Type.DIRECTION) {
				this.variableValues.put(key, Direction.RIGHT);
			}
		}
	}
	
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
	}

}
