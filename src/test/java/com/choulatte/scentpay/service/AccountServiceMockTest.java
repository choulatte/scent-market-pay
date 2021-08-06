package com.choulatte.scentpay.service;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.AccountStatusType;
import com.choulatte.scentpay.dto.AccountDTO;
import com.choulatte.scentpay.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Account Service Mock Test")
class AccountServiceMockTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @BeforeEach
    public void beforeAll() {
        openMocks(this);
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    @DisplayName(value = "Creating user account generates new account")
    public void creatingUserAccountGeneratesNewAccount() {
        // given
        AccountDTO accountDTO = AccountDTO.builder().userId(1L).build();

        when(accountRepository.save(any(Account.class)))
                .thenReturn(Account.builder().id(1L).userId(1L).balance(0L)
                        .statusType(AccountStatusType.NORMAL).validity(true).build());

        // when
        AccountDTO result = accountService.createAccount(accountDTO);

        // then
        verify(accountRepository, times(1)).save(any());

        assertThat(result.getId() > 0, is(true));
        assertThat(result.getUserId(), is(equalTo(accountDTO.getUserId())));
        assertThat(result.getBalance(), is(0L));
        assertThat(result.getStatusType(), is(AccountStatusType.NORMAL));
    }

    @Test
    @DisplayName(value = "Creating user account without user id throws exception")
    public void creatingUserAccountWithoutUserIdThrowsException() {
        // given
        AccountDTO accountDTO = AccountDTO.builder().build();

        when(accountRepository.save(any(Account.class))).thenThrow(RuntimeException.class);

        // when
        assertThrows(RuntimeException.class, () -> accountService.createAccount(accountDTO));

        // then
        verify(accountRepository, times(1)).save(any());
    }
}