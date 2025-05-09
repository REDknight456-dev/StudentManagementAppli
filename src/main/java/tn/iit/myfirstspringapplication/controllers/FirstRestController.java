package tn.iit.myfirstspringapplication.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FirstRestController {

    @GetMapping("/cars")
    public String helloCars(@RequestParam("model") String model) {
        return "Hello " + model;
    }

    @GetMapping("/cars/{id}")
    public String getCarById(@PathVariable("id") int id){
        return "Car : "+id;
    }

}
//sssq