package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.*;
import com.huydq.clothing_ecommerce.entity.BillDetail;

import java.util.List;

public interface BillDetailService {
    BillDetail convertToEntity(BillDetailDTO billDetailDTO);


    BillDetailDTO convertToDto(BillDetail billDetail);

    List<BillDetailDTO> getAllByBillId(Long id);

    List<BillDetailDTO> getAll();

    PageDTO<List<BillDetailDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    PageDTO<List<BillDetailDTO>> getByBillId(Long id, PageRequestDTO pageRequestDTO);

    BillDetailDTO getById(Long id);

    void create(BillDetailDTO billDetailDTO);

    void update(BillDetailDTO billDetailDTO);

    void delete(Long id);
}
