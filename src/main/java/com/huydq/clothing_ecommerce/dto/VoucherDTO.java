package com.huydq.clothing_ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoucherDTO extends TimeAuditableDTO {
    private Long id;
    private String name;
    private String voucherCode;
    private Double maximumPromotion;
    private Double promotionalLevel;
    private Double minimumInvoice;
    private Double discount;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Long quantity;
    private Integer voucherType; // 1 phan tram , 2 gia
    private Integer status;
    private Integer sendType; // 1 chưa gửi , 2 đã gửi
}
