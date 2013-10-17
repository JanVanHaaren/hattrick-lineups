package api.weka;

public class AttributeAndType {
	
	private String name;
	private String type;
	
	
	
	public AttributeAndType(String name, String type) {
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
}
