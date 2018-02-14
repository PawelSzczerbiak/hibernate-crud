package pl.szczerbiak.hibernateForm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.szczerbiak.hibernateForm.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
