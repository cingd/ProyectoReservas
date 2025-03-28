package controller;

import model.Usuario;
import service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controlador dedicado para autenticación y registro de usuarios.
@RestController
@RequestMapping(AuthController.BASE_PATH)
public class AuthController {

    public static final String BASE_PATH = "/api"; // Ruta base

    private final UsuarioService usuarioService;

    // Inyección de dependencia por constructor
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Registro de nuevos usuarios.
     *
     * @param usuario Datos del usuario a registrar.
     * @return Respuesta indicando éxito o error.
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Autenticación de usuarios.
     *
     * @param email    Email del usuario.
     * @param password Contraseña del usuario.
     * @return Respuesta con los datos del usuario autenticado o error.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            Usuario usuarioAutenticado = usuarioService.autenticarUsuario(email, password);
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}