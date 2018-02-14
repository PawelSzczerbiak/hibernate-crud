package pl.szczerbiak.hibernateForm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.szczerbiak.hibernateForm.model.Person;
import pl.szczerbiak.hibernateForm.repository.PersonRepository;

// CRUD operations

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/people")
    public String displayAll(ModelMap modelMap) {
        modelMap.addAttribute("people", personRepository.findAll());
        return "show";
    }

    @GetMapping("/people/add")
    public String addPerson() {
        return "add";
    }

    @PostMapping("/people")
    public String savePerson(@ModelAttribute Person person) {
        personRepository.save(person);
        return "redirect:/people";
    }

    @GetMapping("/people/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return "redirect:/people";
    }

    /**
     * Actually, we would also use add.html template both for adding and editing
     * See: https://github.com/neocorp/spring-mvc-thymeleaf-crud
     */
    @GetMapping("/people/edit/{id}")
    public String editPerson(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("person", personRepository.findById(id).get());
        return "edit";
    }
}
