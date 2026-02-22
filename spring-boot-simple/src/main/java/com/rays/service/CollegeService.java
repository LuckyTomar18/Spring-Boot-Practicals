package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.CollegeDAO;
import com.rays.dto.CollegeDTO;

@Service
@Transactional
public class CollegeService {

	@Autowired
	public CollegeDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(CollegeDTO dto) {

		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(CollegeDTO dto) {

		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long pk) {

		CollegeDTO dto = dao.findByPk(pk);
		dao.delete(dto);
	}

	@Transactional(readOnly = true)
	public CollegeDTO findByPk(long pk) {

		CollegeDTO dto = dao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) {

		return dao.search(dto, pageNo, pageSize);

	}
}
