package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Usuario;
import com.utdev.chilloutserver.repository.UsuarioRepository;
import com.utdev.chilloutserver.service.interfaces.IUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    //Save full User
    @Override
    public Usuario saveUser(Usuario user){
        return repository.save(user);
    }

    // Get all users
    @Override
    public List<Usuario> findAll(){
        return repository.findAll();
    }

    // Get User by id
    @Override
    public Usuario findById(String id){
        return repository.findById(id).orElse(null);
    }

    // Get User by username
    @Override
    public Usuario findByUsername(String username){
        Usuario user = repository.findFirstByUsernameIgnoreCase(username);
        if(user != null)
            return user;
        return null;
    }

    // Inactive User
    @Override
    public boolean inactiveUserById(String id){
        Usuario user = repository.findById(id).orElse(null);
        if(user == null)
            return false;
        user.setActive(false);
        if(this.saveUser(user) == null)
            return false;
        return true;
    }

    // Delete User
    @Override
    public void deleteUser(String id){
        repository.deleteById(id);
    }

}
