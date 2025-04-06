package com.peterpet.demo.module.pet;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PetDao {

	public List<PetDto> selectList(PetVo vo);
	public List<PetDto> selectListOneUser(PetVo vo);
	public int selectOneCount(PetVo vo);
	public int selectOneCountOneUser(PetVo vo);
	public PetDto selectOne(PetVo vo);
	public List<PetDto> selectOnePersonal(PetVo vo);
	public List<PetDto> selectOneDisease(PetVo vo);
	public List<PetDto> selectListPeterPets(PetVo vo);
	public int insert(PetDto petDto);
	public int personalInsert(PetDto petDto);
	public int diseaseInsert(PetDto petDto);
	public String selectMaxSeq();
}
