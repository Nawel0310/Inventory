package com.example.CRUD_Relaciones_entre_Tablas.controller;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Marca.Marca;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Producto.Producto;
import com.example.CRUD_Relaciones_entre_Tablas.repository.CategoriaRepository;
import com.example.CRUD_Relaciones_entre_Tablas.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    //MEJORAR
    @GetMapping("/marcas/editar/{id}")
    public String editarMarca(@PathVariable("id") Integer id, Model modelo){
        List<Categoria>listaCategorias = categoriaRepository.findAll();
        Marca marcaBD = marcaRepository.findById(id).get();

        modelo.addAttribute("marca",marcaBD);
        modelo.addAttribute("listaCategorias",listaCategorias);
        return "marca_formulario";
    }

    @PostMapping("/marcas/guardar")
    public String guardarMarca(@ModelAttribute("marca") Marca marca, @RequestParam(value = "categorias", required = false) List<Integer>categoriasId) {
        if (marca.getId() != null) {
            /*Formulario de edición*/
            Marca marcaDB = marcaRepository.findById(marca.getId()).get();

            marcaDB.setNombre(marca.getNombre());
            if (categoriasId != null) {
                List<Categoria> categoriasElegidas = categoriaRepository.findAllById(categoriasId);
                for (Categoria categoria : categoriasElegidas) {
                    categoria.setMarca(marcaDB);
                    categoriaRepository.save(categoria);
                }
            }
            marcaRepository.save(marcaDB);
        } else {
            marcaRepository.save(marca);
            if (categoriasId != null) {
                List<Categoria> categoriasElegidas = categoriaRepository.findAllById(categoriasId);
                for (Categoria categoria : categoriasElegidas) {
                    categoria.setMarca(marca);
                    categoriaRepository.save(categoria);
                }
            }
        }
        return "redirect:/marcas";
    }

    //MEJORAR
    @GetMapping("/marcas/borrar/{id}")
    public String borrarMarca(@PathVariable Integer id){
        marcaRepository.deleteById(id);
        return "redirect:/marcas";
    }

}
