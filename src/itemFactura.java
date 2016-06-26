
public class itemFactura 
{
	Prenda prenda;
	int cantidadComprada;
	
	public itemFactura(int cantidadComprada, Prenda prenda) {
		super();
		this.prenda = prenda;
		this.cantidadComprada = cantidadComprada;
	}

	public Prenda getPrenda() {
		return prenda;
	}

	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}

	public int getCantidadComprada() {
		return cantidadComprada;
	}

	public void setCantidadComprada(int cantidadComprada) {
		this.cantidadComprada = cantidadComprada;
	}
	
	
	
}
