package ru.otus.bank.service.impladded;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.bank.dao.AccountDao;
import ru.otus.bank.entity.Account;
import ru.otus.bank.entity.Agreement;
import ru.otus.bank.service.impl.AccountServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplAddTest {

    @Mock
    private AccountDao dao;

    AccountServiceImpl accountService;

    @BeforeEach
    public void init() {
        accountService = new AccountServiceImpl(dao);
    }

    @Test
    public void testCharge() {
        Account account = new Account();
        account.setId(1L);
        account.setAmount(new BigDecimal(100));

        when(dao.findById(eq(1L))).thenReturn(Optional.of(account));

        accountService.charge(1L, new BigDecimal(10));

        assertEquals(new BigDecimal(90), account.getAmount());

    }

    @Test
    public void testAddAccount() {
        Agreement agreement = new Agreement();
        String accountNumber = "10";
        Integer accountType = 0;
        BigDecimal amount = new BigDecimal(100);

        Account account01 = new Account();
        account01.setId(1L);
        account01.setAmount(amount);

        ArgumentMatcher<Account> matcher = argument ->
                        argument.getType().equals(accountType) &&
                        argument.getAmount().equals(amount);


        when(dao.save(any())).thenReturn(account01);
        accountService.addAccount(agreement, accountNumber, accountType, amount);

        verify(dao).save(argThat(matcher));
    }


    @Test
    public void testAddAccountWithCaptor() {
        String name = "test";
        Agreement agreement = new Agreement();
        agreement.setId(10L);
        agreement.setName(name);
        BigDecimal amount = new BigDecimal(100);
        String accountNumber = "10";
        Integer accountType = 0;


        Account account01 = new Account();
        account01.setNumber(accountNumber);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        when(dao.save(captor.capture())).thenReturn(account01);

        Account account02= accountService.addAccount(agreement, accountNumber, accountType, amount);

        Assertions.assertEquals(account01.getNumber(), account02.getNumber());
    }


}
