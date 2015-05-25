package jumpingalien.part3.programs.Statements;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.Expressions.Expression;

public class ForEach extends ComposedStatement{
	
	public ForEach(String variableName,
			Kind variableKind,
			Expression<Boolean> where,
			Expression<Double> sort,
			SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		this.kind = variableKind;
		this.where = where;
		this.sort = sort;
		this.sortDirection = sortDirection;
		this.body = body;
	}

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
		
	public Kind getKind(){
		return this.kind;
	}
	
	private final Kind kind;
	
	public Expression<Boolean> getWhereExpression() {
		return this.where;
	}
	
	private final Expression<Boolean> where;
	
	public Expression<Double> getSortExpression() {
		return this.sort;
	}
	
	private final Expression<Double> sort;
	
	public SortDirection getSortDirection() {
		return this.sortDirection;
	}
	
	private final SortDirection sortDirection;
	
	public Statement getBody() {
		return this.body;
	}
	
	private final Statement body;

	@Override
	public void execute(Program program) {
		Collection<GameObject> objects = makeObjectArray(program);
		if (where != null) {
			objects = filterList(program, objects);
		}
		if ((sort != null) && (isValidSortKind(getKind()))) {
			objects = sortList(program, objects);
		}
		for (GameObject gameObject : objects) {
			body.execute(gameObject.getProgram());
		}
	}
	
	private boolean isValidSortKind(Kind kind) {
		return !((kind == Kind.ANY) || (kind == Kind.TERRAIN) || (kind == Kind.MAZUB));
	}
	
	public Collection<GameObject> makeObjectArray(Program program){
		Collection<GameObject> list = new ArrayList<GameObject>();
		Kind kind = getKind();
		if (kind == Kind.MAZUB){
			list.add(program.getPossessedObject().getWorld().getMazub());
		}else if (kind == Kind.BUZAM){
			list.add(program.getPossessedObject().getWorld().getBuzam());
		}else if (kind == Kind.SLIME){
			list.addAll(program.getPossessedObject().getWorld().getSlimes());
		}else if (kind == Kind.SHARK){
			list.addAll(program.getPossessedObject().getWorld().getSharks());
		}else if (kind == Kind.PLANT){
			list.addAll(program.getPossessedObject().getWorld().getPlants());
		}else if (kind == Kind.TERRAIN){
			list.addAll(program.getPossessedObject().getWorld().getTiles());
		}else{
			list.addAll(program.getPossessedObject().getWorld().getAllObjects());
		}
		return list;
	}
	
	public Collection<GameObject> filterList(Program program, Collection<GameObject> objects){
		Collection<GameObject> filteredList = new ArrayList<GameObject>();
		for(GameObject gameObject : objects){
			if (getWhereExpression().evaluate(program)){
				filteredList.add(gameObject);
			}
		}
		return filteredList;
	}
	
	public Collection<GameObject> sortList(Program program, Collection<GameObject> objects){
		TreeMap<Double, GameObject> sortedList = new TreeMap<Double, GameObject>();
		for(GameObject gameObject : objects){
			Double sortDouble = getSortExpression().evaluate(gameObject.getProgram());
			sortedList.put(sortDouble, gameObject);
		}
		if (getSortDirection() == SortDirection.ASCENDING)
			return sortedList.values();
		else if (getSortDirection() == SortDirection.DESCENDING) {
			List<GameObject> sortedValues = new ArrayList<GameObject>(sortedList.values());
			java.util.Collections.reverse(sortedValues);
			return sortedValues;
		}
		else {
			return objects;
		}
	}

}
