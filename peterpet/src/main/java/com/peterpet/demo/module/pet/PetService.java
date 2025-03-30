package com.peterpet.demo.module.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

	@Autowired
	PetDao petDao;
	
	public List<PetDto> selectList(PetVo vo) {
		return petDao.selectList(vo);
	}
	
	public List<PetDto> selectListOneUser(PetVo vo) {
		return petDao.selectListOneUser(vo);
	}
	
	public int selectOneCount(PetVo vo) {
		return petDao.selectOneCount(vo);
	}
	
	public int selectOneCountOneUser(PetVo vo) {
		return petDao.selectOneCountOneUser(vo);
	}
	
	public PetDto selectOne(PetVo vo) {
		return petDao.selectOne(vo);
	}
	
	public List<PetDto> selectOnePersonal(PetVo vo) {
		return petDao.selectOnePersonal(vo);
	}
	
	public List<PetDto> selectOneDisease(PetVo vo) {
		return petDao.selectOneDisease(vo);
	}
	
	public List<PetDto> selectListPeterPets(PetVo vo) {
		return petDao.selectListPeterPets(vo);
	}
}
