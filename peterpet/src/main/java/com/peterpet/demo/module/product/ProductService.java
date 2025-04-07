package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	public Integer selectMaxSeq() {
		return productDao.selectMaxSeq();
	}
	
	public int insert(ProductDto productDto) {
		return productDao.insert(productDto);
	}
	
	public List<ProductDto> selectList(ProductVo vo) {
		return productDao.selectList(vo);
	}
	
	public int selectOneCount(ProductVo vo) {
		return productDao.selectOneCount(vo);
	}
	
	public int infoInsert(ProductDto productDto) {
		return productDao.infoInsert(productDto);
	}
	
	public int funcInsert(ProductDto productDto) {
		return productDao.funcInsert(productDto);
	}
	
	public ProductDto selectOnePoduct(ProductVo vo) {
		return productDao.selectOnePoduct(vo);
	}
	
	public List<ProductDto> selectOneFunctions(ProductVo vo) {
		return productDao.selectOneFunctions(vo);
	}
	
	public List<ProductDto> selectOneInfos(ProductVo vo) {
		return productDao.selectOneInfos(vo);
	}
	
	public int update(ProductDto productDto) {
		return productDao.update(productDto);
	}
	
	public int infoUpdate(ProductDto productDto) {
		return productDao.infoUpdate(productDto);
	}
	
	public int funcUpdate(ProductDto productDto) {
		return productDao.funcUpdate(productDto);
	}
	
	public int delete(ProductDto productDto) {
		return productDao.delete(productDto);
	}
	
	public int funcDelete(ProductDto productDto) {
		return productDao.funcDelete(productDto);
	}
	
	public int infoDelete(ProductDto productDto) {
		return productDao.infoDelete(productDto);
	}
	
	public int uelete(ProductDto productDto) {
		return productDao.uelete(productDto);
	}
	
	public int severalUelete(ProductVo vo) {
		return productDao.severalUelete(vo);
	}
	
	public int severalDelete(ProductVo vo) {
		return productDao.severalDelete(vo);
	}
}
