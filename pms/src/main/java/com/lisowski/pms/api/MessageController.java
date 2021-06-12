package com.lisowski.pms.api;


import com.lisowski.pms.payload.MessageRequestDTO;
import com.lisowski.pms.services.servicesImpl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class MessageController {

    private final MessageServiceImpl messageService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/message")
    public ResponseEntity<?> addMessage(@Valid @RequestBody MessageRequestDTO request) {
        messageService.addMessage(request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/messages")
    public ResponseEntity<?> getMessages(){
        return ResponseEntity.ok(messageService.getAllMessages());
    }

}
