package com.project.emrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.project.emrs.dto.RentalDTO;

public interface RentalService {

	ArrayList<RentalDTO> getUserAllRental(Integer user_id);

	ArrayList<RentalDTO> rentalList(Integer user_id);


	void rentalRenew(Integer rental_id);

	void insertRental(Integer reserve_id);

	ArrayList<RentalDTO> getActivateRentalList();

	void toolReturn(String tool_code, Integer rental_id, Integer user_id);

	RentalDTO selectRental(Integer rental_id);

  // 어드민에서 정렬
	ArrayList<RentalDTO> getAllList();

	List<RentalDTO> getFilterData(String filterType, String sortType, String email, String tool_code);




}
