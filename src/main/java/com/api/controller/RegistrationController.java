package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>>  getAllRegistrations(){
        List<RegistrationDto> dtos = registrationService.getRegistration();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
        @PostMapping
        public ResponseEntity<?> CreateRegistration(
             @Valid @RequestBody RegistrationDto registrationDto,
             BindingResult result
        ){
            if(result.hasErrors()){
                return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
            }
           RegistrationDto regDto =  registrationService.createRegistrations(registrationDto);
           return new ResponseEntity<>(regDto, HttpStatus.CREATED);
        }

        @DeleteMapping
        public ResponseEntity<String> deleteRegistration(
              @RequestParam  long id
        ){
            registrationService.deleteRegistrations(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        @PutMapping("/{id}")
        public ResponseEntity<Registration> updateRegistration(
                @PathVariable long id,
            @RequestBody Registration registration
        ){
          Registration updateReg =  registrationService.updateRegistrations(id,registration);
            return new ResponseEntity<>(updateReg,HttpStatus.OK);
        }
        @GetMapping("/{id}")
        public ResponseEntity<RegistrationDto> getRegistrationById(
                @PathVariable long id
        ){

            RegistrationDto dto = registrationService.getRegistrationById(id);
            return  new ResponseEntity<>(dto,HttpStatus.OK);
        }

}
