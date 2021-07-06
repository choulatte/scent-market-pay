package com.choulatte.scentpay.domain;

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
    private AccountStatusType statusType;

    @Setter
    @Column(name = "validation", nullable = false)
    private Boolean isValid;

    @OneToMany(mappedBy = "account")
    private List<Holding> holdingList;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList;

}
