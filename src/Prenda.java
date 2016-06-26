import java.util.Vector;

public class Prenda 
{
	protected int codigoPrenda;
	protected String nombrePrenda;
	protected int stockPrenda;
	protected float precioPrenda;
	protected Vector<ItemPrenda>itemsprendas;
	

	public Prenda(int codigoPrenda, String nombrePrenda, int stockPrenda, 
			Material material, int cantMaterial) {
		super();
		
		itemsprendas = new Vector<ItemPrenda>();
		ItemPrenda itemprenda = new ItemPrenda(cantMaterial, material);

		this.codigoPrenda = codigoPrenda;
		this.nombrePrenda = nombrePrenda;
		this.stockPrenda = stockPrenda;
		itemsprendas.add(itemprenda);
		
	}
	
	public String getNombrePrenda() 
	{
		return nombrePrenda;
	}
	public void setNombrePrenda(String nombrePrenda) 
	{
		this.nombrePrenda = nombrePrenda;
	}
	public int getStockPrenda() 
	{
		return stockPrenda;
	}
	public void setStockPrenda(int stockPrenda) 
	{
		this.stockPrenda = stockPrenda;
	}
	
	public boolean sosPrenda(int cod)
	{
		return (codigoPrenda==cod);
	}
	
	public int getCodigoPrenda() {
		return codigoPrenda;
	}
	
	public void setCodigoPrenda(int codigoPrenda) 
	{
		this.codigoPrenda = codigoPrenda;
	}

	public Vector<ItemPrenda> getItemsprendas() {
		return itemsprendas;
	}

	public void incorporarItemPrenda(ItemPrenda itemprenda) {
		this.itemsprendas.add(itemprenda);
	}
	
	public float getPrecioPrenda() {
		return precioPrenda;
	}


	public void setPrecioPrenda(float precioPrenda) {
		this.precioPrenda = precioPrenda;
	}


	public void calcularPrecio()
	{
		float total=0;
		for (int i=0; i<itemsprendas.size();i++)
		{
			total = total + (itemsprendas.elementAt(i).getCantidad() * itemsprendas.elementAt(i).getMaterial().getCosto());
		}
		System.out.println("El valor es: " + total);
		this.precioPrenda = total;
	
	}
	public void agregarItemPrenda(int cantMaterial, Material material)
	{
		ItemPrenda itemprenda = new ItemPrenda(cantMaterial, material);	
		this.itemsprendas.add(itemprenda);
		this.calcularPrecio();
	}
	
	//Nuevo metodo derivado de la correccion de la profesora
	public void baja(){
		this.codigoPrenda = this.getCodigoPrenda()*-1;
				
	}
	
	
}
