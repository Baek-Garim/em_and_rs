package com.project.emrs.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.dto.ReservationDTO;

@Mapper
public interface ReservationDAO {

	Integer chkReserveState(ReservationDTO reservation);

	
	void insertReserve(ReservationDTO reservation);

	ArrayList<ReservationDTO> getAllReservation(Integer user_id);


	Integer countMyReservation(Integer user_id);


	Integer duplicateCheck(ReservationDTO reserve);

}
