package com.iqmsoft.boot.react.greetings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


@RestController
@RequestMapping("/rest")
public class GreetingsController {

    private List<String> names = new LinkedList<>();
    
    private Map<String, String> maps = new LinkedHashMap<>();
    
    List<String> l = new LinkedList<>();
	
	

    @PostConstruct
    private void init() {
        names.add("World");
        names.add("To React, from Spring Boot");
        names.add("This is a Test");
        names.add("This is another Test");
        names.add("This is yet another Test");
        
        maps.put("one", "This is a refresh test1");
        maps.put("two", "This is a refresh test2");
        maps.put("three", "This is a refresh test3");
        maps.put("four", "This is a refresh test4");
        
       
       	l.addAll(maps.values());
    }

    @GetMapping("/greetings")
    public ResponseEntity<String> getHello() {
        int index = new Random().nextInt(names.size());
        return getHelloWithName(names.get(index));
    }
    
    @GetMapping("/welcome")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> getWelcome() {
      
    	return new ResponseEntity<>("Welcome " + l.get(new Random().nextInt(l.size())), HttpStatus.OK);
        
    }

    @GetMapping("/greetings/{name}")
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public ResponseEntity<String> getHelloWithName(@PathVariable String name) {
        return ResponseEntity.ok("Hello, "+name+"!");
    }

    @PostMapping("/greetings")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> postName(@RequestBody String name) {
        names.add(name);

        return ResponseEntity.ok().build();
    }
}
