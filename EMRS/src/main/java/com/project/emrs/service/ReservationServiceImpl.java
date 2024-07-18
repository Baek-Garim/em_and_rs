package com.project.emrs.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.dao.ReservationDAO;
import com.project.emrs.dto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	ReservationDAO reservationDAO;

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
		}
	
		reservationDAO.insertReserve(reservation);
	}

	@Override
	public Integer chkReserveState(ReservationDTO reservation) {		
		return reservationDAO.chkReserveState(reservation);
	}
	
	
	
}
