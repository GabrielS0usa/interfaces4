package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.println("Entre os dados do contrato: ");
			System.out.print("Numero: ");
			int number = sc.nextInt();
			sc.nextLine();
			System.out.print("Data (dd/MM/yyyy): ");
			LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
			System.out.print("Valor do contrato: ");
			double amount = sc.nextDouble();
			
			Contract contract = new Contract(number, date, amount);
			
			System.out.print("Entre com o numero de parcelas: ");
			Integer months = sc.nextInt();
			
			System.out.println();
			
			ContractService contractService = new ContractService(new PaypalService());
			
			contractService.processContract(contract, months);
			
			System.out.println("Parcelas:");
			for (Installment installment : contract.getInstallments()) {
				System.out.println(installment);
			}
			
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}finally {
			sc.close();
		}		
	}
}
