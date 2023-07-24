package com.example.monara_backend.controller;


import com.example.monara_backend.Configuration.AuthenticationRequest;
import com.example.monara_backend.dto.navBarLogin;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.Role;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.UserRepo;
import com.example.monara_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

      private final UserRepo userRepo;

      @Autowired
      private UserService userService;

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

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Hey Man..Finally Hahh");
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(){
        return ResponseEntity.ok("Are you happy now..???");
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Integer id , @RequestBody User profile){
        return new ResponseEntity<User>(userService.updateProfile(id , profile) , HttpStatus.OK);
    }

}
