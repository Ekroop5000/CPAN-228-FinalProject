package com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.model.controller;

import com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.model.Item;
import com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.model.Brand;
import com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/add-item")
    public String showAddItemForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("brands", Brand.values());
        return "add-item";
    }

    @PostMapping("/add-item")
    public String addItem(@Valid @ModelAttribute("item") Item item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brands", Brand.values());
            return "add-item";
        }
        itemRepository.save(item);
        return "redirect:/list-items";
    }

    @GetMapping("/list-items")
    public String showItemList(
            @RequestParam(value = "brand", required = false) Brand brand,
            @RequestParam(value = "year", required = false) Integer year,
            @PageableDefault(sort = "brand", direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {

        Page<Item> itemsPage;
        
        if (brand != null && year != null) {
            itemsPage = itemRepository.findByBrandAndYearOfCreation(brand, year, pageable);
        } else if (brand != null) {
            itemsPage = itemRepository.findByBrand(brand, pageable);
        } else if (year != null) {
            itemsPage = itemRepository.findByYearOfCreation(year, pageable);
        } else {
            itemsPage = itemRepository.findAll(pageable);
        }

        model.addAttribute("items", itemsPage.getContent());
        model.addAttribute("brands", Brand.values());
        model.addAttribute("year", year);
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("page", itemsPage);

        return "list-items";
    }
}
