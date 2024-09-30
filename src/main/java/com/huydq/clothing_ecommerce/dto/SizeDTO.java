package com.huydq.clothing_ecommerce.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeDTO extends TimeAuditableDTO implements Serializable {
    @Min(value = 0,message = "Vui lòng chọn kích thước")
    private Long id;
    private String name;
    private Integer status;
}
