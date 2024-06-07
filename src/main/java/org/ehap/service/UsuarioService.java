package org.ehap.service;

import org.ehap.exceptions.WrongPasswordException;
import org.ehap.models.Usuario;
import org.ehap.repositories.UsuarioRepository;

public class UsuarioService {

    UsuarioRepository usuarioRepository = new UsuarioRepository();

    public boolean validarLoginSenha(String login, String senha) {
        try {
            Usuario user = usuarioRepository.getUsuarioByUsername(login);
            return user != null && user.getNomeUsuario().equals(login) && user.getSenha().equals(senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
