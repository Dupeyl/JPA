/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    
package monprojet.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import monprojet.entity.City;
import monprojet.entity.Country;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    private CountryRepository countryDAO;
    @Autowired
    private CityRepository cityDAO;

    @Test
    void onTrouveLePaysDesVilles() {
        log.info("On vérifie que les pays des villes sont bien récupérés");
        City paris = cityDAO.findByName("Paris");
        Country france = countryDAO.findById(1).orElseThrow();
        assertEquals(france, paris.getCountry(), "Paris est en France");
    }
    
     @Test
    void onTrouveLesVillesDesPays() {
        log.info("On vérifie que les villes d'un pays sont accessibles");
        City paris = cityDAO.findByName("Paris");
        Country france = countryDAO.findById(1).orElseThrow();
        assertTrue( france.getCities().contains(paris), "France contient Paris");
    }
}
    

