package com.choulatte.scentpay.domain;

import com.choulatte.scentpay.dto.TransactionDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long id;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account_idx", nullable = false)
    private Account account;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Transaction.Type type;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Column(name = "label", nullable = false)
    private String label;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "recorded_date", nullable = false)
    private Date recordedDate;

    public TransactionDTO toDTO() {
        return TransactionDTO.builder().id(this.id)
                .accountId(this.account.getId())
                .type(this.type)
                .amount(this.amount)
                .balance(this.balance)
                .label(this.label)
                .recordedDate(this.recordedDate).build();
    }

    public enum Type {
        DEPOSIT,
        WITHDRAWAL
    }
}
