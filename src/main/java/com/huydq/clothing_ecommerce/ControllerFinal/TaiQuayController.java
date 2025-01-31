package com.huydq.clothing_ecommerce.ControllerFinal;

import com.huydq.clothing_ecommerce.dto.*;
import com.huydq.clothing_ecommerce.entity.Users;
import com.huydq.clothing_ecommerce.service.BillDetailService;
import com.huydq.clothing_ecommerce.service.BillService;
import com.huydq.clothing_ecommerce.service.ProductDetailService;
import com.huydq.clothing_ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tai-quay")
public class TaiQuayController {

    @Autowired
    private UserService userService;
    @Autowired
    private BillService billService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private BillDetailService billDetailService;
    @Autowired
    private HttpSession session;

    @GetMapping("")
    public String home(Model model,@RequestParam(required = false, name = "page") Integer pageDetail,
                       @RequestParam(name = "idBill",required = false) Long idBill) {
        Users users = (Users) session.getAttribute("user");
        boolean isLogin = users == null ? false : true;
        model.addAttribute("isLogin", isLogin);
        if(!isLogin){
            return "redirect:/account/dang-nhap";
        }
        PageDTO<List<BillDTO>> pageDTO = billService.getSellAtStore(new PageRequestDTO(0, 5 ));
        BillDTO bill = idBill == null ? (pageDTO.getData().size() > 0 ? pageDTO.getData().get(0) : null) :
                pageDTO.getData().stream().filter(billDTO -> billDTO.getId().equals(idBill)).findFirst().orElse(null);
        model.addAttribute("listBill",pageDTO);
        model.addAttribute("bill",bill);
        PageDTO<List<BillDetailDTO>> pageDetailDto = bill == null ? new PageDTO<>() : billDetailService.getByBillId(bill.getId(),new PageRequestDTO(pageDetail, 5));
        model.addAttribute("sizeBill",pageDTO.getData().size());
        model.addAttribute("list",pageDetailDto);
        return "TaiQuay/TaiQuay";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute BillDTO billDTO){
        Users users = (Users) session.getAttribute("user");
        if(users == null){
            return "redirect:/account/dang-nhap";
        }
        billDTO.setStaff(users.getFullName());
        Long idBill = billService.createAtStore(billDTO).getId();
        return "redirect:/tai-quay?idBill="+idBill;
    }

    @GetMapping("/huy/{idBill}")
    public String huy(@PathVariable("idBill") Long id){
        billService.cancelBill(id);
        return "redirect:/tai-quay";
    }

    @GetMapping("/deleteCart/{idBillDetail}/{idBill}")
    public String huyBillDetail(@PathVariable("idBillDetail") Long idBillDetail,
                                @PathVariable("idBill") Long idBill){
        billDetailService.delete(idBillDetail);
        return "redirect:/tai-quay?idBill="+ idBill;
    }

    @PostMapping("/atStore/{id}")
    public String atStore(@ModelAttribute BillDTO billDTO,
                                     @PathVariable Long id) {
        billDTO.setId(id);
        billService.updateSellAtStoreFinal(billDTO);
        return "redirect:/tai-quay";
    }

    @GetMapping("/getFullName/{sdt}")
    @ResponseBody
    public String getFullName(@PathVariable("sdt") String sdt){
        Users user = userService.findByPhoneNumber(sdt);
        if(user == null){
            return "";
        }else{
            return user.getFullName();
        }
    }
}
