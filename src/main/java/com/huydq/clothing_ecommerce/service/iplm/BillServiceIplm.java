package com.huydq.clothing_ecommerce.service.iplm;

import com.huydq.clothing_ecommerce.dto.*;
import com.huydq.clothing_ecommerce.dto.search.SearchBillCustomerDTO;
import com.huydq.clothing_ecommerce.dto.search.SearchBillDTO;
import com.huydq.clothing_ecommerce.entity.*;
import com.huydq.clothing_ecommerce.reponsitory.*;
import com.huydq.clothing_ecommerce.service.BillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BillServiceIplm implements BillService {
    @Autowired
    private BillReponsitory billReponsitory;

    @Autowired
    private BillDetailReponsitory billDetailRepo;

    @Autowired
    private UserReponsitory userRepon;

    @Autowired
    private CartReponsitory cartRepo;

    @Autowired
    private CartDetailReponsitory cartDetailRepo;

    @Autowired
    private ProductReponsitory productRepo;

    @Autowired
    private ProductDetailReponsitory productDetailRepo;

    @Override
    public Bill convertToEntity(BillDTO billDTO) {
        return new ModelMapper().map(billDTO, Bill.class);
    }

    @Override
    public BillDTO convertToDto(Bill bill) {
        return new ModelMapper().map(bill, BillDTO.class);
    }

    @Override
    public List<BillDTO> getAll() {
        return billReponsitory.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    @Override
    public PageDTO<List<BillDTO>> getByPageRequest(PageRequestDTO pageRequestDTO) {
        pageRequestDTO.setPage(pageRequestDTO.getPage() == null ? 0 : pageRequestDTO.getPage());
        pageRequestDTO.setSize(pageRequestDTO.getSize() == null ? 5 : pageRequestDTO.getSize());
        Page<Bill> pageEntity = billReponsitory.findAll(
                PageRequest.of(
                        pageRequestDTO.getPage(),
                        pageRequestDTO.getSize()));
        List<BillDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<BillDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public PageDTO<List<BillDTO>> search(SearchBillDTO searchBillDTO) {
        searchBillDTO.setPage(searchBillDTO.getPage() == null ? 0 : searchBillDTO.getPage());
        searchBillDTO.setSize(searchBillDTO.getSize() == null ? 5 : searchBillDTO.getSize());
        Page<Bill> pageEntity = billReponsitory.search(
                PageRequest.of(
                        searchBillDTO.getPage(),
                        searchBillDTO.getSize()),
                searchBillDTO.getStatus(),
                searchBillDTO.getDateStart(),
                searchBillDTO.getDateEnd(),
                searchBillDTO.getStaff(),
                searchBillDTO.getPhoneNumber());
        List<BillDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<BillDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public PageDTO<List<BillDTO>> searchAtStore(SearchBillDTO searchBillDTO) {
        searchBillDTO.setPage(searchBillDTO.getPage() == null ? 0 : searchBillDTO.getPage());
        searchBillDTO.setSize(searchBillDTO.getSize() == null ? 5 : searchBillDTO.getSize());
        Page<Bill> pageEntity = billReponsitory.searchAtStore(
                PageRequest.of(
                        searchBillDTO.getPage(),
                        searchBillDTO.getSize()),
                searchBillDTO.getStatus(),
                searchBillDTO.getDateStart(),
                searchBillDTO.getDateEnd(),
                "%" + (searchBillDTO.getStaff() == null ? "" : searchBillDTO.getStaff()) + "%",
                "%" + (searchBillDTO.getPhoneNumber() == null ? "" : searchBillDTO.getPhoneNumber()) + "%",
                searchBillDTO.getBillType());
        List<BillDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<BillDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public PageDTO<List<BillDTO>> getSellAtStore(PageRequestDTO pageRequestDTO) {
        pageRequestDTO.setPage(pageRequestDTO.getPage() == null ? 0 : pageRequestDTO.getPage());
        pageRequestDTO.setSize(pageRequestDTO.getSize() == null ? 5 : pageRequestDTO.getSize());
        Page<Bill> pageEntity = billReponsitory.getSellAtStore(
                PageRequest.of(
                        pageRequestDTO.getPage(),
                        pageRequestDTO.getSize()));
        List<BillDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<BillDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public PageDTO<List<BillDTO>> getByStatus(PageRequestDTO pageRequestDTO, Integer status) {
        pageRequestDTO.setPage(pageRequestDTO.getPage() == null ? 0 : pageRequestDTO.getPage());
        pageRequestDTO.setSize(pageRequestDTO.getSize() == null ? 5 : pageRequestDTO.getSize());
        Page<Bill> pageEntity = billReponsitory.getByStatus(
                PageRequest.of(
                        pageRequestDTO.getPage(),
                        pageRequestDTO.getSize()),
                status);
        List<BillDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<BillDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public BillDTO getById(Long id) {
        return convertToDto(billReponsitory.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public Bill createAtStore(BillDTO billDTO) {
        Bill bill = convertToEntity(billDTO);
        bill.setStatus(1);
        bill.setTotalMoney(0.0);
        bill.setTatolProduct(0L);
        bill.setBillType(1);
        bill.setBillCode(generateRandomString());
        bill.setOrderDate(bill.getCreatedAt());
        bill.setMoneyRoot(0d);
        bill.setGiaGiam(0d);
        bill.setShippingFee(0l);
        return billReponsitory.save(bill);
    }

    @Override
    public void updateSellAtStoreFinal(BillDTO billDTO) {
        Voucher voucher = voucherRepo.findByVoucherCode(billDTO.getVoucher()).orElse(null);
        Bill bill = billReponsitory.findById(billDTO.getId()).orElseThrow(IllegalArgumentException::new);
        if (bill != null) {
            bill.setStatus(billDTO.getStatus());
            bill.setFullName(billDTO.getFullName());
            bill.setPhoneNumber(billDTO.getPhoneNumber());
            bill.setAddressDetail(billDTO.getAddressDetail());
            bill.setMoneyRoot(bill.getTotalMoney());
            bill.setOrderDateFinal(new Date());
            if(voucher != null){
                if (voucher.getVoucherType() == 1) {
                    double gia = bill.getTotalMoney() * (voucher.getPromotionalLevel()/100);
                    if (gia > voucher.getMaximumPromotion()) {
                        gia = voucher.getMaximumPromotion();
                    }
                    bill.setGiaGiam(gia);
                    bill.setTotalMoney(bill.getTotalMoney() - gia);
                } else {
                    bill.setTotalMoney(bill.getTotalMoney() - voucher.getPromotionalLevel());
                }
                voucher.setQuantity(voucher.getQuantity() - 1);
                voucherRepo.save(voucher);
            }else {
                bill.setGiaGiam(0d);
            }
            Users user = userRepon.findByPhoneNumber(billDTO.getUser().getPhoneNumber()).orElse(null);
            if (user != null) {
                bill.setUser(user);
                bill.setPhoneNumber(billDTO.getUser().getPhoneNumber());
                bill.setFullName(user.getFullName());
                billReponsitory.save(bill);
            } else {
                if (billDTO.getUser() == null || billDTO.getUser().getPhoneNumber() == null || "".equals(billDTO.getUser().getPhoneNumber())) {
                    bill.setFullName("Khách lẻ");
                    billReponsitory.save(bill);
                } else {
                    bill.setPhoneNumber(billDTO.getPhoneNumber() == null ? billDTO.getUser().getPhoneNumber() : billDTO.getPhoneNumber());
                    bill.setFullName(billDTO.getFullName() == null ? billDTO.getUser().getPhoneNumber() : billDTO.getFullName());
                    bill.setOrderDateFinal(bill.getStatus() == 5 ? new Date() : null);
                    Bill b = billReponsitory.save(bill);
                    if (user == null && !"".equals(billDTO.getUser().getPhoneNumber()) && billDTO.getUser().getPhoneNumber() != null) {
                        Users users = new Users();
                        users.setPhoneNumber(billDTO.getUser().getPhoneNumber());
                        users.setRole(Role.CUSTOMER);
                        users.setStatus(1);
                        users.setTotalInvoiceValue(b.getTotalMoney());
                        users.setUserCode("KH" + generateRandomString());
                        users.setTotalInvoice(1L);
                        Users u = userRepon.save(users);
                        cartRepo.save(new Cart(0L, 0.0, u));
                    }
                }
            }

        }
    }

    @Override
    public void updateStatusById(Long id, Integer status) {
        Bill bill = billReponsitory.findById(id).orElse(null);
        if (bill != null) {
            bill.setOrderDateFinal(status == 5 ? new Date() : null);
            bill.setStatus(status);
            billReponsitory.save(bill);
            if(status == 5 && bill.getVoucher() != null && bill.getGiaGiam() > 0){
                Voucher voucher = voucherRepo.findByVoucherCode(bill.getVoucher()).orElse(null);
                if (voucher != null) {
                    voucher.setQuantity(voucher.getQuantity() + 1);
                    voucherRepo.save(voucher);
                }
            }
            if (status == 0) {
                if (bill.getVoucher() != null || !"".equals(bill.getVoucher())) {
                    Voucher voucher = voucherRepo.findByVoucherCode(bill.getVoucher()).orElse(null);
                    if (voucher != null) {
                        voucher.setQuantity(voucher.getQuantity() + 1);
                        voucherRepo.save(voucher);
                    }
                }
                List<BillDetail> billDetails = billDetailRepo.findByBillId(bill.getId());
                billDetails.stream().forEach(x -> {
                    ProductDetail productDetail = productDetailRepo.findById(x.getProductDetail().getId()).orElse(null);
                    productDetail.setQuantitySold(productDetail.getQuantitySold() - x.getQuantity());
                    productDetail.setQuantity(productDetail.getQuantity() + x.getQuantity());
                    Product product = productRepo.findById(productDetail.getProduct().getId()).orElse(null);
                    product.setTotalQuantitySold(product.getTotalQuantitySold() - x.getQuantity());
                    product.setTotalQuantity(product.getTotalQuantity() + x.getQuantity());
                    productDetailRepo.save(productDetail);
                    productRepo.save(product);
                });
            }
        }
    }

    @Override
    public void updateStatusById(Long id, Integer status, String reason) {
        Bill bill = billReponsitory.findById(id).orElse(null);
        if (bill != null) {
            bill.setStatus(status);
            bill.setReasonCancel(reason);
            billReponsitory.save(bill);
            if(status == 5 && bill.getVoucher() != null && bill.getGiaGiam() > 0){
                Voucher voucher = voucherRepo.findByVoucherCode(bill.getVoucher()).orElse(null);
                if (voucher != null) {
                    voucher.setQuantity(voucher.getQuantity() + 1);
                    voucherRepo.save(voucher);
                }
            }
            if (status == 0) {
                if (bill.getVoucher() != null || !"".equals(bill.getVoucher())) {
                    Voucher voucher = voucherRepo.findByVoucherCode(bill.getVoucher()).orElse(null);
                    if (voucher != null) {
                        voucher.setQuantity(voucher.getQuantity() + 1);
                        voucherRepo.save(voucher);
                    }
                }
                List<BillDetail> billDetails = billDetailRepo.findByBillId(bill.getId());
                billDetails.stream().forEach(x -> {
                    ProductDetail productDetail = productDetailRepo.findById(x.getProductDetail().getId()).orElse(null);
                    productDetail.setQuantitySold(productDetail.getQuantitySold() - x.getQuantity());
                    productDetail.setQuantity(productDetail.getQuantity() + x.getQuantity());
                    Product product = productRepo.findById(productDetail.getProduct().getId()).orElse(null);
                    product.setTotalQuantitySold(product.getTotalQuantitySold() - x.getQuantity());
                    product.setTotalQuantity(product.getTotalQuantity() + x.getQuantity());
                    productDetailRepo.save(productDetail);
                    productRepo.save(product);
                });
            }
        }
    }

    @Override
    public void updateStatusById(Long id, Integer status, Long quantity) {
        Bill bill = billReponsitory.findById(id).orElse(null);
        if (bill != null) {
            bill.setTotalMoney(bill.getTotalMoney() + quantity);
            bill.setOrderDateFinal(status == 5 ? new Date() : null);
            bill.setStatus(status);
            bill.setShippingFee(quantity);
            billReponsitory.save(bill);
            if(status == 5 && bill.getVoucher() != null && bill.getGiaGiam() > 0){
                Voucher voucher = voucherRepo.findByVoucherCode(bill.getVoucher()).orElse(null);
                if (voucher != null) {
                    voucher.setQuantity(voucher.getQuantity() + 1);
                    voucherRepo.save(voucher);
                }
            }
            if (status == 0) {
                if (bill.getVoucher() != null || !"".equals(bill.getVoucher())) {
                    Voucher voucher = voucherRepo.findByVoucherCode(bill.getVoucher()).orElse(null);
                    if (voucher != null) {
                        voucher.setQuantity(voucher.getQuantity() + 1);
                        voucherRepo.save(voucher);
                    }
                }
                List<BillDetail> billDetails = billDetailRepo.findByBillId(bill.getId());
                billDetails.stream().forEach(x -> {
                    ProductDetail productDetail = productDetailRepo.findById(x.getProductDetail().getId()).orElse(null);
                    productDetail.setQuantitySold(productDetail.getQuantitySold() - x.getQuantity());
                    productDetail.setQuantity(productDetail.getQuantity() + x.getQuantity());
                    Product product = productRepo.findById(productDetail.getProduct().getId()).orElse(null);
                    product.setTotalQuantitySold(product.getTotalQuantitySold() - x.getQuantity());
                    product.setTotalQuantity(product.getTotalQuantity() + x.getQuantity());
                    productDetailRepo.save(productDetail);
                    productRepo.save(product);
                });
            }
        }
    }

    @Autowired
    private VoucherReponsitory voucherRepo;

    @Override
    public void createBillOnline(BillDTO billDTO) {
        Voucher voucher = voucherRepo.findByVoucherCode(billDTO.getVoucher()).orElse(null);
        List<CartDetail> cartDetails = cartDetailRepo.findByCartId(billDTO.getCartId());
        Cart cart = cartRepo.findById(billDTO.getCartId()).orElse(null);
        Users users = userRepon.findById(billDTO.getUser().getId()).orElse(null);
        Bill bill = new Bill();
        if (users != null) {
            users.setTotalInvoiceValue(users.getTotalInvoiceValue() + cart.getTotalMoney());
            users.setTotalInvoice(users.getTotalInvoice() + 1l);
            bill.setUser(users);
        }
        String addressDetail = billDTO.getAddressDetail()
                + ", " + billDTO.getWard().split("\\|")[1]
                + ", " + billDTO.getDistrict().split("\\|")[1]
                + ", " + billDTO.getCity().split("\\|")[1];
        bill.setBillCode(generateRandomString());
        bill.setStatus(1);
        bill.setPhoneNumber(billDTO.getUser().getPhoneNumber());
        bill.setFullName(billDTO.getFullName());
        bill.setAddressDetail(addressDetail);
        bill.setBillType(2);
        bill.setTatolProduct(cart.getTotalProduct());
        bill.setMoneyRoot(cart.getTotalMoney());
        bill.setShippingFee(0l);
        if (voucher != null && voucher.getMinimumInvoice() <= cart.getTotalMoney()) {
            if (voucher.getVoucherType() == 1) {
                double gia = cart.getTotalMoney() * (voucher.getPromotionalLevel()/100);
                if (gia > voucher.getMaximumPromotion()) {
                    gia = voucher.getMaximumPromotion();
                }
                bill.setGiaGiam(gia);
                bill.setTotalMoney(cart.getTotalMoney() - gia);
            } else {
                bill.setTotalMoney(cart.getTotalMoney() - voucher.getPromotionalLevel());
            }
            voucher.setQuantity(voucher.getQuantity() - 1);
            bill.setVoucher(voucher.getVoucherCode());
            voucherRepo.save(voucher);
        } else {
            bill.setTotalMoney(cart.getTotalMoney());
            bill.setGiaGiam(0d);
        }
        Bill billSave = billReponsitory.save(bill);
        List<BillDetail> billDetailLists = new ArrayList<>();
        cartDetails.forEach(x -> {
            BillDetail billDetail = new BillDetail();
            billDetail.setPrice(x.getProductDetail().getPriceSale());
            billDetail.setBill(billSave);
            billDetail.setQuantity(x.getQuantity());
            billDetail.setProductDetail(x.getProductDetail());
            billDetail.setTotalPrice(x.getProductDetail().getPriceSale() * x.getQuantity());
            billDetailLists.add(billDetail);
            ProductDetail productDetail = x.getProductDetail();
            Product product = productDetail.getProduct();
            productDetail.setQuantity(productDetail.getQuantity() - x.getQuantity());
            product.setTotalQuantity(product.getTotalQuantity() - x.getQuantity());
            productDetail.setQuantitySold(productDetail.getQuantitySold() + x.getQuantity());
            product.setTotalQuantitySold(product.getTotalQuantitySold() + x.getQuantity());
            productRepo.save(product);
            productDetailRepo.save(productDetail);
        });
        billDetailRepo.saveAll(billDetailLists);
        cartDetailRepo.deleteAll(cartDetails);
        cart.setTotalProduct(0L);
        cart.setTotalMoney(0d);
        cartRepo.save(cart);
    }

    @Override
    public void delete(Long id) {
        Bill bill = billReponsitory.findById(id).orElseThrow(IllegalArgumentException::new);
        if (bill != null) {
            billReponsitory.deleteById(id);
        }
    }

    @Override
    public void cancelBill(Long id) {
        Bill bill = billReponsitory.findById(id).orElseThrow(IllegalArgumentException::new);
        bill.setStatus(0);
        List<BillDetail> billDetailList = billDetailRepo.findByBillId(id);
        billDetailList.stream().forEach(billDetail -> {
            ProductDetail productDetail = billDetail.getProductDetail();
            Product product = productDetail.getProduct();
            productDetail.setQuantitySold(productDetail.getQuantitySold() - billDetail.getQuantity());
            product.setTotalQuantitySold(product.getTotalQuantitySold() - billDetail.getQuantity());
            productDetail.setQuantity(productDetail.getQuantity() + billDetail.getQuantity());
            product.setTotalQuantity(product.getTotalQuantity() + billDetail.getQuantity());
        });
        billReponsitory.save(bill);
    }

    @Override
    public void updateAddress(BillDTO billDTO) {
        Bill bill = billReponsitory.findById(billDTO.getId()).orElse(null);
        if (bill != null) {
            bill.setAddressDetail(billDTO.getAddressDetail());
            bill.setFullName(billDTO.getFullName());
            bill.setPhoneNumber(billDTO.getUser().getPhoneNumber());
            billReponsitory.save(bill);
        }
    }

    @Override
    public PageDTO<List<BillDTO>> searchByCustomer(SearchBillCustomerDTO searchBillCustomerDTO) {
        searchBillCustomerDTO.setPage(searchBillCustomerDTO.getPage() == null ? 0 : searchBillCustomerDTO.getPage());
        searchBillCustomerDTO.setSize(searchBillCustomerDTO.getSize() == null ? 5 : searchBillCustomerDTO.getSize());
        Page<Bill> pageEntity = billReponsitory.searchByCustomer(
                PageRequest.of(
                        searchBillCustomerDTO.getPage(),
                        searchBillCustomerDTO.getSize()),
                searchBillCustomerDTO.getUserId(),
                searchBillCustomerDTO.getStatus());
        List<BillDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<BillDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    //utils
    public String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return "HD" + randomString.toString();
    }

    public boolean checkStatusBill(Long id, Integer status) {
        Bill bill = billReponsitory.findByIdAndStatus(id, status).orElse(null);
        if (bill == null) return false;
        return true;
    }
}
