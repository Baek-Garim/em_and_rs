package com.project.emrs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.dao.RentalDAO;
import com.project.emrs.dao.ReservationDAO;
import com.project.emrs.dto.RentalDTO;
import com.project.emrs.dto.ReservationDTO;

import lombok.extern.slf4j.Slf4j;

import com.project.emrs.user.dao.UserDAO;
import com.project.emrs.user.dto.UserDTO;


@Service
@Slf4j
public class RentalServiceImpl implements RentalService{

	@Autowired
	RentalDAO rentalDAO;

	@Autowired
	ReservationDAO reservationDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	
	@Override
	public ArrayList<RentalDTO> getUserAllRental(Integer user_id) {
		return rentalDAO.getUserAllRental(user_id);
	}

	@Override
	public ArrayList<RentalDTO> rentalList(Integer user_id) {
		ArrayList<RentalDTO> rentalList = rentalDAO.rentalList(user_id);
		
		return rentalList;
	}

	@Override
	public void rentalRenew(Integer rental_id) {
		rentalDAO.rentalRenew(rental_id);
	}

	@Override
	public void insertRental(Integer reserve_id) {
		// 렌트 하려면 먼저
		
		// 예약 정보 가져오기
		ReservationDTO rsv = reservationDAO.findById(reserve_id);
		
		// 나머지 랜트 정보 채우기
		if(rsv != null) {
			RentalDTO rental = new RentalDTO();
			rental.setUser_id(rsv.getUser_id());
			rental.setTool_code(rsv.getTool_code());
			rental.setRental_date(LocalDateTime.now());
			rental.setReturn_date(LocalDateTime.now().plusDays(3));
			rental.setRenew(0);
			rental.setRental_state("정상");			

			// 대여 목록에 넣고
			rentalDAO.insertRental(rental);
			
			// 예약 상태를 대여완료로 바꾸고
			reservationDAO.completeReservation(reserve_id);
		}
		
		
	}

	// 어드민에서 대여중인 장비 전체 목록 불러오기
	@Override
	public ArrayList<RentalDTO> getActivateRentalList() {
		return rentalDAO.getActivateRentalList();
	}

	// 반납 하기
	@Override
	public void toolReturn(String tool_code, Integer rental_id, Integer user_id) {
		// 반납때 해야할 일
		// 대여상태를 반납으로 바꾸기
		rentalDAO.toolReturn(rental_id);

		// 연체인지 확인하고 연체면 반납 불가 설정하기
		Integer day = rentalDAO.returnDateChk(rental_id);
		if(day > 0) {
			UserDTO user = new UserDTO();
			user.setUser_id(user_id);
			user.setUser_rentable("대여 불가");
			user.setUnavailable_date(LocalDateTime.now().plusDays(day*2));
			
			userDAO.setOverdue(user);
		}
		
		// 해당 장비를 예약하고 있는 목록 대기 1번째를 대여 가능으로 바꾸기, 데드라인 오늘+3일 추가하기
		ReservationDTO reservation = reservationDAO.getFirstReservation(tool_code);
		if(reservation != null) {
			reservation.setReserve_state("대여가능");
			reservation.setDeadline_date(LocalDateTime.now().plusDays(3));		
			reservationDAO.updateActivateReservation(reservation);			
		}
		
	}

	

	
}
