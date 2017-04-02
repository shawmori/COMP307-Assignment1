import java.util.*;

public class Node {
	
	List<String> outcomes;
	String attribute;
	Map<String, List<Boolean>> values;
	
	public Node(List<String> outcomes, String att, Map<String, List<Boolean>> values){
		this.outcomes = outcomes;
		this.attribute = att;
		this.values = values;
	}
	
	public void print(){
		for(String s : values.keySet()){
			System.out.println(s + " " + values.get(s));
		}
	}
}
