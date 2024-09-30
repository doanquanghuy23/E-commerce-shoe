package com.huydq.clothing_ecommerce.service;


import com.huydq.clothing_ecommerce.dto.thongKe.BillFinal;
import com.huydq.clothing_ecommerce.dto.thongKe.BillRate;
import com.huydq.clothing_ecommerce.dto.thongKe.SearchThongKeDTO;

import java.util.List;

public interface ThongKeService {
    List<BillFinal> getSumFinal(SearchThongKeDTO searchThongKeDTO);

    BillRate getRate(SearchThongKeDTO searchThongKeDTO);

    BillRate getBills(SearchThongKeDTO searchThongKeDTO);

    BillRate getProducts(SearchThongKeDTO searchThongKeDTO);
}
