package com.example.monara_backend.controller;

import com.example.monara_backend.model.ERole;
import com.example.monara_backend.model.Role;
import com.example.monara_backend.model.User;
import com.example.monara_backend.payload.request.LoginRequest;
import com.example.monara_backend.payload.request.SignupRequest;
import com.example.monara_backend.payload.response.JwtResponse;
import com.example.monara_backend.payload.response.MessageResponse;
import com.example.monara_backend.repository.RoleRepository;
import com.example.monara_backend.repository.UserRepository;
import com.example.monara_backend.security.jwt.JwtUtils;
import com.example.monara_backend.security.service.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.System_Admin)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "Inventory_Admin":
                        Role inventoryadminRole = roleRepository.findByName(ERole.Inventory_Admin)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(inventoryadminRole);

                        break;
                    case "Store_Manager":
                        Role storemanagerRole = roleRepository.findByName(ERole.Store_Manager)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(storemanagerRole);

                        break;
                    case "Purchase_Coordinator":
                        Role purchasecoordinatorRole = roleRepository.findByName(ERole.Purchase_Coordinator)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(purchasecoordinatorRole);

                        break;
                    case "Storekeeper":
                        Role storekeeperRole = roleRepository.findByName(ERole.Storekeeper)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(storekeeperRole);

                        break;
                    case "Designer":
                        Role designerRole = roleRepository.findByName(ERole.Designer)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(designerRole);

                        break;

                    case "ShowRoom_Manager":
                        Role showroommanagerRole = roleRepository.findByName(ERole.ShowRoom_Manager)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(showroommanagerRole);

                        break;
                    default:
                        Role systemadminRole = roleRepository.findByName(ERole.System_Admin)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(systemadminRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
