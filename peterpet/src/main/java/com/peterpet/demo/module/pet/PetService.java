package com.peterpet.demo.module.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.peterpet.demo.module.base.BaseService;

@Service
public class PetService extends BaseService{

	@Autowired
	PetDao petDao;
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
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
	
	public int insert(PetDto petDto) throws Exception {
		petDao.insert(petDto);
		uploadFilesToS3(petDto.getUploadImg1(), petDto, "image", petDto.getUploadImg1Type(), petDto.getUploadImg1MaxNumber()
    			, petDto.getPetSeq(), petDao, amazonS3Client);
		return 1;
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
	
	public int update(PetDto petDto) throws Exception {
		petDao.update(petDto);
		petDao.updateUploaded(petDto);
		uploadFilesToS3(petDto.getUploadImg1(), petDto, "image", petDto.getUploadImg1Type(), petDto.getUploadImg1MaxNumber()
    			, petDto.getPetSeq(), petDao, amazonS3Client);
		return 1;
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
	
	public int uelete(PetVo vo) {
		return petDao.uelete(vo);
	}
}
