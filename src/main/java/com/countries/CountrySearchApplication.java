package com.countries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountrySearchApplication {
    static CountryList ourCountryList;
    public static void main(String[] args) {

        ourCountryList = new CountryList();
        SpringApplication.run(CountrySearchApplication.class, args);
    }

}
