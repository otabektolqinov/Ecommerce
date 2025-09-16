package com.company.ecommerce.mvc;

import com.company.ecommerce.dto.request.AddressRequestDto;
import com.company.ecommerce.dto.response.AddressResponseDto;
import com.company.ecommerce.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/addresses")
@RequiredArgsConstructor
public class AddressMvcController {

    private final AddressService addressService;

    @GetMapping("/get-all")
    public String getAll(Model model){
        List<AddressResponseDto> content = addressService.getAll().getContent();
        model.addAttribute("addresses", content);
        return "address/addresses :: content";
    }

    @GetMapping("/create")
    public String createPage(){
        return "address/address-form :: form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AddressRequestDto dto, Model model){
        addressService.createAddress(dto);
        List<AddressResponseDto> content = addressService.getAll().getContent();
        model.addAttribute("addresses", content);
        return "address/addresses :: content";
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable Long id){
        AddressResponseDto content = addressService.getAddressById(id).getContent();
        model.addAttribute("address", content);
        return "address/address-edit :: form";
    }

    @PostMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, @ModelAttribute AddressRequestDto dto){
        addressService.updateAddressById(id, dto);
        List<AddressResponseDto> content = addressService.getAll().getContent();
        model.addAttribute("addresses", content);
        return "address/addresses :: content";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        addressService.deleteAddressById(id);
        return ResponseEntity.ok().build();
    }
}
