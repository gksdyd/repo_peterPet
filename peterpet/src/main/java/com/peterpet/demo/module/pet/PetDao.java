package com.peterpet.demo.module.pet;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;
import com.peterpet.demo.module.base.BaseDto;

@Repository
public interface PetDao extends BaseDao {

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
	public int update(PetDto petDto);
	public int updatePersonal(PetDto petDto);
	public int updateDisease(PetDto petDto);
	public int deletePersonal(PetDto petDto);
	public int deleteDisease(PetDto petDto);
	public int uelete(PetVo vo);
	public int insertUploaded(BaseDto dto);
	public int updateUploaded(BaseDto dto);
}
