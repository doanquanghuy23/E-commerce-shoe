package com.huydq.clothing_ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorDTO extends TimeAuditableDTO implements Serializable {
    @Min(value = 0 , message = "Vui lòng chọn màu sắc")
    private Long id;
    @NotNull(message = "${message.err.nameColor}")
    @NotBlank(message = "${message.err.nameColor}")
    private String name;
    private Integer status;
}
