package com.huydq.clothing_ecommerce.dto;

import com.huydq.clothing_ecommerce.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends TimeAuditableDTO implements Serializable {
    private Long id;
    private String userCode;
    private String phoneNumber;
    private String fullName;
    private String password;
    private String email;
    private String image;
    private Role role;
    private Integer status;
    private Double totalInvoiceValue;
    private Long totalInvoice;
    private Integer userType;
    private FavouriteDTO favourite;

    public UserDTO(Long id){
        this.id = id;
    }
}
