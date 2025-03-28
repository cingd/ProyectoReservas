package com.reservas.app.controller;

import com.reservas.app.model.Usuario;
import com.reservas.app.service.UsuarioService;
import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<model.Usuario> registrarUsuario(@RequestBody model.Usuario usuario) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<model.Usuario> autenticarUsuario(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(usuarioService.autenticarUsuario(email, password));
    }
}