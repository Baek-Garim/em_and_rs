package com.project.emrs.service;


import java.util.ArrayList;

import com.project.emrs.dto.ReservationDTO;

public interface ReservationService {

	void insertReserve(Integer user_id, String tool_code);

	Integer chkReserveState(ReservationDTO reservation);

	ArrayList<ReservationDTO> getUserAllReservation(Integer user_id);

	Integer countMyReservation(Integer user_id);

	Integer duplicateCheck(Integer user_id, String tool_code);

	ArrayList<ReservationDTO> reservationList(Integer user_id);

	ArrayList<ReservationDTO> getActivateReserveList();

	
}
