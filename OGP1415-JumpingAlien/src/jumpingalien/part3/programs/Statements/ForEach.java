package jumpingalien.part3.programs.Statements;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.Expressions.Expression;

public class ForEach extends ComposedStatement{

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
	
	public Object[] getObjectArray(){
		return this.objects;
	}
	
	private Object[] objects;
	
	public void setObjectArray(Object[] objects){
		this.objects = objects;
	}
	
	public Kind getKind(){
		return this.kind;
	}
	
	private Kind kind;
	
	public void setKind(Kind kind){
		this.kind = kind;
	}

	@Override
	public void execute(Program program) {
		Object[] array = objects;
		for (int i = 1; i < objects.length;i +=1){
				objects[i].execute(program);
				//find a way 
			}
	}
	
	public List<Object> makeObjectArray(Program program){
		List<Object> list = new ArrayList<Object>();
		Kind kind = getKind();
		if (kind == Kind.MAZUB){
			list.add(program.getPossessedObject().getWorld().getMazub());
		}else if (kind == Kind.BUZAM){
			list.add(program.getPossessedObject().getWorld().getBuzam());
		}else if (kind == Kind.SLIME){
			list.add(program.getPossessedObject().getWorld().getSlimes());
		}else if (kind == Kind.SHARK){
			list.add(program.getPossessedObject().getWorld().getSharks());
		}else if (kind == Kind.PLANT){
			list.add(program.getPossessedObject().getWorld().getPlants());
		}else if (kind == Kind.TERRAIN){
			list.add(program.getPossessedObject().getWorld().getTiles());
		}else{
			list.add(program.getPossessedObject().getWorld().getMazub());
			list.add(program.getPossessedObject().getWorld().getSlimes());
			list.add(program.getPossessedObject().getWorld().getSharks());
			list.add(program.getPossessedObject().getWorld().getPlants());
			list.add(program.getPossessedObject().getWorld().getTiles());
		}
		return list;
	}
	
	public void sortList(Program program,Expression<Boolean> whereClause){
		List<Object> sortedList = new ArrayList<Object>();
		List<Object> listToSort = makeObjectArray(program);
		for(Object x : listToSort){
			if (whereClause.evaluate()){
				sortedList.add(x);
			}
		}
	}

}
