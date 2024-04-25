package com.example.CRUD_Relaciones_entre_Tablas.controller;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Marca.Marca;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Producto.Producto;
import com.example.CRUD_Relaciones_entre_Tablas.repository.CategoriaRepository;
import com.example.CRUD_Relaciones_entre_Tablas.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MarcaController {
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/marcas")
    public String listarMarcas(Model modelo){
        List<Marca> listaMarcas= marcaRepository.findAll();
        modelo.addAttribute("listaMarcas",listaMarcas);
        return "marcas";
    }


    @GetMapping("/marcas/nuevo")
    public String nuevaMarca(Model modelo){
        List<Categoria> listaCategorias = categoriaRepository.findAll();

        modelo.addAttribute("listaCategorias",listaCategorias);
        modelo.addAttribute("marca",new Marca());
        return "marca_formulario";
    }


    @GetMapping("/marcas/editar/{id}")
    public String editarMarca(@PathVariable("id") Integer id, Model modelo){
        List<Categoria>listaCategorias = categoriaRepository.findAll();
        Marca marcaBD = marcaRepository.findById(id).get();


        modelo.addAttribute("marca",marcaBD);
        modelo.addAttribute("listaCategorias",listaCategorias);
        return "marca_formulario";
    }

    @PostMapping("/marcas/guardar")
    public String guardarMarca(Marca marca){
        if (marca.getId()!=null){
            /*Formulario de edición*/
            Marca marcaDB= marcaRepository.findById(marca.getId()).get();

            marcaDB.setNombre(marca.getNombre());
            marcaDB.setCategorias(marca.getCategorias());

            marcaRepository.save(marcaDB);
        }
        else{
            marcaRepository.save(marca);
        }
        return "redirect:/marcas";
    }

    @GetMapping("/marcas/borrar/{id}")
    public String borrarMarca(@PathVariable Integer id){
        marcaRepository.deleteById(id);
        return "redirect:/marcas";
    }

}
