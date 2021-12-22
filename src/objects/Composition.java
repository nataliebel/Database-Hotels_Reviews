package objects;

public class Composition {
	private int keyCompsition;
	private String composition;
	
	public Composition(int keyCompsition, String composition) {
		this.keyCompsition = keyCompsition;
		this.composition = composition;
	}
	public int getKeyCompsition() {
		return keyCompsition;
	}
	public void setKeyCompsition(int keyCompsition) {
		this.keyCompsition = keyCompsition;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}

}
