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

    @GetMapping("/marcas/editar/{id}")
    public String editarMarca(@PathVariable("id") Integer id, Model modelo){
        Marca marcaDB = marcaRepository.findById(id).get();

        //Obtenemos la lista de categorias
        List<Categoria> listaCategorias = categoriaRepository.findAll();

        modelo.addAttribute("marca",marcaDB);
        modelo.addAttribute("listaCategorias",listaCategorias);
        return "marca_formulario";
    }

    @PostMapping("/marcas/guardar")
    public String guardarMarca(@ModelAttribute("marca") Marca marca, @RequestParam(value = "categorias", required = false) List<Integer>categoriasId) {
        if (marca.getId() != null) {
            Marca marcaDB = marcaRepository.findById(marca.getId()).get();

            marcaDB.setNombre(marca.getNombre());

            // Eliminar las categorías que ya no están seleccionadas
            List<Categoria> categoriasActuales = marcaDB.getCategorias();
            if (categoriasActuales != null) {
                for (Categoria categoria : categoriasActuales) {
                    if (categoriasId == null || !categoriasId.contains(categoria.getId())) {
                        categoria.setMarca(null); // Desvincular la categoría de la marca
                        categoriaRepository.save(categoria);
                    }
                }
            }

            // Agregar las nuevas categorías seleccionadas
            if (categoriasId != null) {
                List<Categoria> categoriasNuevas = categoriaRepository.findAllById(categoriasId);
                for (Categoria categoria : categoriasNuevas) {
                    categoria.setMarca(marcaDB);
                    categoriaRepository.save(categoria);
                }
            }

            marcaRepository.save(marcaDB);
        } else {
            /*Formulario Nueva Marca:*/
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


    @GetMapping("/marcas/borrar/{id}")
    public String borrarMarca(@PathVariable Integer id){
        Marca marcaDB = marcaRepository.findById(id).get();

        //Debemos desvincular todas las posibles categorias
       List<Categoria> categorias = categoriaRepository.findByMarca(marcaDB);
       for (Categoria categoria : categorias){
           categoria.setMarca(null);
           categoriaRepository.save(categoria);
       }
       marcaRepository.delete(marcaDB);
        return "redirect:/marcas";
    }

}
