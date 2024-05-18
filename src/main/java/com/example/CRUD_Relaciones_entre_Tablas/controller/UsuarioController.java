package com.example.CRUD_Relaciones_entre_Tablas.controller;


import com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario.Usuario;
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

    @GetMapping ("/usuarios")
    public String listarUsuarios(Model modelo){
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        modelo.addAttribute("listaUsuarios",listaUsuarios);
        return "usuarios";
        //El HTML debe mostrar los id, emails y los roles
    }


}
