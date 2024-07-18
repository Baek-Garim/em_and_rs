package com.project.emrs.service;

import java.util.ArrayList;

import com.project.emrs.dto.RentalDTO;

public interface RentalService {

	ArrayList<RentalDTO> getUserAllRental(Integer user_id);

	ArrayList<RentalDTO> rentalList(Integer user_id);


	void rentalRenew(Integer rental_id);

	void insertRental(Integer reserve_id);

	ArrayList<RentalDTO> getActivateRentalList();

	void toolReturn(String tool_code, Integer rental_id, Integer user_id);



}
