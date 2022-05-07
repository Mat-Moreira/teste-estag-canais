package projeto;

public class Emissor extends Correntista {
	
	@Override
	public String toString() {
		return "Saldo do Emissor: R$ " +String.valueOf(super.getSaldo());
	}
	

}
