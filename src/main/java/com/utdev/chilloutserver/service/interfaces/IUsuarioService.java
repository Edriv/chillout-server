package com.utdev.chilloutserver.service.interfaces;

import com.utdev.chilloutserver.model.Usuario;
import com.utdev.chilloutserver.model.utils.LoginModel;

import java.util.List;

public interface IUsuarioService {
    //Save full User
    Usuario saveUser(Usuario user);

    // Autenticar Usuario
    Usuario loginUser(LoginModel login);

    // Get all users
    List<Usuario> findAll();

    // Get User by id
    Usuario findById(String id);

    // Get User by username
    Usuario findByUsername(String username);

    // Inactive User
    boolean inactiveUserById(String id);

    // Delete User
    void deleteUser(String id);
}
