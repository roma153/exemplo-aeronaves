package com.exemplo.aeronaves.api;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.aeronaves.domain.Aircraft;
import com.exemplo.aeronaves.domain.AircraftFacade;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

/**
 * AircraftController
 * @author Felipe Romera
 *
 */
@RestController("/")
@AllArgsConstructor
public class AircraftController {
	
	final AircraftFacade aircraftFacade;

	/**
	 * Method to get all aircraft in database
	 * 
	 * @return a {@link List} of all {@link Aircraft} in database
	 */
	@GetMapping
	public List<Aircraft> getAllAircraft() {
		return aircraftFacade.findAll();
	}
	
	/**
	 * Method to find an {@link Aircraft} by its id
	 * 
	 * @param id used to search
	 * @return an {@link Aircraft} object
	 */
	@GetMapping("{id}")
	public ResponseEntity<Aircraft> findById(@PathVariable final Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(aircraftFacade.findById(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	/**
	 * Method to add a new {@link Aircraft}
	 * 
	 * @param aircraftJson
	 * @return a map to describe the method execution
	 */
	@PostMapping
	public ResponseEntity<?> addAircraft(@RequestBody final String aircraftJson) {
		try {
			aircraftFacade.saveAircraft(aircraftJson);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * Method to update an {@link Aircraft} through a JSON object
	 * 
	 * @param id is an identifier to an {@link Aircraft} object
	 * @param aircraftJson is a JSON object of {@link Aircraft} with new values
	 * @return a {@link ResponseEntity} with request status
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> updateAircraft(@PathVariable final Long id, @RequestBody final String aircraftJson) {
		try {
			aircraftFacade.updateAircraft(id, aircraftJson);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	/**
	 * Method to delete an {@link Aircraft} object by its id
	 * 
	 * @param id given to delete an {@link Aircraft} object
	 * @return a {@link ResponseEntity} with request status
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteAircraft(@PathVariable final Long id) {
		aircraftFacade.deleteAircraft(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
