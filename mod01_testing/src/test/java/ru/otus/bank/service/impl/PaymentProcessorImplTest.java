package ru.otus.bank.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.bank.entity.Account;
import ru.otus.bank.entity.Agreement;
import ru.otus.bank.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentProcessorImplTest {

    @Mock
    AccountService accountService;

    @Spy
    @InjectMocks
    PaymentProcessorImpl paymentProcessor;

    @BeforeEach
    public void init() {
        paymentProcessor = new PaymentProcessorImpl(accountService);
    }


    @Test
    public void testTransfer() {
        Agreement sourceAgreement = new Agreement();
        sourceAgreement.setId(1L);

        Agreement destinationAgreement = new Agreement();
        destinationAgreement.setId(2L);

        Account sourceAccount = new Account();
        sourceAccount.setAmount(BigDecimal.TEN);
        sourceAccount.setType(0);

        Account destinationAccount = new Account();
        destinationAccount.setAmount(BigDecimal.ZERO);
        destinationAccount.setType(0);

        ArgumentMatcher<Agreement> m1 = argument -> argument != null && argument.getId() == 1L;
        ArgumentMatcher<Agreement> m2 = argument -> argument != null && argument.getId() == 2L;


        when(accountService.getAccounts(argThat(m1))).thenReturn(List.of(sourceAccount));
        when(accountService.getAccounts(argThat(m2))).thenReturn(List.of(destinationAccount));


        paymentProcessor.makeTransfer(sourceAgreement, destinationAgreement,
                0, 0, BigDecimal.ONE);


    }


    @Test
    public void testTransferWithComission() {
        Agreement sourceAgreement = new Agreement();
        sourceAgreement.setId(1L);

        Agreement destinationAgreement = new Agreement();
        destinationAgreement.setId(2L);

        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));
        sourceAccount.setType(0);

        Account destinationAccount = new Account();
        destinationAccount.setAmount(BigDecimal.ZERO);
        destinationAccount.setType(0);

        ArgumentMatcher<Agreement> m1 = argument -> argument != null && argument.getId() == 1L;
        ArgumentMatcher<Agreement> m2 = argument -> argument != null && argument.getId() == 2L;

        when(accountService.getAccounts(argThat(m1))).thenReturn(List.of(sourceAccount));
        when(accountService.getAccounts(argThat(m2))).thenReturn(List.of(destinationAccount));


        paymentProcessor.makeTransferWithComission(sourceAgreement, destinationAgreement,
                0, 0, BigDecimal.TEN, new BigDecimal("0.10"));

    }


}
