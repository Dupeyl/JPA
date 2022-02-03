package monprojet.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import monprojet.entity.Country;
import org.springframework.data.jpa.repository.Query;


public interface CountryRepository extends JpaRepository<Country, Integer> {

    public Country findByName(String countryName);

    @Query
        (value = "SELECT SUM(POPULATION) " 
    + "FROM  "
    + "(SELECT Country.name AS Pays, City.name AS Ville, City.population AS POPULATION "
    + "FROM Country INNER JOIN City "
    + "WHERE Country.ID=City.COUNTRY_ID) "
    + "WHERE PAYS = : nom", nativeQuery = true)
    public int populationPays(String nom);

    @Query
        (value = "SELECT  Pays, SUM(POPULATION) AS Population "
        + "FROM "
        + "(SELECT Country.name AS Pays, City.name AS Ville, City.population AS Population "
        + "FROM Country INNER JOIN City "
        + "WHERE Country.ID=City.COUNTRY_ID) "
        + "GROUP BY Pays", nativeQuery = true)
    
    public List<PopPays> paysAvecPopulation();
}
