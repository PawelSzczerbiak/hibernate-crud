package pl.szczerbiak.hibernateForm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.szczerbiak.hibernateForm.model.Person;
import pl.szczerbiak.hibernateForm.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    // Searching

    @GetMapping("/people/find")
    public String findPeople() {
        return "find";
    }

    @PostMapping("/people/found")
    public String showFoundPeople(@RequestParam String type, @RequestParam String value
            , ModelMap modelMap) {

        List<Person> people = new ArrayList<>();
        // Better way?
        List<Person> allPeople = (List<Person>) personRepository.findAll();
        if(type.equals("name")){
            people = allPeople.stream()
                    .filter( p -> p.getName().contains(value))
                    .collect(Collectors.toList());
        }else{
            people = allPeople.stream()
                    .filter( p -> p.getSurname().contains(value))
                    .collect(Collectors.toList());
        }
        modelMap.addAttribute("people", people);
        return "found";
    }

}
