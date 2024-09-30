package com.huydq.clothing_ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_oman")
public class Users extends TimeAuditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userCode;
    private String password;
    private String email;
    private String image;
    private String phoneNumber;
    private String fullName;
    private Role role;
    private Integer status;
    private Double totalInvoiceValue;
    private Long totalInvoice;
    private Integer userType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favourite_id")
    private Favourite favourite;
}
