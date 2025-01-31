package com.huydq.clothing_ecommerce.ControllerFinal;

import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.dto.ProductDetailDTO;
import com.huydq.clothing_ecommerce.entity.ProductDetail;
import com.huydq.clothing_ecommerce.service.ColorService;
import com.huydq.clothing_ecommerce.service.ProductDetailService;
import com.huydq.clothing_ecommerce.service.SizeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chi-tiet-sp")
public class ChiTietSanPhamController {

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private HttpSession session;

    @GetMapping
    public String home() {
        return "ChiTietSanPham/ChiTietSanPham";
    }

    @GetMapping("/{idSP}")
    public String home(@RequestParam(required = false, name = "page") Integer page, @PathVariable("idSP") Long idSp, Model model) {
        model.addAttribute("list", productDetailService.getByIdSpAd(new PageRequestDTO(page, 5), idSp));
        model.addAttribute("sizes", sizeService.getAll());
        model.addAttribute("colors", colorService.getAll());
        model.addAttribute("idSp", idSp);
        return "ChiTietSanPham/ChiTietSanPham";
    }

    @GetMapping("/changeStatus/{id}/{status}")
    @ResponseBody
    public void changeStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status){
        productDetailService.changeStatus(id,status);
    }

    @GetMapping("/api/{productCode}")
    @ResponseBody
    public ProductDetailDTO getProductDetailApi(@PathVariable("productCode") String productCode) {
        session.setAttribute("billType", 1);
        return productDetailService.findProductDetailByProductDetailCode(productCode);
    }

    @PostMapping("/action")
    public String action(@ModelAttribute ProductDetailDTO productDetail) {
        if (productDetail.getId() == null) {
            String _return = productDetailService.create(productDetail);
        }else {
            productDetailService.update(productDetail);
        }
        return "redirect:/chi-tiet-sp/" + productDetail.getProduct().getId();
    }

    //check số lượng khi thêm vào giỏ hàng
    @GetMapping("/quanlity/{id}/{number}")
    @ResponseBody
    public String checkSoLuong(@PathVariable("id") Long id,
                               @PathVariable("number") Long number) {
        ProductDetailDTO productDetail = productDetailService.getById(id);
        if (productDetail == null) {
            return "Sản phẩm không tồn tại";
        }
        Long quantity = productDetailService.getById(id).getQuantity();
        if (quantity < number) {
            return "Số lượng chỉ còn " + quantity;
        }
        return "";
    }

    @GetMapping("/check")
    @ResponseBody
    public int isEmpty(
            @RequestParam("idProduct") Long idProduct,
            @RequestParam("idColor") String idColor,
            @RequestParam("idSize") String idSize
    ) {
        ProductDetail productDetail =
                productDetailService.searchBySizeAndColor(idProduct, Long.valueOf(idColor), Long.valueOf(idSize));
        System.out.println(productDetail);
        if (productDetail != null) {
            return 0;
        } else {
            return -1;
        }
    }

}
