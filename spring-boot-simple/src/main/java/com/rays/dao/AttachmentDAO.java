package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dto.AttachmentDTO;

@Repository
public class AttachmentDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(AttachmentDTO dto) {
		em.persist(dto);
		return dto.getId();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AttachmentDTO dto) {
		em.merge(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(AttachmentDTO dto) {
		em.remove(dto);

	}

	@Transactional(readOnly = true)
	public AttachmentDTO findByPk(long pk) {
		AttachmentDTO dto = em.find(AttachmentDTO.class, pk);
		return dto;

	}
}
