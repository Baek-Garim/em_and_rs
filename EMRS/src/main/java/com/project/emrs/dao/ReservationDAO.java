package com.project.emrs.dao;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.dto.ReservationDTO;

@Mapper
public interface ReservationDAO {

	Integer chkReserveState(ReservationDTO reservation);

	void insertReserve(ReservationDTO reservation);

}
