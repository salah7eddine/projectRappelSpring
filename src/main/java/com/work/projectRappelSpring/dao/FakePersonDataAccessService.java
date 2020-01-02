package com.work.projectRappelSpring.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.work.projectRappelSpring.model.Person;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

	private static List<Person> DB = new ArrayList<>();
	
	@Override
	public int insertPerson(UUID id, Person person) {
		// TODO Auto-generated method stub
		DB.add(new Person(id, person.getName()));
		return 1;
	}

	@Override
	public List<Person> selectAllPeople() {
		// TODO Auto-generated method stub
		return DB;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		// TODO Auto-generated method stub
		return DB.stream()
				.filter(person -> person.getId().equals(id)).findFirst();
	}

	@Override
	public int deletePersonById(UUID id) {
		// TODO Auto-generated method stub
		Optional<Person> personMaybe = selectPersonById(id);

		if(personMaybe.empty() != null) {
			return 0;
		}
		DB.remove(personMaybe.get());
		return 1;
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		// TODO Auto-generated method stub
		return selectPersonById(id).map(p -> {
			int indexOfPersonToUpdate = DB.indexOf(p);
			if(indexOfPersonToUpdate >= 0) {
				DB.set(indexOfPersonToUpdate, new Person(id, person.getName()));
				return 1;
			}
			return 0;
		}).orElse(0);
	}
}
