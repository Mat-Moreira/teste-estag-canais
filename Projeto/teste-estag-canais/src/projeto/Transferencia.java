package projeto;

import projeto.exceptions.ValidaCorrentistaException;
import projeto.exceptions.ValidarContaException;
import projeto.exceptions.ValorInvalidoException;

public class Transferencia {

	public void transferir(Correntista emissor, Correntista receptor, String tipoTransferencia,
			double valorTransferencia) throws ValorInvalidoException, ValidarContaException, ValidaCorrentistaException {

		try {
			validarValorTipo(tipoTransferencia, valorTransferencia);
			validaCorrentista(receptor);
			validaCorrentista(emissor);
			validarConta(emissor, receptor);
			emissor.setSaldo(valorTransferencia);
			receptor.setSaldo(valorTransferencia * -1);
		} catch (ValorInvalidoException e) {
			throw new ValorInvalidoException(e.getMessage());
		} catch (ValidarContaException e) {
			throw new ValidarContaException(e.getMessage());
		}catch(ValidaCorrentistaException e) {
			throw new ValidaCorrentistaException(e.getMessage());
			}

	}

	// Método para verificar o tipo da transferencia junto do valor
	private void validarValorTipo(String tipoTransferencia, double valorTransferencia) throws ValorInvalidoException {
		if (tipoTransferencia.equals("PIX") && !(valorTransferencia <= 5000)) {
			// O limite de valor máximo permitido para uma transferência via PIX é de R$ 5
			// mil;
			throw new ValorInvalidoException(
					"O limite de valor máximo permitido para uma transferência via PIX é de R$ 5mil");
		}
		if ((valorTransferencia > 5000 || valorTransferencia <= 10000) && tipoTransferencia.equals("TED")) {
			// Transferências via TED só são permitidas para valores acima de R$ 5 mil e até
			// R$ 10 mil;
			throw new ValorInvalidoException(
					"Transferências via TED só são permitidas para valores acima de R$ 5 mil e até R$ 10 mil");
		}
		if (tipoTransferencia.equals("DOC") && valorTransferencia < 10000) {
			// Transferências via DOC só são permitidas para valores acima de R$ 10 mil;
			throw new ValorInvalidoException(
					"Transferências via DOC só são permitidas para valores acima de R$ 10 mil");
		}
	}

	// Não serão permitidas transferências para a mesma conta, mas um emissor pode
	// transferir para ele mesmo se for uma conta diferente
	private void validarConta(Correntista emissor, Correntista receptor) throws ValidarContaException {
		if (emissor.nome.equals(receptor.nome) && emissor.conta == receptor.conta) {
			throw new ValidarContaException(
					"Não serão permitidas transferências para a mesma conta, mas um emissor pode transferir para ele mesmo se for uma conta diferente");
		}
	}
	
	private void validaCorrentista (Correntista correntista) throws ValidaCorrentistaException{
		if(correntista.getNome().equals("")) 
		throw new ValidaCorrentistaException(correntista instanceof Emissor? "Nome do Emissor não foi informado." : "Nome do Receptor não foi informado. ");
		
		if(correntista.getAgencia() <= 0)
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "Agencia do Emissor não foi informada. ": "Agencia do Receptor foi não informada. ");
		
		if(correntista.getSaldo() < 0)
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "Saldo do Emissor não foi  informada": "Saldo do Receptor não foi ser informado" );
		
		if(correntista.getConta() <=0 )
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "Conta do Emissor não foi informada": "Conta do Receptor não foi informada" );
		
		if(correntista.getCpf().equals("") )
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "CPF do Emissor não foi informada": "CPF do Receptor não foi informada" );
		
		
		if(correntista.getCpf().length()!=11)
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "CPF do Emissor inválido": "CPF do Receptor inválido" );
	}
}
