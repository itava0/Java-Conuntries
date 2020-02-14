package com.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;

@RestController
@RequestMapping("/data")
public class CountryController {

    //localhost:8080/data/names/all
    //  return the names of all the countries alphabetically
    @GetMapping(value = "/names/all",
                produces = {"application/json"})
    public ResponseEntity<?> getAllCountries() {
        // sort the country list alphabetically
        CountrySearchApplication.ourCountryList.countryList.sort((c1, c2) -> c1.getCountryName().compareToIgnoreCase(c2.getCountryName()));

        return new ResponseEntity<>(CountrySearchApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    //localhost:8080/data/names/start/{letter}
    //  return the countries alphabetically that begin with the given letter
    @GetMapping(value = "/names/start/{letter}",
                produces = {"application/json"})

    public ResponseEntity<?> getCountriesByFirstLetter(@PathVariable char letter) {

        // find countries that begin with letter from path
        ArrayList<Country> rtnCountry = CountrySearchApplication.ourCountryList.findCountries(c -> c.getCountryName().toUpperCase().charAt(0) == Character.toUpperCase(letter));

        // sort the country list alphabetically
        rtnCountry.sort((c1, c2) -> c1.getCountryName().compareToIgnoreCase(c2.getCountryName()));

        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    //localhost:8080/data/names/size/{number}
    //return the countries alphabetically that have a name equal to or longer than the given length
    @GetMapping(value = "names/size/{number}",
                produces = {"application/json"})
    public ResponseEntity<?> getCountriesByNameLength(@PathVariable int number) {

        // find countries with name equal to or longer than given length
        ArrayList<Country> rtnCountry = CountrySearchApplication.ourCountryList.findCountries(c -> c.getCountryName().length() >= number);

        // sort the country list alphabetically
        rtnCountry.sort((c1, c2) -> c1.getCountryName().compareToIgnoreCase(c2.getCountryName()));

        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    //localhost:8080/data/population/size/{people}
//  return the countries that have a population equal to or greater than the given population
    @GetMapping(value = "/population/size/{people}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByPopulation(@PathVariable int people)
    {
        // find countries with equal or greater population size
        ArrayList<Country> resData = CountrySearchApplication.ourCountryList.findCountries(c -> c.getPopulation() >= people);

        return new ResponseEntity<>(resData, HttpStatus.OK);
    }

    //localhost:8080/data/population/min
//  return the country with the smallest population
    @GetMapping(value = "/population/min",
            produces = {"application/json"})
    public ResponseEntity<?> getSmallestPopulation()
    {
        // sort countries by population, ascending
        CountrySearchApplication.ourCountryList.countryList.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));

        return new ResponseEntity<>(CountrySearchApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    //localhost:8080/data/population/max
//  return the country with the largest population
    @GetMapping(value = "/population/max",
            produces = {"application/json"})
    public ResponseEntity<?> getLargestPopulation()
    {
        // sort countries by population, descending
        CountrySearchApplication.ourCountryList.countryList.sort((c1, c2) -> (int)(c2.getPopulation() - c1.getPopulation()));

        return new ResponseEntity<>(CountrySearchApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    //    Stretch Goal
//  /population/median
//  return the country with the median population
    @GetMapping(value = "/population/median",
            produces = {"application/json"})
    public ResponseEntity<?> getMedianPopulation()
    {
        // sort by population, ascending
        CountrySearchApplication.ourCountryList.countryList.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));

        // get country at middle of list
        Country resData = CountrySearchApplication.ourCountryList.countryList.get(CountrySearchApplication.ourCountryList.countryList.size() / 2);

        return new ResponseEntity<>(resData, HttpStatus.OK);
    }

    //  /age/age/{age}
//  return the countries that have a median age equal to or greater than the given age
    @GetMapping(value = "/age/age/{age}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByMedianAge(@PathVariable int age)
    {
        return new ResponseEntity<>(CountrySearchApplication.ourCountryList.findCountries(c -> c.getMedianAge() >= age), HttpStatus.OK);
    }

    //age/min
//  return the country with the smallest median age
    @GetMapping(value = "age/min",
            produces = {"application/json"})
    public ResponseEntity<?> getSmallestAge()
    {
        // sort countries by median age, ascending
        CountrySearchApplication.ourCountryList.countryList.sort((c1, c2) -> (c1.getMedianAge() - c2.getMedianAge()));

        return new ResponseEntity<>(CountrySearchApplication.ourCountryList.countryList.get(0), HttpStatus.OK);
    }

}

