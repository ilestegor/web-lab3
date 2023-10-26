package com.ilestegor.lab3.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("validatorR")
public class ValidatorR implements Validator<Double> {
    private static final double R_MAX = 3.0;
    private static final double R_MIN = 1.0;
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double aDouble) throws ValidatorException {

        if (aDouble == null){
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "R value cannot be empty and must be int"));
        }

        if (aDouble > R_MAX || aDouble < R_MIN){
            System.out.println(aDouble);
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "R value is out of range"));
        }
    }
}
