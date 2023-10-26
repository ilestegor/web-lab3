package com.ilestegor.lab3.validator;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("validatorY")
public class ValidatorY implements Validator<Double> {
    private static final double Y_MAX = 3.0;
    private static final double Y_MIN = -3.0;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double yValue) throws ValidatorException {

        if (yValue == null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "The Y field cannot be empty and must be int!"));
        }
        if (yValue < Y_MIN || yValue > Y_MAX) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Choose number from -3...3"));
        }

    }
}
