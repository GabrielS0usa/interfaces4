package model.service;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {

		for (int i = 1; i <= months; i++) {
			
			double amount = contract.getTotalValue() / months;
			
			double result = onlinePaymentService.interest(amount, i);
			result = onlinePaymentService.paymentFee(result);
			
			LocalDate date = contract.getDate();
			
			date = date.plusMonths(i);
			
			contract.addInstallment(new Installment(date, result));
			
		}

	}

}
