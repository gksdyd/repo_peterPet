package com.peterpet.demo.module.speech;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;
import com.peterpet.demo.module.base.BaseDto;

@Repository
public interface SpeechDao extends BaseDao {

	public int insertUploaded(BaseDto dto);
}
