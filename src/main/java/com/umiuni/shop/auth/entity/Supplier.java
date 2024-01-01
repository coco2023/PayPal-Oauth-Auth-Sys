package com.umiuni.shop.auth.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@DiscriminatorValue("SUPPLIER")
public class Supplier extends User{

    private String supplierName;

    private String contactInfo;

    private String paypalEmail; // PayPal account email

    private String paypalName; // PayPal account email

    private String paypalAccessToken; // Store PayPal access token

    private String paypalClientId;

    private String paypalClientSecret; // Ensure this is stored securely

    private String paypalRedirectUri;

    private BigDecimal balance;

}
