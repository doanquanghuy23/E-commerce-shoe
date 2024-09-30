package com.huydq.clothing_ecommerce.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO extends TimeAuditableDTO implements Serializable {
    private Long id;
    private String name;
    private Integer status;
}
