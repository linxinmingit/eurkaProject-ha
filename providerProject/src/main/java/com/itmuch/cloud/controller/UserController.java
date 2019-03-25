package com.itmuch.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Value("${zone.name}")
  private String zoneName;

  @RequestMapping(value = "/hi/{id}", method = RequestMethod.GET)
  public String hi(@PathVariable Integer id) {
    System.out.println(id);
    //System.out.println(1111);
    return zoneName;
  }

}
