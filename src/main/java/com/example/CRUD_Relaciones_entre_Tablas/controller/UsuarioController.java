package com.example.CRUD_Relaciones_entre_Tablas.controller;


import com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario.Rol;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario.Usuario;
import com.example.CRUD_Relaciones_entre_Tablas.repository.RolRepository;
import com.example.CRUD_Relaciones_entre_Tablas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @GetMapping ("/usuarios")
    public String listarUsuarios(Model modelo){
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        modelo.addAttribute("listaUsuarios",listaUsuarios);
        return "usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String mostrarformularioDeRegistroDeUsuario(Model modelo){
        List<Rol> listaRoles = rolRepository.findAll();

        modelo.addAttribute("listaRoles",listaRoles);
        modelo.addAttribute("usuario", new Usuario());

        return "usuario_formulario";
    }




}
