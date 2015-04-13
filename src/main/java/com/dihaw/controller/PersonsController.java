package com.dihaw.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dihaw.entity.Person;
import com.dihaw.services.PersonsService;

@Controller
@SessionAttributes(PersonsController.PERSONS_ATTRIBUTE)
@RequestMapping(PersonsController.CONTROLLER_BASE_PATH)
public class PersonsController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public final static String CONTROLLER_BASE_PATH = "/persons";
	
	private final static String SELECT_PERSONS_PATH = "/list";
	private final static String REMOVE_PERSON_PATH = "/remove";
	
	private static String PERSONS_LIST_VIEW = "view/persons/list";
	protected static final String PERSONS_ATTRIBUTE = "persons";
	
	private static String ERROR_VIEW= "error";
	private static String ERROR_MESSAGE = "errorMessage";

	@Autowired
	PersonsService personService;
	
	@ModelAttribute(PERSONS_ATTRIBUTE)
	private List<Person> getPersons(){
		return personService.createPersons();
	}
	
	@RequestMapping(SELECT_PERSONS_PATH)
	public String viewPersons(Model model,
			@ModelAttribute(PERSONS_ATTRIBUTE) List<Person> persons) {
		
		model.addAttribute(PERSONS_ATTRIBUTE, persons);
		return PERSONS_LIST_VIEW;
	}
	
	
	@RequestMapping(REMOVE_PERSON_PATH)
	public String removePerson(Model model,	@RequestParam("index") int index,
			@ModelAttribute(PERSONS_ATTRIBUTE) List<Person> persons) {
		
		if(index <0){
			
			model.addAttribute(ERROR_MESSAGE, "person index <0 !");
			return ERROR_VIEW;
		}
		else if(index > persons.size()){
			
			model.addAttribute(ERROR_MESSAGE, "person index > persons size !");
			return ERROR_VIEW;
		}
		else{
			
			persons.remove(index);
			model.addAttribute(PERSONS_ATTRIBUTE, persons);
			return PERSONS_LIST_VIEW;
		}
	}
}
