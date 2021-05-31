package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.UserRating;

@Service
public class RatingDataService {

	@Autowired
	private RestTemplate restTemplate;
	
	public UserRating getRatingData(String userId) {
		return restTemplate.getForObject("http://rating-data-service/ratings/"+userId, UserRating.class);
	}
}
