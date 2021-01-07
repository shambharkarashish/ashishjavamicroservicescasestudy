package com.virtusa.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.ecommerce.models.Location;
import com.virtusa.ecommerce.services.LocationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/locations")
public class LocationController {

	@Autowired
	private LocationService service;
	
	@PostMapping({"/v1.0", "v1.1"})
	
	@CrossOrigin("*")
	public ResponseEntity<?> addLocation(@RequestBody Location location){
		Location locationObj = service.addLocation(location);
		if(locationObj != null) {
			log.info("location added successfully");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(locationObj);
		}
		else {
			log.info("location not added");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Added");
		}
	}
	
	@GetMapping({"/v1.0", "v1.1"})
	
	@CrossOrigin("*")
	public List<Location> findAllLocations(){
		return service.findAllLocations();
	}
}
