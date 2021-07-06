package com.choulatte.scentpay.domain;

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
    @Column(name = "recorded_date", nullable = false)
    private Date recordedDate;

    @Setter
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", nullable = false)
    private Date lastModifiedDate;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private HoldingStatusType statusType;
}
