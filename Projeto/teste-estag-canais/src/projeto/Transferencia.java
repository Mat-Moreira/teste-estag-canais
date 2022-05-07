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

	// M�todo para verificar o tipo da transferencia junto do valor
	private void validarValorTipo(String tipoTransferencia, double valorTransferencia) throws ValorInvalidoException {
		if (tipoTransferencia.equals("PIX") && !(valorTransferencia <= 5000)) {
			// O limite de valor m�ximo permitido para uma transfer�ncia via PIX � de R$ 5
			// mil;
			throw new ValorInvalidoException(
					"O limite de valor m�ximo permitido para uma transfer�ncia via PIX � de R$ 5mil");
		}
		if ((valorTransferencia > 5000 || valorTransferencia <= 10000) && tipoTransferencia.equals("TED")) {
			// Transfer�ncias via TED s� s�o permitidas para valores acima de R$ 5 mil e at�
			// R$ 10 mil;
			throw new ValorInvalidoException(
					"Transfer�ncias via TED s� s�o permitidas para valores acima de R$ 5 mil e at� R$ 10 mil");
		}
		if (tipoTransferencia.equals("DOC") && valorTransferencia < 10000) {
			// Transfer�ncias via DOC s� s�o permitidas para valores acima de R$ 10 mil;
			throw new ValorInvalidoException(
					"Transfer�ncias via DOC s� s�o permitidas para valores acima de R$ 10 mil");
		}
	}

	// N�o ser�o permitidas transfer�ncias para a mesma conta, mas um emissor pode
	// transferir para ele mesmo se for uma conta diferente
	private void validarConta(Correntista emissor, Correntista receptor) throws ValidarContaException {
		if (emissor.nome.equals(receptor.nome) && emissor.conta == receptor.conta) {
			throw new ValidarContaException(
					"N�o ser�o permitidas transfer�ncias para a mesma conta, mas um emissor pode transferir para ele mesmo se for uma conta diferente");
		}
	}
	
	private void validaCorrentista (Correntista correntista) throws ValidaCorrentistaException{
		if(correntista.getNome().equals("")) 
		throw new ValidaCorrentistaException(correntista instanceof Emissor? "Nome do Emissor n�o foi informado." : "Nome do Receptor n�o foi informado. ");
		
		if(correntista.getAgencia() <= 0)
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "Agencia do Emissor n�o foi informada. ": "Agencia do Receptor foi n�o informada. ");
		
		if(correntista.getSaldo() < 0)
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "Saldo do Emissor n�o foi  informada": "Saldo do Receptor n�o foi ser informado" );
		
		if(correntista.getConta() <=0 )
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "Conta do Emissor n�o foi informada": "Conta do Receptor n�o foi informada" );
		
		if(correntista.getCpf().equals("") )
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "CPF do Emissor n�o foi informada": "CPF do Receptor n�o foi informada" );
		
		
		if(correntista.getCpf().length()!=11)
			throw new ValidaCorrentistaException(correntista instanceof Emissor? "CPF do Emissor inv�lido": "CPF do Receptor inv�lido" );
	}
}
