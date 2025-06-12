package com.peterpet.demo.module.speech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.peterpet.demo.module.base.BaseService;

@Service
public class SpeechService extends BaseService{

	@Autowired
	private AmazonS3Client amazonS3Client;
	
	@Autowired
	private SpeechDao speechDao;
	
	public int SpeechPeterInsert(SpeechDto speechDto) throws Exception {
		uploadFilesToS3(speechDto.getUploadImg1(), speechDto, "image", 10, 1, "1", speechDao, amazonS3Client);
		return 1;
	}
}
