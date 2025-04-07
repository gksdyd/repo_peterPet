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
	
	public int insert(PetDto petDto) {
		return petDao.insert(petDto);
	}
	
	public int personalInsert(PetDto petDto) {
		return petDao.personalInsert(petDto);
	}
	
	public int diseaseInsert(PetDto petDto) {
		return petDao.diseaseInsert(petDto);
	}
	
	public String selectMaxSeq() {
		return petDao.selectMaxSeq();
	}
	
	public int update(PetDto petDto) {
		return petDao.update(petDto);
	}
	
	public int updatePersonal(PetDto petDto) {
		return petDao.updatePersonal(petDto);
	}
	
	public int updateDisease(PetDto petDto) {
		return petDao.updateDisease(petDto);
	}
	
	public int deletePersonal(PetDto petDto) {
		return petDao.deletePersonal(petDto);
	}
	
	public int deleteDisease(PetDto petDto) {
		return petDao.deleteDisease(petDto);
	}
}
