import java.util.Vector;

public class Prenda 
{
	protected int codigoPrenda;
	protected String nombrePrenda;
	protected int stockPrenda;
	protected float precioPrenda;
	protected Vector<ItemPrenda>itemsprendas;	

	public Prenda(int codigoPrenda, String nombrePrenda, int stockPrenda) {	
		super();
		
		itemsprendas = new Vector<ItemPrenda>();

		this.codigoPrenda = codigoPrenda;
		this.nombrePrenda = nombrePrenda;
		this.stockPrenda = stockPrenda;
		
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
	
	public void agregarItemPrenda(int cantMaterial, Material material){	
		//Chequeo si existe el itemPrenda y lo actualizo, de lo contrario lo creo y agrego.
		if(itemsprendas.size()>0){
			for(int i = 0; i<itemsprendas.size();i++){
			
				if(itemsprendas.elementAt(i).getMaterial().getCodigoMaterial() == material.getCodigoMaterial()){
					
					itemsprendas.elementAt(i).setCantidad(cantMaterial);					
					break;
				} else if(i+1 == itemsprendas.size()){
					ItemPrenda itemPrenda = new ItemPrenda(cantMaterial, material);
					this.itemsprendas.add(itemPrenda);
				}
			}
		} else {
			ItemPrenda itemPrenda = new ItemPrenda(cantMaterial, material);
			this.itemsprendas.add(itemPrenda);
		}
					
		this.calcularPrecio();
	}
	
	//Nuevo metodo derivado de la correccion de la profesora
	public void baja(){
		this.codigoPrenda = this.getCodigoPrenda()*-1;
				
	}
		
}
