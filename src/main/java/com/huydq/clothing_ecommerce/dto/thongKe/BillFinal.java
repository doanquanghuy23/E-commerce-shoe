package com.huydq.clothing_ecommerce.dto.thongKe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillFinal {
    private String date;
    private Long totalMoney;
}
