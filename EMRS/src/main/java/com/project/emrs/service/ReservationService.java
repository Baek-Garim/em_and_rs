package com.project.emrs.service;


import java.util.ArrayList;

import com.project.emrs.dto.ReservationDTO;

public interface ReservationService {

	void insertReserve(Integer user_id, String tool_code);

	Integer chkReserveState(ReservationDTO reservation);

	ArrayList<ReservationDTO> getAllReservation(Integer user_id);

	Integer countMyReservation(Integer user_id);
	
}
