package com.msg.after_school.domain.auth.utils;

import com.msg.after_school.domain.auth.exception.NotFoundInGSMException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class GSMProvider {
    @Value("classpath:/students.json")
    private Resource resource;

    private JSONArray users;

    public void setupUsers() throws Exception {
        Object ob = new JSONParser().parse(new InputStreamReader(resource.getInputStream()));
        this.users = (JSONArray) ob;
    }

    public JSONObject findGSMUser(String email) {
        try {
            return (JSONObject) users.stream().filter(e -> ((JSONObject) e).get("email").equals(email))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundInGSMException());
        } catch (Throwable e) {
            throw new NotFoundInGSMException();
        }
    }
}
