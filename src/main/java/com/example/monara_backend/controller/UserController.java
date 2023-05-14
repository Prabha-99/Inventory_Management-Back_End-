package com.example.monara_backend.controller;

import com.example.monara_backend.Configuration.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>adminWelcome(){
        return ResponseEntity.ok("Admin Board");
    }
    @GetMapping("/inventory_admin")
    @PreAuthorize("hasRole('INVENTORY_ADMIN')")
    public ResponseEntity<String> invAdminWelcome(){
        return ResponseEntity.ok("Inventory Admin Board");
    }
    @GetMapping("/stock_manager")
    @PreAuthorize("hasRole('STOCK_MANAGER')")
    public ResponseEntity<String> stManagerWelcome(){
        return ResponseEntity.ok("Stock Manager Board");
    }
    @GetMapping("/purchase_coordinator")
    @PreAuthorize("hasRole('PURCHASE_COORDINATOR')")
    public ResponseEntity<String> purCorWelcome(){
        return ResponseEntity.ok("Purchase Coordinator Board");
    }
    @GetMapping("/stock_keeper")
    @PreAuthorize("hasRole('STOCK_KEEPER')")
    public ResponseEntity<String> stKeeperWelcome(){
        return ResponseEntity.ok("Stock Keeper Board");
    }
    @GetMapping("/designer")
    @PreAuthorize("hasRole('DESIGNER')")
    public ResponseEntity<String> designerWelcome(){
        return ResponseEntity.ok("Designer Board");
    }
    @GetMapping("/showroom_manager")
    @PreAuthorize("hasRole('SHOWROOM_MANAGER')")
    public ResponseEntity<String> shRoomWelcome(){
        return ResponseEntity.ok("Showroom Manager Board");
    }


}
