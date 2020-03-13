package com.exemplo.aeronaves.domain;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AircraftService {

	final AircraftRepository aircraftRepository;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * It get all persisted aircraft
	 * 
	 * @return a {@link List} of {@link Aircraft}
	 */
	public List<Aircraft> findAll() {
		return aircraftRepository.findAll();
	}
	
	/**
	 * Method looks for an {@link Aircraft} by its id
	 * 
	 * @param id of {@link Aircraft} searched
	 * @return the searched {@link Aircraft}
	 * 
	 * @throws {@link EntityNotFoundException} if id was not found on database
	 */
	public Aircraft findById(final Long id) {
		return aircraftRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = '" + id.toString() + "' was not found"));
	}
	
	/**
	 * Saves an {@link Aircraft} given object 
	 * 
	 * @param aircraft the {@link Aircraft} object to be persisted
	 * @return the persisted {@link Aircraft}
	 */
	public Aircraft saveAircraft(final Aircraft aircraft) {
		return this.aircraftRepository.save(aircraft);
	}
	
	
	/**
	 * Deletes an {@link Aircraft} object by it id
	 * @param id parameter to be used to delete an object in database
	 */
	public void deleteAircraft(final Long id) {
		aircraftRepository.deleteById(id);
	}
	
	/**
	 * Method to serialize a JSON into an {@link Aircraft} object
	 * 
	 * @param aircraftJson is a JSON object of {@link Aircraft}
	 * @return an {@link Aircraft} instance
	 * @throws JsonProcessingException if the JSON parameter could not be casted into an {@link Aircraft} object
	 */
	public Aircraft serializeAircraft(final String aircraftJson) throws JsonProcessingException {
		return mapper.readValue(aircraftJson, Aircraft.class);
	}
}
