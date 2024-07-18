package com.project.emrs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.dao.RentalDAO;
import com.project.emrs.dao.ReservationDAO;
import com.project.emrs.dto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	ReservationDAO reservationDAO;
	
	@Autowired
	RentalDAO rentalDAO;

	@Override
	public void insertReserve(Integer user_id, String tool_code) {
		
		ReservationDTO reservation = new ReservationDTO();
		
		reservation.setTool_code(tool_code);
		reservation.setUser_id(user_id);
		reservation.setReserve_date(LocalDateTime.now());
		
		// 예약 가능 상태면
		if(chkReserveState(reservation) == 0) {
			reservation.setDeadline_date(reservation.getReserve_date().minusDays(3));
			reservation.setReserve_state('Y');			
		} else {
			reservation.setDeadline_date(null);
			reservation.setReserve_state('N');						
		}
	
		reservationDAO.insertReserve(reservation);
	}

	@Override
	public Integer chkReserveState(ReservationDTO reservation) {		
		Integer temp = rentalDAO.chkRentalState(reservation.getTool_code());

		if(temp > 0) return temp;
		
		temp += reservationDAO.chkReserveState(reservation);

		return temp;
	}

	@Override
	public ArrayList<ReservationDTO> getAllReservation(Integer user_id) {
		return reservationDAO.getAllReservation(user_id);
	}

	@Override
	public Integer countMyReservation(Integer user_id) {
		return reservationDAO.countMyReservation(user_id);
	}
	
	
	
}
