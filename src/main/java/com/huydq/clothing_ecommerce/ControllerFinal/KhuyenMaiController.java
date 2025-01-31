package com.huydq.clothing_ecommerce.ControllerFinal;

import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.dto.VoucherDTO;
import com.huydq.clothing_ecommerce.service.UserService;
import com.huydq.clothing_ecommerce.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khuyen-mai")
public class KhuyenMaiController {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String home(Model model, @RequestParam(required = false, name = "page") Integer page){
        model.addAttribute("list", voucherService.getByPageRequest(new PageRequestDTO(page, 5)));
        return "KhuyenMai/KhuyenMai";
    }

    @PostMapping("/action")
    public String action(@ModelAttribute VoucherDTO voucherDTO){
        voucherService.action(voucherDTO);
        return "redirect:/khuyen-mai";
    }

    @GetMapping("/sendEmail/{id}")
    @ResponseBody
    public void sendEmail(@PathVariable("id") Long id){
        voucherService.sendEmail(id);
    }


    @GetMapping("/voucherApp/{voucherCode}")
    @ResponseBody
    public VoucherDTO findByVoucherCode(@PathVariable("voucherCode") String voucherCode){
        return voucherService.findByVoucherCode(voucherCode);
    }
    @GetMapping("/voucherApp2/{voucherCode}")
    @ResponseBody
    public VoucherDTO findByVoucherCode2(@PathVariable("voucherCode") String voucherCode){
        return voucherService.findByVoucherCode2(voucherCode);
    }
}
