package edu.dlsu.securdeproject.validation;

import com.google.common.base.Joiner;
import edu.dlsu.securdeproject.validation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Autowired
    private MessageSource messages;

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(
                Arrays.asList(new LengthRule(8, 30),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1),
                new NumericalSequenceRule(3,false),
                new AlphabeticalSequenceRule(3,false),
                new QwertySequenceRule(3,false),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messages.getMessage("message.passwordNotStrong", null, null))
                .addConstraintViolation();
        return false;
    }
}