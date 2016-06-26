
public class itemOC {

	private Material material;
	private int cantPedir;

	public itemOC(Material material, int cantPedir) {
		super();
		this.material = material;
		this.cantPedir = cantPedir;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getCantPedir() {
		return cantPedir;
	}

	public void setCantPedir(int cantPedir) {
		this.cantPedir = cantPedir;
	}	
	
}
