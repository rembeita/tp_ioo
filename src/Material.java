
public class Material 
{
	private int codigoMaterial;
	private String nombreMaterial;
	private int cantStock;
	private Proveedor proveedor;
	private float costo;
	private int cantidadAPedir;
	private int puntoDeReposicion;
	
		
	public Material(int codigoMaterial, String nombreMaterial, int cantStock, Proveedor proveedor, float costo,
			int cantidadAPedir, int puntoDeReposicion) 
	{
		super();
		this.codigoMaterial = codigoMaterial;
		this.nombreMaterial = nombreMaterial;
		this.cantStock = cantStock;
		this.proveedor = proveedor;
		this.costo = costo;
		this.cantidadAPedir = cantidadAPedir;
		this.puntoDeReposicion = puntoDeReposicion;
	}
	public int getCodigoMaterial() {
		return codigoMaterial;
	}
	public void setCodigoMaterial(int codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	public String getNombreMaterial() {
		return nombreMaterial;
	}
	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}
	public int getCantStock() {
		return cantStock;
	}
	public void setCantStock(int cantStock) {
		this.cantStock = cantStock;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public int getCantidadAPedir() {
		return cantidadAPedir;
	}
	public void setCantidadAPedir(int cantidadAPedir) {
		this.cantidadAPedir = cantidadAPedir;
	}
	public int getPuntoDeReposicion() {
		return puntoDeReposicion;
	}
	public void setPuntoDeReposicion(int puntoDeReposicion) {
		this.puntoDeReposicion = puntoDeReposicion;
	}

	public boolean sosMaterial(int cod)
	{
		return (codigoMaterial==cod);
	}
	
	public boolean reducirStock(int cantidad) 
	{
		if (cantidad < this.cantStock)
		{
			this.cantStock = cantStock-cantidad;
		}
		else
		{
			System.out.println("No hay cantidad suficiente");
			return false;
		}
		return true;
	}
	
	public void imprimirCantStock() {
		System.out.println("Queda disponible la siguiente cantidad: " + this.cantStock);
	}
	
	
}
