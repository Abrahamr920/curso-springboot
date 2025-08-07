package com.abraham.curso.springboot.webapp.springbootweb.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abraham.curso.springboot.webapp.springbootweb.models.User;
import com.abraham.curso.springboot.webapp.springbootweb.models.dto.ParamDTO;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {   

    /*
     * @Value("${config.username}")
     * private String username;
     */
    @Value("${config.message}")
    private String message;

    @Value("#{'${config.listOfValues}'.toUpperCase().split(',')}")
    private List<String> valueList;

    @Value("${config.code}")
    private Integer code;

    @Value("#{${config.valueMap}}")
    private Map<String, Object> valueMap;

    @Value("#{${config.valueMap}.product}")
    private String product;

    @Value("#{${config.valueMap}.description}")
    private String description;

    @Value("#{${config.valueMap}.price}")
    private long price;

    @Autowired
    private Environment environment;


    @GetMapping("/baz/{message}")
    public ParamDTO baz(@PathVariable(required = false) String message) {
        ParamDTO param = new ParamDTO();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);
        return json;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        user.setName(user.getName().toUpperCase());
        user.setLastname(user.getLastname().toUpperCase());
        return user;
    }

    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.username}") String username) {
        Map<String, Object> json = new HashMap<>();
        json.put("username", environment.getProperty("config.username"));
        json.put("message", message);
        json.put("listOfValues", valueList);
        json.put("code", code);
        json.put("code 2", environment.getProperty("config.code",Long.class));
        json.put("valueMap", valueMap);
        json.put("product", product);
        json.put("description", description);
        json.put("price", price);
        return json;
    }
}
