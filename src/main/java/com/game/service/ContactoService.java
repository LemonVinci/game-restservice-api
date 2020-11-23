package com.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.model.Contacto;
import com.game.repository.ContactoRepository;

@Service
public class ContactoService {
	@Autowired
    private ContactoRepository repository;

    public Contacto saveContacto(Contacto product) {
        return repository.save(product);
    }

    public List<Contacto> saveContactos(List<Contacto> contactos) {
        return repository.saveAll(contactos);
    }

    public List<Contacto> getContactos() {
        return repository.findAll();
    }

    public Contacto getContactoById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Contacto getContactoByName(String name) {
        return repository.findByName(name);
    }

    public String deleteContacto(int id) {
        repository.deleteById(id);
        return "contacto removed !! " + id;
    }

    public Contacto updateContacto(Contacto contacto) {
    	
    	Contacto existingContacto = repository.findById(contacto.getId()).orElse(null);
    	existingContacto.setName(contacto.getName());
    	existingContacto.setEmail(contacto.getEmail().toLowerCase());
    	existingContacto.setTelefono(contacto.getTelefono());
        return repository.save(existingContacto);
    }
}
