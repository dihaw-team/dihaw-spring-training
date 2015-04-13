package com.dihaw.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dihaw.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	
	List<City> findAll();
	
	
    /**
     * Find a single {@link City} by his unique name.
     * 
     * @param cityName the city name to look
     * @return the {@link City} or <code>null</code>
     */
    @Query("select c from City c where c.cityName = :cityName")
	City findByCityName(@Param("cityName") String cityName);

    /**
     * Retrieve the list of {@link City}.
     * 
     * @param pageable the paging specification
     * @return the {@link Page} of {@link City}
     */
	@Query("select c from City c")
	Page<City> findAllCity(Pageable pageable);
	
	
}
