package com.project.emrs.service;


import java.util.ArrayList;
import java.util.Map;

import com.project.emrs.dto.ReservationDTO;

public interface ReservationService {

	void insertReserve(Integer user_id, String tool_code);

	Integer chkReserveState(ReservationDTO reservation);

	ArrayList<ReservationDTO> getUserAllReservation(Integer user_id);

	Integer countMyReservation(Map<String, Object> countMap);

	Integer duplicateCheck(Integer user_id, String tool_code);

	ArrayList<ReservationDTO> reservationList(Integer user_id);

	ArrayList<ReservationDTO> getActivateReserveList();

	Integer countMyTotalReservation(Integer user_id);

	
}
