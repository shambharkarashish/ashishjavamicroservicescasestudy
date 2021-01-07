package com.virtusa.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ecommerce.models.Location;
import com.virtusa.ecommerce.repository.LocationRepository;
@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	public Location addLocation(Location location) {
		return locationRepository.save(location);
	}
	
	public List<Location> findAllLocations(){
		return locationRepository.findAll();
	}
	
	public Location getByLocationId(long locationId) {
		return locationRepository.findById(locationId).orElse(null);
	}
}
