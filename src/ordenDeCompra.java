import java.util.Vector;

public class ordenDeCompra {

	private int codigoOC;
	private String fechaOC;
	private float totalOC = 0;
	private Vector<itemOC> itemsOCs;
	private String nombreProveedor;

	public ordenDeCompra(String proveedor, Vector<Material> materiales) {
		super();

		SistemaIndumentaria sistemaindumentaria = new SistemaIndumentaria();
		this.codigoOC = sistemaindumentaria.getIdsOC().size()+1;
		this.fechaOC = "Hoy";
		this.nombreProveedor = proveedor;
		this.itemsOCs = new Vector<itemOC>();
		
		if(materiales.size() > 0){
			for (int i = 0; i < materiales.size(); i++) {
				Material mat = materiales.elementAt(i);
				itemOC item = new itemOC(mat, mat.getCantidadAPedir());
				itemsOCs.add(item);
				this.totalOC += (float) (mat.getCosto() * item.getCantPedir());
			}
		}
	}

	public int getCodigoOC() {
		return codigoOC;
	}

	public void setCodigoOC(int codigoOC) {
		this.codigoOC = codigoOC;
	}

	public Vector<itemOC> getItemsOCs() {
		return itemsOCs;
	}

	public void setItemsOCs(Vector<itemOC> itemsOCs) {
		this.itemsOCs = itemsOCs;
	}

	public String getFechaOC() {
		return fechaOC;
	}

	public void setFechaOC(String fechaOC) {
		this.fechaOC = fechaOC;
	}

	public float getTotalOC() {
		return totalOC;
	}

	public void setTotalOC(float totalOC) {
		this.totalOC = totalOC;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public void calcularPrecioTotal() {
		float total = 0;
		for (int i = 0; i < itemsOCs.size(); i++) {
			total = total + (itemsOCs.elementAt(i).getCantPedir() * itemsOCs.elementAt(i).getMaterial().getCosto());
		}
		this.totalOC = total;
	}

	public void incorporarItemOC(int cantPedir, Material material) {

		itemOC itemOC = new itemOC(material, cantPedir);
		this.itemsOCs.addElement(itemOC);

	}

	public boolean sosOC(int codigo) {
		return codigo == codigoOC;
	}

}
