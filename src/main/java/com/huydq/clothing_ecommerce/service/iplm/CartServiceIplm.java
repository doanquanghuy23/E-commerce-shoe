package com.huydq.clothing_ecommerce.service.iplm;

import com.huydq.clothing_ecommerce.dto.CartDTO;
import com.huydq.clothing_ecommerce.dto.CartDetailDTO;
import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.entity.Cart;
import com.huydq.clothing_ecommerce.reponsitory.CartDetailReponsitory;
import com.huydq.clothing_ecommerce.reponsitory.CartReponsitory;
import com.huydq.clothing_ecommerce.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceIplm implements CartService {
    private static final String HASH_KEY = "Cart";
    @Autowired
    private CartReponsitory cartRepo;

    @Autowired
    private CartDetailReponsitory cartDetailRepo;

    @Override
    public Cart convertToEntity(CartDTO cartDTO) {
        return new ModelMapper().map(cartDTO, Cart.class);
    }

    @Override
    public CartDTO convertToDto(Cart cart) {
        return new ModelMapper().map(cart, CartDTO.class);
    }

    @Override
    public List<CartDTO> getAll() {
        return cartRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    @Override
    public CartDTO getByUserId(Long id) {
        CartDTO cartDTO = convertToDto(cartRepo.findByUserId(id));
        cartDTO.setCartDetails(
                cartDetailRepo.findByCartId(cartDTO.getId())
                        .stream().map(c -> new ModelMapper().map(c, CartDetailDTO.class))
                        .collect(Collectors.toList())
        );
        return cartDTO;
    }

    @Override
    public PageDTO<List<CartDTO>> getByPageRequest(PageRequestDTO pageRequestDTO) {
        pageRequestDTO.setPage(pageRequestDTO.getPage() == null ? 0 : pageRequestDTO.getPage());
        pageRequestDTO.setSize(pageRequestDTO.getSize() == null ? 5 : pageRequestDTO.getSize());
        Page<Cart> pageEntity = cartRepo.findAll(
                PageRequest.of(
                        pageRequestDTO.getPage(),
                        pageRequestDTO.getSize()));
        List<CartDTO> listDto = pageEntity.get().map(a -> convertToDto(a)).collect(Collectors.toList());
        return PageDTO.<List<CartDTO>>builder()
                .data(listDto)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();
    }

    @Override
    public CartDTO getById(Long id) {
        CartDTO cartDTO =  convertToDto(cartRepo.findById(id).orElseThrow(IllegalArgumentException::new));
        cartDTO.setCartDetails(
                cartDetailRepo.findByCartId(cartDTO.getId())
                        .stream().map(c -> new ModelMapper().map(c, CartDetailDTO.class))
                        .collect(Collectors.toList())
        );
        return cartDTO;
    }

    @Override
    public void create(CartDTO cartDTO) {
        cartRepo.save(convertToEntity(cartDTO));
    }

    @Override
    public void update(CartDTO cartDTO) {
        Cart cart = cartRepo.findById(cartDTO.getId()).orElseThrow(IllegalArgumentException::new);
        if (cart != null) {
            cart = convertToEntity(cartDTO);
            cartRepo.save(cart);
        }

    }

    @Override
    public void delete(Long id) {
        Cart cart = cartRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        if (cart != null) {
            cartRepo.deleteById(id);
        }
    }
}
