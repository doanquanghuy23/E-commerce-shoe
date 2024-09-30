package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.dto.ProductDTO;
import com.huydq.clothing_ecommerce.dto.search.SearchProductDTO;
import com.huydq.clothing_ecommerce.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Product convertToEntity(ProductDTO productDTO);

    ProductDTO convertToDto(Product product);

    List<ProductDTO> getAll();

    PageDTO<List<ProductDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);
    PageDTO<List<ProductDTO>> getByBestSeller(PageRequestDTO pageRequestDTO);
    PageDTO<List<ProductDTO>> getByNew(PageRequestDTO pageRequestDTO);

    ProductDTO getById(Long id);

    void addProducts(MultipartFile multipartFile);
    void create(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(Long id);

    PageDTO<List<ProductDTO>> search(SearchProductDTO searchProductDTO);

    PageDTO<List<ProductDTO>> searchOnline(SearchProductDTO searchProductDTO);

    void changeStatus(Long id, Integer status);
}
