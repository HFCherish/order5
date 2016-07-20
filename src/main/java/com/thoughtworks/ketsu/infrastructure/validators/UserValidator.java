package com.thoughtworks.ketsu.infrastructure.validators;

import java.util.Map;

public class UserValidator implements Validator {
    @Override
    public boolean validate(Map<String, Object> infos) {
        if (infos.get("name") == null || !infos.get("name").toString().matches("^[\\w\\W\\d]+$"))
            throw new IllegalArgumentException("must contain a name composed of letters and numbers.");
        return true;
    }
}
