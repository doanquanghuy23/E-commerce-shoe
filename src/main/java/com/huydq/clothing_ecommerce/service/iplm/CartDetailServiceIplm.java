package com.huydq.clothing_ecommerce.service.iplm;

import com.huydq.clothing_ecommerce.dto.CartDetailDTO;
import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.entity.Cart;
import com.huydq.clothing_ecommerce.entity.CartDetail;
import com.huydq.clothing_ecommerce.entity.ProductDetail;
import com.huydq.clothing_ecommerce.reponsitory.CartDetailReponsitory;
import com.huydq.clothing_ecommerce.reponsitory.CartReponsitory;
import com.huydq.clothing_ecommerce.reponsitory.ProductDetailReponsitory;
import com.huydq.clothing_ecommerce.service.CartDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartDetailServiceIplm implements CartDetailService {
    @Autowired
    private CartDetailReponsitory cartDetailRepo;

    @Autowired
    private CartReponsitory cartRepo;

    @Autowired
    private ProductDetailReponsitory productDetailRepo;

    @Override
    public CartDetail convertToEntity(CartDetailDTO cartDetailDTO) {
        return new ModelMapper().map(cartDetailDTO, CartDetail.class);
    }

    @Override
    public CartDetailDTO convertToDto(CartDetail cartDetail) {
        return new ModelMapper().map(cartDetail, CartDetailDTO.class);
    }

    @Override
    public List<CartDetailDTO> getAll() {
        return cartDetailRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    @Override
    public PageDTO<List<CartDetailDTO>> getByPageRequest(PageRequestDTO pageRequestDTO) {
        pageRequestDTO.setPage(pageRequestDTO.getPage() == null ? 0 : pageRequestDTO.getPage());
        pageRequestDTO.setSize(pageRequestDTO.getSize() == null ? 5 : pageRequestDTO.getSize());
        Page<CartDetail> pageEntity = cartDetailRepo.findAll(
                PageRequest.of(
                        pageRequestDTO.getPage(),
                        pageRequestDTO.getSize()));
        List<CartDetailDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<CartDetailDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public CartDetailDTO getById(Long id) {
        return convertToDto(cartDetailRepo.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public CartDetailDTO create(CartDetailDTO cartDetail) {
        Cart cart = cartRepo.findById(cartDetail.getCart().getId()).orElse(null);
        ProductDetail productDetail = productDetailRepo.findById(cartDetail.getProductDetail().getId()).orElse(null);
        List<CartDetail> cartDetails = cartDetailRepo.findByCartIdAndProductDetailId(cartDetail.getCart().getId(), cartDetail.getProductDetail().getId());
        if (cart != null && productDetail != null) {
            if (cartDetails.isEmpty()) {
                cart.setTotalProduct(cart.getTotalProduct() + 1);
                cart.setTotalMoney(cart.getTotalMoney() + productDetail.getPriceSale() * cartDetail.getQuantity());
            } else {
                cart.setTotalMoney(cart.getTotalMoney() + productDetail.getPriceSale() * cartDetail.getQuantity());
                cartDetail.setQuantity(cartDetail.getQuantity() + cartDetails.get(0).getQuantity());
                cartDetail.setId(cartDetails.get(0).getId());
            }
        }
        return convertToDto(cartDetailRepo.save(convertToEntity(cartDetail)));
    }

    @Override
    public CartDetailDTO update(CartDetailDTO cartDetailDTO) {
        CartDetail cartDetail = cartDetailRepo.findById(cartDetailDTO.getId()).orElseThrow(IllegalArgumentException::new);
        if (cartDetail != null) {
            Cart cart = cartDetail.getCart();
            cart.setTotalMoney(
                    (cart.getTotalMoney() - cartDetail.getProductDetail().getPriceSale() * cartDetail.getQuantity())
                            + cartDetail.getProductDetail().getPriceSale() * cartDetailDTO.getQuantity()
            );
            cartDetail = convertToEntity(cartDetailDTO);
            return convertToDto(cartDetailRepo.save(cartDetail));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        CartDetail cartDetail = cartDetailRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        if (cartDetail != null) {
            Cart cart = cartDetail.getCart();
            cart.setTotalProduct(cart.getTotalProduct() - 1);
            cart.setTotalMoney(cart.getTotalMoney() - (cartDetail.getQuantity() * cartDetail.getProductDetail().getPriceSale()));
            cartDetailRepo.deleteById(id);
        }
    }

    @Override
    public CartDetailDTO findByCartIdAndProductDetailId(Long cartId, Long productDetailId) {
        return convertToDto(cartDetailRepo.findByCartIdAndProductDetailId(cartId,productDetailId).get(0));
    }
}
