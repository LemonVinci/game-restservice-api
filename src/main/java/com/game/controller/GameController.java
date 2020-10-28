package com.game.controller;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.model.Contacto;
import com.game.service.ContactoService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
public class GameController {

    @Autowired
    private ContactoService service;

    @PostMapping("/addContacto")
    public Contacto addContacto(@RequestParam Contacto Contacto) {
        return service.saveContacto(Contacto);
    }

    @PostMapping("/addContactos")
    public List<Contacto> addContactos(@RequestBody List<Contacto> Contactos) {
        return service.saveContactos(Contactos);
    }

    @GetMapping("/Contacto")
    public List<Contacto> findAllContactos() {
        return service.getContactos();
    }

    @GetMapping("/ContactoById/{id}")
    public Contacto findContactoById(@PathVariable int id) {
        return service.getContactoById(id);
    }

    @GetMapping("/Contacto/{name}")
    public Contacto findContactoByName(@PathVariable String name) {
        return service.getContactoByName(name);
    }

    @PutMapping("/update")
    public Contacto updateContacto(@RequestBody Contacto Contacto) {
        return service.updateContacto(Contacto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteContacto(@PathVariable int id) {
        return service.deleteContacto(id);
    }
}
