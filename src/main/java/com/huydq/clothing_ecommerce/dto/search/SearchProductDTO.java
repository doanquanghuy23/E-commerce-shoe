package com.huydq.clothing_ecommerce.dto.search;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductDTO {
    private String name;
    private String productDetailCode;
    private Long brandId;
    private Integer status;
    private Long categoryId;
    private Long sizeId;
    private Long colorId;
    private Integer page;
    private Integer size;
    private String order;
}
