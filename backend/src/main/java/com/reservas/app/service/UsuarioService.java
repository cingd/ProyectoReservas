package service;

import model.Usuario;
import repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticarUsuario(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        throw new IllegalArgumentException("Correo electrónico o contraseña incorrectos");
    }
}