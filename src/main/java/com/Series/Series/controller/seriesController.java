package com.Series.Series.controller;


import com.Series.Series.entity.series;
import com.Series.Series.payload.seriesdto;
import com.Series.Series.repository.seriesrepository;
import com.Series.Series.service.seriesService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("series/test")
public class seriesController {



    @Autowired
    public seriesService service;

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody seriesdto dto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return  new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.save(dto);

        return new ResponseEntity<>("Record is saved", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
           service.delete(id);

        return new ResponseEntity<>("Record is deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>update(@PathVariable long id , @Valid @RequestBody seriesdto dto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


        series se =  service.find(id);
           series serr = new series();
           serr.setId(se.getId());
           serr.setName(dto.getName());
           serr.setYearOfRelease(dto.getYearOfRelease());
           service.updatedsave(serr);

           return new ResponseEntity<>("Post of id "+ id + " is Updated",HttpStatus.OK);
    }
      //http:localhost:8080/series/test?pageNo=0&pageSize=4&sortBy=name&sortDir=
    @GetMapping
    public ResponseEntity<List<seriesdto>> readall(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "4", required = false) int pageSize,
            @RequestParam(name ="sortBy" ,defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue ="asc",required = false) String sortDir
    ){
        List<seriesdto> gotrecords = service.read(pageNo,pageSize,sortBy,sortDir);

        return new ResponseEntity<>(gotrecords,HttpStatus.OK);
    }


}
