package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.*;
import com.huydq.clothing_ecommerce.dto.search.SearchBillCustomerDTO;
import com.huydq.clothing_ecommerce.dto.search.SearchBillDTO;
import com.huydq.clothing_ecommerce.entity.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {
    Bill convertToEntity(BillDTO billDTO);

    BillDTO convertToDto(Bill bill);

    List<BillDTO> getAll();

    PageDTO<List<BillDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    PageDTO<List<BillDTO>> search(SearchBillDTO searchBillDTO);

    PageDTO<List<BillDTO>> searchAtStore(SearchBillDTO searchBillDTO);

    PageDTO<List<BillDTO>> getSellAtStore(PageRequestDTO pageRequestDTO);
    PageDTO<List<BillDTO>> getByStatus(PageRequestDTO pageRequestDTO, Integer status);

    BillDTO getById(Long id);

    Bill createAtStore(BillDTO billDTO);

    void updateSellAtStoreFinal(BillDTO billDTO);

    void updateStatusById(Long id, Integer status);


    void updateStatusById(Long id, Integer status, Long quantity);
    void updateStatusById(Long id, Integer status, String reson);

    void createBillOnline(BillDTO billDTO);

    void delete(Long id);

    void cancelBill(Long id);

    void updateAddress(BillDTO billDTO);

    PageDTO<List<BillDTO>> searchByCustomer(SearchBillCustomerDTO searchBillCustomerDTO);

    boolean checkStatusBill(Long id, Integer status);
}
