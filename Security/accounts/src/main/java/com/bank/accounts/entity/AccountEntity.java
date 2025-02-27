package com.bank.accounts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "accounts")
@Getter
@Setter
public class AccountEntity  extends  BaseEntity {

    @Id
    private Long accountNumber;

    @Column
    private Long customerId;

    @Column
    private String accountType;

    @Column
    private String branchAddress;

}
