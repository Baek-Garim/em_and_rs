package com.project.emrs.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.dto.ReservationDTO;

@Mapper
public interface ReservationDAO {

	Integer chkReserveState(ReservationDTO reservation);

	void insertReserve(ReservationDTO reservation);

	ArrayList<ReservationDTO> getUserAllReservation(Integer user_id);


	Integer countMyReservation(Map<String, Object> countMap);


	Integer duplicateCheck(ReservationDTO reserve);



	ArrayList<ReservationDTO> reservationList(Integer user_id);

	ArrayList<ReservationDTO> getActivateReserveList();


	ReservationDTO findById(Integer reserve_id);


	void completeReservation(Integer reserve_id);


	ReservationDTO getFirstReservation(String tool_code);

	void updateActivateReservation(ReservationDTO reservation);

	Integer countMyTotalReservation(Integer user_id);


}
