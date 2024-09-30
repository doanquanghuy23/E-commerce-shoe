package com.huydq.clothing_ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO extends TimeAuditableDTO implements Serializable {
    private Long id;
    private String billCode;
    private String note;
    private Long tatolProduct;
    private Double totalMoney;
    private String phoneNumber;
    private String addressDetail;
    private String fullName;
    private Integer status;
    private Date orderDate;
    private Date orderDateFinal;
    private AddressDTO address;
    private UserDTO user;
    private String staff;
    private Integer billType;
    private Long cartId;
    private String district;
    private String city;
    private String ward;
    private String voucher;
    private Double moneyRoot;
    private Double giaGiam;
    private Long shippingFee;
    private String reasonCancel;
}
