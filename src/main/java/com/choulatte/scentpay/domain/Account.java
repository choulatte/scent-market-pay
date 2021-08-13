package com.choulatte.scentpay.domain;

import com.choulatte.scentpay.dto.AccountDTO;
import com.choulatte.scentpay.exception.AccountIllegalStateException;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long id;

    @Column(name = "user_idx", nullable = false)
    private Long userId;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "registered_date", nullable = false)
    private Date registeredDate;

    @Setter
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", nullable = false)
    private Date lastModifiedDate;

    @Setter
    @Column(name = "status_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusType statusType;

    @Setter
    @Column(name = "validity", nullable = false)
    private Boolean validity;

    @OneToMany(mappedBy = "account")
    private List<Holding> holdingList;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList;

    public AccountDTO toDTO() {
        return AccountDTO.builder().id(this.id)
                .userId(this.userId)
                .balance(this.balance)
                .registeredDate(this.registeredDate)
                .lastModifiedDate(this.lastModifiedDate)
                .statusType(this.statusType)
                .validity(this.validity).build();
    }

    public Account updateInfo(AccountDTO accountDTO) {
        this.statusType = accountDTO.getStatusType();
        this.lastModifiedDate = new Date();

        return this;
    }

    public Account applyTransaction(Transaction transaction) {
        if (this.statusType == Account.StatusType.FREEZING || !this.getValidity()) throw new AccountIllegalStateException();

        switch (transaction.getType()) {
            case DEPOSIT:
                this.balance -= transaction.getAmount();
                break;
            case WITHDRAWAL:
                this.balance += transaction.getAmount();
        }

        this.lastModifiedDate = new Date();

        return this;
    }

    public enum StatusType {
        NORMAL,
        PENDING,
        FREEZING
    }
}
