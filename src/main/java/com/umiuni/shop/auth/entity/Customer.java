package com.umiuni.shop.auth.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@DiscriminatorValue("CUSTOMER")
public class Customer extends User{

    private String customerName;

    private String contactInfo;

    private String paypalEmail;

    private String paypalName;

    private String paypalAccessToken;

    private BigDecimal balance;
}
