package com.huydq.clothing_ecommerce.service.iplm;

import com.huydq.clothing_ecommerce.dto.*;
import com.huydq.clothing_ecommerce.dto.search.SearchProductDTO;
import com.huydq.clothing_ecommerce.entity.Color;
import com.huydq.clothing_ecommerce.entity.Product;
import com.huydq.clothing_ecommerce.entity.ProductDetail;
import com.huydq.clothing_ecommerce.entity.Size;
import com.huydq.clothing_ecommerce.exception.CustomValidationException;
import com.huydq.clothing_ecommerce.reponsitory.ProductDetailReponsitory;
import com.huydq.clothing_ecommerce.reponsitory.ProductReponsitory;
import com.huydq.clothing_ecommerce.service.ProductDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductDetailServiceIplm implements ProductDetailService {
    @Autowired
    private ProductDetailReponsitory productDetailRepo;

    @Autowired
    private ProductReponsitory productRepo;

    @Override
    public ProductDetail convertToEntity(ProductDetailDTO productDetailDTO) {
        return new ModelMapper().map(productDetailDTO, ProductDetail.class);
    }

    @Override
    public ProductDetailDTO convertToDto(ProductDetail productDetail) {
        return new ModelMapper().map(productDetail, ProductDetailDTO.class);
    }

    @Override
    public List<ProductDetailDTO> getAll() {
        return productDetailRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    @Override
    public PageDTO<List<ProductDetailDTO>> getByIdSp(PageRequestDTO pageRequestDTO, Long id) {
        pageRequestDTO.setPage(pageRequestDTO.getPage() == null ? 0 : pageRequestDTO.getPage());
        pageRequestDTO.setSize(pageRequestDTO.getSize() == null ? 5 : pageRequestDTO.getSize());
        Page<ProductDetail> pageEntity = productDetailRepo.findByProductId(id,
                PageRequest.of(
                        pageRequestDTO.getPage(),
                        pageRequestDTO.getSize()));
        List<ProductDetailDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList())
                .stream().filter(p -> p.getStatus() == 1 && p.getQuantity() > 0).collect(Collectors.toList());
        return PageDTO.<List<ProductDetailDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public PageDTO<List<ProductDetailDTO>> getByIdSpAd(PageRequestDTO pageRequestDTO, Long id) {
        pageRequestDTO.setPage(pageRequestDTO.getPage() == null ? 0 : pageRequestDTO.getPage());
        pageRequestDTO.setSize(pageRequestDTO.getSize() == null ? 5 : pageRequestDTO.getSize());
        Page<ProductDetail> pageEntity = productDetailRepo.findByProductId(id,
                PageRequest.of(
                        pageRequestDTO.getPage(),
                        pageRequestDTO.getSize()));
        List<ProductDetailDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<ProductDetailDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public PageDTO<List<ProductDetailDTO>> getByPageRequest(PageRequestDTO pageRequestDTO) {
        pageRequestDTO.setPage(pageRequestDTO.getPage() == null ? 0 : pageRequestDTO.getPage());
        pageRequestDTO.setSize(pageRequestDTO.getSize() == null ? 5 : pageRequestDTO.getSize());
        Page<ProductDetail> pageEntity = productDetailRepo.findAll(
                PageRequest.of(
                        pageRequestDTO.getPage(),
                        pageRequestDTO.getSize()));
        List<ProductDetailDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList())
                .stream().filter(p -> p.getStatus() == 1 && p.getQuantity() > 0).collect(Collectors.toList());
        return PageDTO.<List<ProductDetailDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public PageDTO<List<ProductDetailDTO>> search(SearchProductDTO searchProductDTO) {
        searchProductDTO.setPage(searchProductDTO.getPage() == null ? 0 : searchProductDTO.getPage());
        searchProductDTO.setSize(searchProductDTO.getSize() == null ? 5 : searchProductDTO.getSize());
        Page<ProductDetail> pageEntity = productDetailRepo.search(
                "%" + (searchProductDTO.getName() != null ? searchProductDTO.getName() : "") + "%",
                "%" + (searchProductDTO.getProductDetailCode() != null ? searchProductDTO.getProductDetailCode() : "") + "%",
                searchProductDTO.getBrandId(),
                searchProductDTO.getStatus(),
                searchProductDTO.getCategoryId(),
                searchProductDTO.getSizeId(),
                searchProductDTO.getColorId(),
                PageRequest.of(
                        searchProductDTO.getPage(),
                        searchProductDTO.getSize()));
        List<ProductDetailDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<ProductDetailDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public ProductDetailDTO findProductDetailByProductDetailCode(String productDetailCode) {
        return convertToDto(productDetailRepo.findProductDetailByProductDetailCode(productDetailCode));
    }

    @Override
    public ProductDetailDTO getById(Long id) {
        ProductDetail productDetail =productDetailRepo.findById(id).orElse(null);
        if(productDetail != null){
            return convertToDto(productDetail);
        }
        return null;
    }

    @Override
    public String create(ProductDetailDTO productDetailDTO) {
        Product product = productRepo.findById(productDetailDTO.getProduct().getId()).orElse(null);
        product.setTotalQuantity(product.getTotalQuantity() + productDetailDTO.getQuantity());
        productDetailDTO.setStatus(product.getStatus());
        productDetailDTO.setPrice(product.getPrice());
        productDetailDTO.setPriceSale(product.getPriceSale());
        productDetailDTO.setName(product.getName());
        productDetailDTO.setQuantitySold(0l);
        productDetailDTO.setAveragedReview(5.0);
        productDetailDTO.setProductDetailCode(generateRandomString());
        productDetailRepo.save(convertToEntity(productDetailDTO));
        productRepo.save(product);
        return null;
    }

    @Override
    public void update(ProductDetailDTO productDetailDTO) {
        ProductDetail productDetail = productDetailRepo.findById(productDetailDTO.getId()).orElseThrow(IllegalArgumentException::new);
        if (productDetail != null) {
            if (productDetailRepo.kiemTraMaSauKhiSua(productDetailDTO.getSize().getId(), productDetailDTO.getColor().getId(),
                    productDetailDTO.getId(), productDetailDTO.getProduct().getId()) > 0)
                throw new CustomValidationException("ton tia");
//            productDetail = convertToEntity(productDetailDTO);
            productDetail.setQuantity(productDetailDTO.getQuantity());
            productDetail.setColor(new ModelMapper().map(productDetailDTO.getColor(), Color.class));
            productDetail.setSize(new ModelMapper().map(productDetailDTO.getSize(), Size.class));
            Product product = productRepo.findById(productDetailDTO.getProduct().getId()).orElse(null);
            product.setTotalQuantity(product.getTotalQuantity() - productDetail.getQuantity() + productDetailDTO.getQuantity());
            productRepo.save(product);
            productDetailRepo.save(productDetail);
        }

    }

    @Override
    public void delete(Long id) {
        ProductDetail productDetail = productDetailRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        if (productDetail != null) {
            productDetailRepo.deleteById(id);
        }
    }

    @Override
    public List<ProductDetailDTO> searchByColorName(String nameColor, Long idProduct) {
        return productDetailRepo.searchByColorName(nameColor,idProduct).stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }
    @Override
    public ProductDetail searchBySizeAndColor(Long idProduct, Long idColor,Long idSize) {
        return productDetailRepo.searchBySizeAndColor(idProduct, idColor,idSize);
    }

    public String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return "SPCT" + randomString.toString();
    }

    @Override
    public void changeStatus(Long id , Integer status){
        ProductDetail productDetail = productDetailRepo.findById(id).orElse(null);
        if(productDetail != null){
            productDetail.setStatus(status);
            if(status == 1){
                productDetail.getProduct().setStatus(1);
            }else{
                List<ProductDetail> list = productDetailRepo.findByProduct(productDetail.getProduct().getId());
                list = list.stream().filter(x -> (x.getStatus() == 1)).collect(Collectors.toList());
                if(list.size() == 0){
                    productDetail.getProduct().setStatus(0);
                }
            }
            productDetailRepo.save(productDetail);
        }
    }
}
