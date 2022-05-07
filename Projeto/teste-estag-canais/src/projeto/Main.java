package projeto;

import java.util.Scanner;

import projeto.exceptions.ValidaCorrentistaException;
import projeto.exceptions.ValidarContaException;
import projeto.exceptions.ValorInvalidoException;

public class Main {

	// Classe para iniciar o programa
	public static void main(String[] args) {

		int id_transferencia;
		String tipo_transferencia;
		double valor_transferencia;

		Scanner teclado = new Scanner(System.in);

		Emissor emissor = new Emissor();
		//System.out.println("Qual o seu nome: ");
		//emissor.nome = teclado.next();
		emissor.setNome("Joao");
		//System.out.println("Qual a sua agência?: ");
		//emissor.agencia= teclado.nextInt();
		emissor.setAgencia(001);
		//System.out.println("Qual a sua conta?: ");
		//emissor.agencia= teclado.nextInt();
		emissor.setConta(1234);
		//System.out.println("Qual o seu CPF?: ");3
		//emissor.cpf= teclado.next();
		emissor.setCpf("12312312312");

		Receptor receptor = new Receptor();

		//System.out.println("Qual o nome do receptor?: ");
		//receptor.nome = teclado.next();
		receptor.setNome("Maria");
		//System.out.println("Qual a agência do receptor?: ");
		//receptor.agencia=teclado.nextInt();
		receptor.setAgencia(002);
		//System.out.println("Qual a conta do receptor?: ");
		//receptor.conta=teclado.nextInt();
		receptor.setConta(1335);
		//System.out.println("Qual o CPF do receptor?: ");
		//receptor.cpf=teclado.next();
		receptor.setCpf("12312312312");

		//System.out.println("Qual é o ID da transferência: ");
		//id_transferencia= teclado.nextInt();
		id_transferencia = 1;
		//System.out.println("Qual será o valor da transferência?: ");
		//valor_transferencia = teclado.nextDouble();
		valor_transferencia = 500 ;
		//System.out.println("\nQual será o modo da tranferência?:");
		//tipo_transferencia = teclado.next();;
		tipo_transferencia = "PIX";
		
		
		Transferencia teste = new Transferencia();
		try {
			teste.transferir(emissor, receptor, tipo_transferencia, valor_transferencia);
			System.out.println("Sua transferência foi realizada com sucesso!");
			System.out.println(emissor.toString());
			System.out.println(receptor.toString());
		} catch (ValorInvalidoException | ValidarContaException | ValidaCorrentistaException e ) {

			System.out.println("\nSua transferência não foi completada pois, " + e.getMessage());

		}
		
		
	}

}
