package pl.szczerbiak.hibernateForm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Helper class that contains useful functions
public class PersonUtils {

    // Private constructor - none should ever instantiate this class
    private PersonUtils() {
    }

    // Searches people
    public static List<Person> searchPeople(List<Person> allPeople, String type, String value){
        List<Person> people = new ArrayList<>();
        if(type.equals("name")){
            people = allPeople.stream()
                    .filter( p -> p.getName().contains(value))
                    .collect(Collectors.toList());
        }else{
            people = allPeople.stream()
                    .filter( p -> p.getSurname().contains(value))
                    .collect(Collectors.toList());
        }
        return people;
    }
}
