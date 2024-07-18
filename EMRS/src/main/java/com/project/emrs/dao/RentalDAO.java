package com.project.emrs.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.dto.RentalDTO;
import com.project.emrs.dto.ReservationDTO;

@Mapper
public interface RentalDAO {
	
	Integer chkRentalState(String tool_code);	
	
	ArrayList<RentalDTO> getAllRental(Integer user_id);

	ArrayList<RentalDTO> rentalList(Integer user_id);

	Integer duplicateCheck(ReservationDTO reserve);


}
