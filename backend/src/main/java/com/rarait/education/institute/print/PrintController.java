package com.rarait.education.institute.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.PrintException;
import java.io.FileNotFoundException;


@RestController
public class PrintController {

    @Autowired
    PrintServiceImpl printServiceImpl;

    @RequestMapping("/print")
    public void print() throws FileNotFoundException, PrintException {
        System.out.println("Print");
        printServiceImpl.printFile();
    }

    @GetMapping("/hello")
    public void hello(){
      printServiceImpl.getTitle();
    }




}
