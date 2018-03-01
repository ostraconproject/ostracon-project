package ostracon.ostracon_project.power_plants;

import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("powerPlantValidator")
public class PowerPlantValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}
	
	@Override
	public void validate(Object target, Errors errors) 
	{
		NewPowerPlantForm plant = (NewPowerPlantForm) target;
		
		if (StringUtil.isEmpty(plant.getName()))
			errors.rejectValue("name", "name.message");
			
	}
}
