
public class Proveedor 
{
	private String nombreProveedor;
	private int codigoProveedor;
		
	public Proveedor(String nombreProveedor, int codigoProveedor) 
	{
		super();
		this.nombreProveedor = nombreProveedor;
		this.codigoProveedor = codigoProveedor;
	}
	
	public String getNombreProveedor() 
	{
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) 
	{
		this.nombreProveedor = nombreProveedor;
	}
	public int getCodigoProveedor() 
	{
		return codigoProveedor;
	}
	public void setCodigoProveedor(int codigoProveedor) 
	{
		this.codigoProveedor = codigoProveedor;
	}
	public boolean sosProveedor(int cod)
	{
		return (codigoProveedor==cod);
	}
	
}
