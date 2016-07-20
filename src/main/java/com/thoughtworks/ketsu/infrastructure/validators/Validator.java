package com.thoughtworks.ketsu.infrastructure.validators;

import java.util.Map;

public interface Validator {
    public boolean validate(Map<String, Object> infos);
}
