package com.dihaw.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dihaw.controller.exception.UserNotFoundException;
import com.dihaw.dto.ResponseDTO;
import com.dihaw.dto.ResponseStatusType;
import com.dihaw.entity.City;
import com.dihaw.entity.Gender;
import com.dihaw.entity.User;
import com.dihaw.services.CityService;
import com.dihaw.services.UserService;
import com.dihaw.validators.ValidatorUserEntry;

@Controller
@RequestMapping(UserController.CONTROLLER_BASE_PATH)
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public final static String CONTROLLER_BASE_PATH = "/users";
	public final static String SELECT_USERS_SUB_PATH = "/list";
	
	public static final String USER_FORM_ATTRIBUTE = "user";
	public static final String RESULT_ATTRIBUTE = "result";
	public static final String CITY_MODEL_ATTRIBUTE = "cityList";
	public static final String GENDER_MODEL_ATTRIBUTE = "genderList";
	public static final String USER_STATUS_MODEL_ATTRIBUTE = "statusList";
	public static final String USER_ROLES_MODEL_ATTRIBUTE = "roleList";
	
	private static String ERROR_MESSAGE = "errorMessage";
	private static String RESPONSE_STATUS = "status";
	
	private static String ADD_VIEW 	= "view/users/add";
	private static String LIST_VIEW = "view/users/list";
	private static String EDIT_VIEW = "view/users/edit";
	private static String EDIT_STAUTS_VIEW = "view/users/editStatus";
	private static String ERROR_VIEW= "error/generic_error";
	
	@Autowired
	private ValidatorUserEntry validatorUserEntry;

	@Autowired
	UserService userService;
	
	@Autowired
	CityService cityService;
	
	@ModelAttribute(USER_FORM_ATTRIBUTE)
	public User registerUSerRequestForm() {
		return new User();
	}
	
	@ModelAttribute(GENDER_MODEL_ATTRIBUTE)
	public List<String> genderModelAttribute() {
		
		List<String> genderList = new ArrayList<String>();
		genderList.add(Gender.Female.value());
		genderList.add(Gender.Male.value());

		return genderList;
	}
	
	@ModelAttribute(USER_ROLES_MODEL_ATTRIBUTE)
	public List<Integer> rolesModelAttribute(){
		
		List<Integer> roles = new ArrayList<Integer>();
		roles.add(1);
		roles.add(2);
		
		return roles;
	}
	
	@ModelAttribute(CITY_MODEL_ATTRIBUTE)
	public List<City> CityModelAttribute() {
		
		return cityService.getAll();
	}

	@RequestMapping(value="/list", method = RequestMethod.GET)
	  public String showUsers(Model model,
	            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
	            @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
		
		logger.info("---------- Showing Users view");
		
		PageRequest pageable = new PageRequest(page, size, Direction.ASC, "id");
		
	    model.addAttribute(RESULT_ATTRIBUTE, userService.users(pageable));
	    
	    return LIST_VIEW;
	  }

	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String registerUser(Model model, ModelMap modelMap,
			@ModelAttribute(GENDER_MODEL_ATTRIBUTE) List<String> genderList,
			@ModelAttribute(USER_STATUS_MODEL_ATTRIBUTE) List<String> userSatusList,
			@ModelAttribute(CITY_MODEL_ATTRIBUTE) List<String> cityList,
			@ModelAttribute(USER_ROLES_MODEL_ATTRIBUTE) List<Integer> userRoles,
			@ModelAttribute(USER_FORM_ATTRIBUTE) User user, BindingResult bindingResult) {
		
		logger.info("---------- Showing add user view");
		
		Errors errors = (Errors) modelMap.get("addErrors");
		
		if (errors != null) {
			
			logger.info("---------- addErrors != null");
			
			bindingResult.addAllErrors(errors);
		}

		model.addAttribute(USER_FORM_ATTRIBUTE, new User());
		model.addAttribute(GENDER_MODEL_ATTRIBUTE, genderList);
		model.addAttribute(USER_STATUS_MODEL_ATTRIBUTE, userSatusList);
		model.addAttribute(USER_ROLES_MODEL_ATTRIBUTE, userRoles);
		model.addAttribute(CITY_MODEL_ATTRIBUTE, cityList);
		
		return ADD_VIEW;
		
	}

	@RequestMapping(value = "/do-add", method = RequestMethod.POST)
	public String addUser(Model model,
			@ModelAttribute(GENDER_MODEL_ATTRIBUTE) List<String> genderList,
			@ModelAttribute(USER_STATUS_MODEL_ATTRIBUTE) List<String> userSatusList,
			@ModelAttribute(CITY_MODEL_ATTRIBUTE) List<String> cityList,
			@ModelAttribute(USER_FORM_ATTRIBUTE) User user, 
			BindingResult bindingResult ) {
		
		validatorUserEntry.validate(user, bindingResult);
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("addErrors", bindingResult);
			
			model.addAttribute(USER_STATUS_MODEL_ATTRIBUTE, userSatusList);
			model.addAttribute(GENDER_MODEL_ATTRIBUTE, genderList);
			model.addAttribute(CITY_MODEL_ATTRIBUTE, cityList);
		}
		
		else{
			
			ResponseDTO response = userService.registerUser(user);
			
			model.addAttribute(RESPONSE_STATUS, response.getStatus().value());
			
			if(response.getStatus().value().equals(ResponseStatusType.SUCCESS.value())){
				
				model.addAttribute(USER_FORM_ATTRIBUTE, new User());
				
			}

		}
		
		return ADD_VIEW;
	}
	
	@RequestMapping(value="/editStatus", method = RequestMethod.GET)
	public String editUserStatus(Model model, ModelMap modelMap, @RequestParam String id,
			@ModelAttribute(USER_FORM_ATTRIBUTE) User user) throws UserNotFoundException{
		
		logger.info("---------- Showing edit user view");
		
		try{
			user = userService.getUserById(id);
		}catch(UserNotFoundException e){
			
			model.addAttribute(ERROR_MESSAGE, e.getMessage());
			
			return ERROR_VIEW;
		}
		
		model.addAttribute(USER_FORM_ATTRIBUTE, user);
		
		return EDIT_STAUTS_VIEW;

	}

	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String editUser(Model model, ModelMap modelMap,
			@ModelAttribute(GENDER_MODEL_ATTRIBUTE) List<String> genderList,
			@ModelAttribute(USER_STATUS_MODEL_ATTRIBUTE) List<String> userSatusList,
			@ModelAttribute(USER_ROLES_MODEL_ATTRIBUTE) List<String> userRoles,
			@ModelAttribute(CITY_MODEL_ATTRIBUTE) List<City> cityList,
			@RequestParam String id,
			@ModelAttribute(USER_FORM_ATTRIBUTE) User user,
			BindingResult bindingResult) throws UserNotFoundException{
		
		logger.info("---------- Showing edit user view");
		
		Errors errors = (Errors) modelMap.get("editErrors");
		
		if (errors != null) {
			bindingResult.addAllErrors(errors);
		}
		
		try{
			user = userService.getUserById(id);
		}catch(UserNotFoundException e){
			
			model.addAttribute(ERROR_MESSAGE, e.getMessage());
			
			return ERROR_VIEW;
		}
		
		model.addAttribute(USER_FORM_ATTRIBUTE, user);
		model.addAttribute(USER_STATUS_MODEL_ATTRIBUTE, userSatusList);
		model.addAttribute(GENDER_MODEL_ATTRIBUTE, genderList);
		model.addAttribute(USER_ROLES_MODEL_ATTRIBUTE, userRoles);
		model.addAttribute(CITY_MODEL_ATTRIBUTE, cityList);
		
		return EDIT_VIEW;

	}

	@RequestMapping(value="/do-edit", method = RequestMethod.POST)
	public String updateUser(Model model,
			@ModelAttribute(USER_FORM_ATTRIBUTE) User user,
			BindingResult bindingResult ) throws UserNotFoundException{
		
		logger.info("---------- RequestMapping: /do-edit");
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("editErrors", bindingResult);
			
			model.addAttribute(USER_FORM_ATTRIBUTE, user);
			
			return EDIT_VIEW;
		}
		else{
			
			userService.updateUser(user);
			
			StringBuilder redirectPath = new StringBuilder();
			redirectPath.append("redirect:" + CONTROLLER_BASE_PATH + SELECT_USERS_SUB_PATH );
			return redirectPath.toString();
			
			
		}
		
	}

	@RequestMapping("/delete")
	public String deleteUser(Model model, @RequestParam String id) {
		
		logger.info("---------- RequestMapping: /delete");
		
		try {
			userService.deleteUser(id);
		} catch (UserNotFoundException e) {
			
			model.addAttribute(ERROR_MESSAGE, e.getMessage());
			
			return ERROR_VIEW;
		}
		
		return "redirect:/users/list";
	}
	

}