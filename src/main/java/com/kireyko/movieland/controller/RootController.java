package com.kireyko.movieland.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class RootController {
    private static int counter = 0;
    private static final String VIEW_INDEX = "index";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String welcome(ModelMap model) {

        model.addAttribute("message", "Welcome");
        model.addAttribute("counter", ++counter);
        log.debug("[welcome] counter : {}", counter);

        // Spring uses InternalResourceViewResolver and return back index.jsp
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String welcomeName(@PathVariable String name, ModelMap model) {

        model.addAttribute("message", "Welcome " + name);
        model.addAttribute("counter", ++counter);
        log.debug("[welcomeName] counter : {}", counter);
        return VIEW_INDEX;
    }

}