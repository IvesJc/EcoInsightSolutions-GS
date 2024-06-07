package org.ehap.service;

import org.ehap.exceptions.WrongPasswordException;
import org.ehap.models.Usuario;
import org.ehap.repositories.UsuarioRepository;

public class UsuarioService {

    UsuarioRepository usuarioRepository = new UsuarioRepository();

    public boolean validarLoginSenha(String login, String senha) {
        Usuario user = usuarioRepository.getUsuarioByUsername(login);
        try {

            if (user.getNomeUsuario().equals(login) && user.getSenha().equals(senha)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new WrongPasswordException("Senha inválida!");
        }
    }
}
