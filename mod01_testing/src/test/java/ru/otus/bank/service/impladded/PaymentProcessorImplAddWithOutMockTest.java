package ru.otus.bank.service.impladded;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.bank.dao.AccountDao;
import ru.otus.bank.dao.AgreementDao;
import ru.otus.bank.entity.Account;
import ru.otus.bank.entity.Agreement;
import ru.otus.bank.service.AccountService;
import ru.otus.bank.service.AgreementService;
import ru.otus.bank.service.PaymentProcessor;
import ru.otus.bank.service.impl.AccountServiceImpl;
import ru.otus.bank.service.impl.AgreementServiceImpl;
import ru.otus.bank.service.impl.PaymentProcessorImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentProcessorImplAddWithOutMockTest {

    AgreementService agreementService;
    AccountService accountService;
    PaymentProcessor paymentProcessor;
    AccountDao accountDao;
    AgreementDao agreementDao;

    @BeforeEach
    void init() {
        accountDao = new AccountDao();
        agreementDao = new AgreementDao();
        agreementService = new AgreementServiceImpl(agreementDao);
        accountService = new AccountServiceImpl(accountDao);
        paymentProcessor = new PaymentProcessorImpl(accountService);

        Agreement clientAgreement1 = agreementService.addAgreement("Client1");
        Agreement clientAgreement2 = agreementService.addAgreement("Client2");
        Agreement bankAgreement = agreementService.addAgreement("Bank");

        Account client1Account1 = accountService.addAccount(clientAgreement1,
                clientAgreement1.getName() + "_acc1", 0, new BigDecimal(1000));

        Account client2Account1 = accountService.addAccount(clientAgreement2,
                clientAgreement2.getName() + "_acc1", 0, new BigDecimal(1000));
        Account bankAccount1 = accountService.addAccount(bankAgreement,
                bankAgreement.getName() + "_acc1", 0, new BigDecimal(1000000));
    }

    @Test
    public void testTransfer() {
        Agreement bankAgreement = agreementService.findByName("Bank").get();
        Agreement clientAgreement = agreementService.findByName("Client2").get();

        Account bankAccount = accountService.getAccounts(bankAgreement).get(0);
        Account clientAccount = accountService.getAccounts(clientAgreement).get(0);

        accountService.makeTransfer(bankAccount.getId(), clientAccount.getId(), BigDecimal.TEN);
        assertEquals(clientAccount.getAmount(),new BigDecimal(1010));
        assertEquals(bankAccount.getAmount(),(new BigDecimal(1000000)).subtract(new BigDecimal(10)));

    }


    @Test
    public void testTransferWithComission() {
        Agreement bankAgreement = agreementService.findByName("Bank").get();
        Agreement clientAgreement = agreementService.findByName("Client1").get();

        Account bankAccount = accountService.getAccounts(bankAgreement).get(0);
        Account clientAccount = accountService.getAccounts(clientAgreement).get(0);

        paymentProcessor.makeTransferWithComission(bankAgreement, clientAgreement, 0, 0,
                new BigDecimal(100), new BigDecimal("1"));
        assertEquals(clientAccount.getAmount(),new BigDecimal(1100));

        assertEquals(bankAccount.getAmount(), new BigDecimal(1000000 - 100 * 2));

    }


}
