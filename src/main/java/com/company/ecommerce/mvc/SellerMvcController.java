package com.company.ecommerce.mvc;

import com.company.ecommerce.dto.request.SellerRequestDto;
import com.company.ecommerce.dto.response.SellerResponseDto;
import com.company.ecommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sellers")
@RequiredArgsConstructor
public class SellerMvcController {

    private final SellerService sellerService;

    @GetMapping("/get-all")
    public String getAll(Model model){
        List<SellerResponseDto> content = sellerService.getAll().getContent();
        model.addAttribute("sellers", content);
        return "sellers/sellers :: content";
    }

    @GetMapping("/create")
    public String createPage(){
        return "sellers/seller-form :: form";
    }

    @PostMapping("/create")
    public String createSeller(@ModelAttribute SellerRequestDto dto,
                               Model model
    ){
        sellerService.createSeller(dto);
        List<SellerResponseDto> content = sellerService.getAll().getContent();
        model.addAttribute("sellers", content);
        return "sellers/sellers :: content";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        SellerResponseDto content = sellerService.getSellerById(id).getContent();
        model.addAttribute("seller", content);
        return "sellers/seller-edit :: form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute SellerRequestDto dto,
                         Model model
    ){
        sellerService.updateSellerById(dto, id);
        List<SellerResponseDto> content = sellerService.getAll().getContent();
        model.addAttribute("sellers", content);
        return "sellers/sellers :: content";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        sellerService.deleteSellerById(id);
        return ResponseEntity.ok().build();
    }
}
