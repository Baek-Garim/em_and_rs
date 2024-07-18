package com.project.emrs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.dao.RentalDAO;
import com.project.emrs.dto.RentalDTO;

@Service
public class RentalServiceImpl implements RentalService{

	@Autowired
	RentalDAO rentalDAO;

	@Override
	public ArrayList<RentalDTO> getAllRental(Integer user_id) {
		return rentalDAO.getAllRental(user_id);
	}

	
	
	
}
