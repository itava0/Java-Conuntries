package com.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/data")
public class CountryController {

    //localhost:8080/data/names/all

    @GetMapping(value = "/names/all",
                produces = {"application/json"})
    public ResponseEntity<?> getAllCountries() {
        CountrySearchApplication.ourCountryList.countryList.sort((e1, e2) -> e1.getCountryName().compareToIgnoreCase(e2.getCountryName()));
        return new ResponseEntity<>(CountrySearchApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    //localhost:8080/data/names/start/{letter}

    @GetMapping(value = "/names/start/{letter}",
                produces = {"application/json"})

    public ResponseEntity<?> getCounties(
            @PathVariable
                    char letter) {
        ArrayList<Country> rtnCountry = CountrySearchApplication.ourCountryList.findCountry(e -> e.getCountryName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }
}
