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
	
	public int selectOneCount() {
		return petDao.selectOneCount();
	}
	
	public int selectOneCountOneUser(PetVo vo) {
		return petDao.selectOneCountOneUser(vo);
	}
	
	public PetDto selectOne(PetDto petDto) {
		return petDao.selectOne(petDto);
	}
	
	List<PetDto> selectOnePersonal(PetDto petDto) {
		return petDao.selectOnePersonal(petDto);
	}
	
	List<PetDto> selectOneDisease(PetDto petDto) {
		return petDao.selectOneDisease(petDto);
	}
}
