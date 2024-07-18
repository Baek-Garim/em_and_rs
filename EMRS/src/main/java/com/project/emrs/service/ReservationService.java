package com.project.emrs.service;


import com.project.emrs.dto.ReservationDTO;

public interface ReservationService {

	void insertReserve(Integer user_id, String tool_code);

	Integer chkReserveState(ReservationDTO reservation);
	
}
