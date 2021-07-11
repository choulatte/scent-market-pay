package com.choulatte.scentpay.domain;

import com.choulatte.scentpay.dto.HoldingDTO;
import com.choulatte.scentpay.exception.HoldingIllegalStateException;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "holding")
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long id;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account_idx", nullable = false)
    private Account account;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "expired_date", nullable = false)
    private Date expiredDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "recorded_date", nullable = false)
    private Date recordedDate;

    @Setter
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", nullable = false)
    private Date lastModifiedDate;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private HoldingStatusType statusType;

    public HoldingDTO toDTO() {
        return HoldingDTO.builder().id(this.id)
                .accountId(this.account.getId())
                .amount(this.amount)
                .balance(this.balance)
                .expiredDate(this.expiredDate)
                .recordedDate(this.recordedDate)
                .lastModifiedDate(this.lastModifiedDate)
                .statusType(this.statusType).build();
    }

    public Holding updateStatus(HoldingStatusType holdingStatusType) {
        if (this.statusType == HoldingStatusType.CLOSED) throw new HoldingIllegalStateException();

        this.statusType = holdingStatusType;
        this.lastModifiedDate = new Date();

        return this;
    }
}
