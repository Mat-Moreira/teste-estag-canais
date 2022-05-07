package projeto;

public class Receptor extends Correntista {

	@Override
	public String toString() {
		return "Saldo do Receptor: R$ " +String.valueOf(super.getSaldo());
	}
	

}
