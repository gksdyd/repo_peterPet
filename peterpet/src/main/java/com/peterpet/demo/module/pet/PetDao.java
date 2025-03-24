package com.peterpet.demo.module.pet;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PetDao {

	List<PetDto> selectList(PetVo vo);
	List<PetDto> selectListOneUser(PetVo vo);
	int selectOneCount(PetVo vo);
	int selectOneCountOneUser(PetVo vo);
	PetDto selectOne(PetDto petDto);
	List<PetDto> selectOnePersonal(PetDto petDto);
	List<PetDto> selectOneDisease(PetDto petDto);
}
