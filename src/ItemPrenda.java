
public class ItemPrenda 
{
	private int cantidad;
	private Material material;
	
	public ItemPrenda(int cantidad, Material material) 
	{
		super();
		this.cantidad = cantidad;
		this.material = material;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
	
}
