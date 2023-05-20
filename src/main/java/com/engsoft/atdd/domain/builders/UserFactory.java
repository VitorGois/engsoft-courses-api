package com.engsoft.atdd.domain.builders;

import com.engsoft.atdd.domain.models.User;
import com.engsoft.atdd.domain.models.valueobjects.Email;
import com.engsoft.atdd.domain.models.valueobjects.Password;
import com.engsoft.atdd.domain.models.valueobjects.TaxId;
import com.engsoft.atdd.infraestructure.controllers.dtos.SignupUserCreateDto;

public class UserFactory {

	private User user;
	
    public UserFactory withSignupParams(SignupUserCreateDto params) {
        Email emailObj = Email.fromString(params.getEmail());
        TaxId taxIdObj = TaxId.fromString(params.getTaxId());
        Password passwordObj = Password.fromString(params.getPassword());

        this.user = new User(params.getName(), emailObj, taxIdObj, passwordObj);    
        return this;
    }
    
    public User build() {
    	return this.user;
    }
	
}
