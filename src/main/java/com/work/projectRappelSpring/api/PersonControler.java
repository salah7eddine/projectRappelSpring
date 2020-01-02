package com.work.projectRappelSpring.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import com.work.projectRappelSpring.model.Person;
import com.work.projectRappelSpring.service.PersonService;

@RequestMapping("api/v1/person")
@RestController
public class PersonControler {
	private final PersonService personService;

	@Autowired
	public PersonControler(PersonService personService) {
		super();
		this.personService = personService;
	}
	
	@PostMapping
	public void addPerson(@Valid @NonNull @RequestBody Person person) {
		personService.addPerson(person);
	}
	
	@GetMapping
	public List<Person> getAllPeople() {
		return personService.getAllPeople();
	}
	
	@GetMapping(path = "/{id}")
	public Person getPeronById(@PathVariable("id") UUID id) {
		return personService.getPersonById(id).orElse(null);
	}

	@DeleteMapping(path="/{id}")
	public void deletePersonById(@PathVariable("id") UUID id) {
		personService.deletePerson(id);
	}

	@PutMapping(path = "/{id}")
	public void updatePerson(@PathVariable("id") UUID id,@Valid @NonNull @RequestBody Person personToUpdate){
		System.out.println("************************");
		System.out.println(personToUpdate.getName());
		personService.updatePerson(id, personToUpdate);
	}
}