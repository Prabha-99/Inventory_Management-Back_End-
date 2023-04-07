package com.example.monara_backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/System_Admin")
    @PreAuthorize("hasRole('System_Admin')")
    public String System_AdminAccess() {
        return "System_Admin Content.";
    }

    @GetMapping("/Inventory_Admin")
    @PreAuthorize("hasRole('Inventory_Admin')")
    public String Inventory_AdminAccess() {
        return "Inventory_Admin Board.";
    }

    @GetMapping("/Store_Manager")
    @PreAuthorize("hasRole('Store_Manager')")
    public String Store_ManagerAccess() {
        return "Store_Manager Board.";
    }
    @GetMapping("/Purchase_Coordinator")
    @PreAuthorize("hasRole('Purchase_Coordinator')")
    public String Purchase_CoordinatorAccess() {
        return "Purchase_Coordinator Board.";
    }

    @GetMapping("/Storekeeper")
    @PreAuthorize("hasRole('Storekeeper')")
    public String StorekeeperAccess() {
        return "Storekeeper Board.";
    }

    @GetMapping("/Designer")
    @PreAuthorize("hasRole('Designer')")
    public String DesignerAccess() {
        return "Designer Board.";
    }

    @GetMapping("/ShowRoom_Manager")
    @PreAuthorize("hasRole('ShowRoom_Manager')")
    public String ShowRoom_ManagerAccess() {
        return "ShowRoom_Manager Board.";
    }

}
