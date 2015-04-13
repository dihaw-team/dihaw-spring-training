package com.dihaw.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dihaw.entity.Person;
import com.dihaw.services.PersonsService;

@Service
public class PersonsServiceImpl implements PersonsService{
	
	public List<Person> createPersons() {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Wahid", "wahid.gazzah@gmail.com", 29));
		persons.add(new Person("Amine", "amin@mail.com", 28));
		persons.add(new Person("wael", "wael@mail.com", 29));
		persons.add(new Person("Mouhamed", "mouhamed@mail.com", 32));
		persons.add(new Person("Zakariya", "zak@mail.com", 33));
		persons.add(new Person("Salah", "salah@mail.com", 31));
		return persons;
	}

}
