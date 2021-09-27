/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author shenlong
 * @version ValidationDemo.java, v 0.1 2021��09��09�� 8:46 ���� shenlong
 */
public class ValidationDemo {

    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        User user = new User();
        user.setId(3);
        user.setMail("123123213qq@2.com");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        constraintViolations.forEach(userConstraintViolation -> {
            System.out.println(userConstraintViolation.getMessage());
        });
    }
}