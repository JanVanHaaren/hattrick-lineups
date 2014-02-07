package api.wekaclassifier;


public enum ResultClass {
	WIN(0), LOSS(1);
	
	private int index;

	private ResultClass(int index){
		this.index = index;
	}
	
	public int getIndex()
	{
		return this.index;
	}
}


