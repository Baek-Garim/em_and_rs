package com.project.emrs.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.dto.RentalDTO;
import com.project.emrs.dto.ReservationDTO;

@Mapper
public interface RentalDAO {
	
	Integer chkRentalState(String tool_code);	
	
	ArrayList<RentalDTO> getUserAllRental(Integer user_id);

	ArrayList<RentalDTO> rentalList(Integer user_id);

	Integer duplicateCheck(ReservationDTO reserve);

	void rentalRenew(Integer rental_id);

	void insertRental(RentalDTO rental);

	ArrayList<RentalDTO> getActivateRentalList();

	void toolReturn(Integer rental_id);

	Integer returnDateChk(Integer rental_id);

	RentalDTO selectRental(Integer rental_id);



}
