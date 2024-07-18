package com.project.emrs.service;

import java.util.ArrayList;

import com.project.emrs.dto.RentalDTO;

public interface RentalService {

	ArrayList<RentalDTO> getAllRental(Integer user_id);

}
