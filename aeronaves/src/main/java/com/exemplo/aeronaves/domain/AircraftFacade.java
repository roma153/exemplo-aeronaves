package com.exemplo.aeronaves.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AircraftFacade {
	
	final AircraftService aircraftService;
	
	/**
	 * Method use to get a list of all persisted Aircraft
	 * 
	 * @return a {@link List} of {@link Aircraft}
	 */
	public List<Aircraft> findAll() {
		return aircraftService.findAll();
	}
	
	/**
	 * Method used to search an {@link Aircraft} by its id
	 * 
	 * @param id of the {@link Aircraft} desired
	 * @return if founded, a fulfilled {@link Aircraft} object, otherwise a new one
	 */
	public Aircraft findById(final Long id) {
		return aircraftService.findById(id);
	}
	
	/**
	 * Method used to save an {@link Aircraft} given object
	 * 
	 * @param aircraft the {@link Aircraft} given object to be saved
	 * @return a map object describing the method execution
	 * @throws JsonProcessingException if JSON object could not be parsed into an {@link Aircraft} object 
	 */
	@Transactional
	public void saveAircraft(final String aircraftJson) throws JsonProcessingException {
		final Aircraft aircraft = aircraftService.serializeAircraft(aircraftJson);
		aircraftService.saveAircraft(aircraft);
	}
	
	/**
	 * Method to update {@link Aircraft} fields
	 * 
	 * @param id is the identifier to {@link Aircraft} instance
	 * @param aircraftJson is a JSON object of {@link Aircraft} with new values
	 * 
	 * @throws JsonProcessingException if JSON object could not be parsed into an {@link Aircraft} object 
	 */
	@Transactional
	public void updateAircraft(final Long id, final String aircraftJson) throws JsonProcessingException {
		final Aircraft persistedAircraft = this.findById(id);
		final Aircraft modifiedAircraft = aircraftService.serializeAircraft(aircraftJson);
		if(modifiedAircraft.getId().equals(persistedAircraft.getId())) {
			persistedAircraft.update(modifiedAircraft);
			aircraftService.saveAircraft(persistedAircraft);
		} else {
			throw new IllegalArgumentException("Specified id does not matches with JSON object id");
		}
	}
	
	/**
	 * Method to delete an {@link Aircraft} object by its id
	 * @param id parameter used to delete an {@link Aircraft} object in database
	 */
	@Transactional
	public void deleteAircraft(final Long id) {
		aircraftService.deleteAircraft(id);
	}
	
	
}
	
