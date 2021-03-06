public class prendaDeTemporada extends Prenda 
{
	private String estacion;
	private float porcentajeIncremento;
		
	public prendaDeTemporada(int codigoPrenda, String nombrePrenda, int stockPrenda, String epoca) {
		super(codigoPrenda, nombrePrenda, stockPrenda);
		this.estacion = epoca;
		
		if (estacion == "Verano")
		{
			this.porcentajeIncremento = 20;
		}
		else if (estacion == "Invierno")
		{
			this.porcentajeIncremento = 15;
		}
		else if (estacion == "Oto�o")
		{
			this.porcentajeIncremento = 10;
		}
		else if (estacion == "Primavera")
		{
			this.porcentajeIncremento = 5;
		}
		
	}

	public void calcularPrecio()
	{
		float total=0;
		for (int i=0; i<itemsprendas.size();i++)
		{
			total = total + (itemsprendas.elementAt(i).getCantidad() * itemsprendas.elementAt(i).getMaterial().getCosto());
			total = ((total * this.porcentajeIncremento)/100)+total;
		}
		System.out.println("El valor es: " + total);
		this.precioPrenda = total;
	
	}
}