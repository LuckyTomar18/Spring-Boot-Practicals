package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.CollegeDTO;

@Repository
public class CollegeDAO {

	@PersistenceContext
	public EntityManager em;

	public long add(CollegeDTO dto) {
		em.persist(dto);
		return dto.getId();
	}

	public void update(CollegeDTO dto) {
		em.merge(dto);

	}

	public void delete(CollegeDTO dto) {
		em.remove(dto);

	}

	public CollegeDTO findByPk(long pk) {
		CollegeDTO dto = em.find(CollegeDTO.class, pk);
		return dto;
	}

	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) {

		List<CollegeDTO> list = new ArrayList<CollegeDTO>();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<CollegeDTO> cq = builder.createQuery(CollegeDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		Root<CollegeDTO> qRoot = cq.from(CollegeDTO.class);

		if (dto != null) {
			if (dto.getName() != null && dto.getName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
			}
			if (dto.getCity() != null && dto.getCity().length() > 0) {
				predicateList.add(builder.like(qRoot.get("city"), dto.getCity()));
			}
		}
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
		TypedQuery<CollegeDTO> tq = em.createQuery(cq);

		if (pageSize > 0) {

			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}
		list = tq.getResultList();
		return list;
	}
}
