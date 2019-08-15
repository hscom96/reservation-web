package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CategoryDao;
import dto.category.CategoryListDto;

@Service
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;
	
	public CategoryListDto getCategoryList(){

		CategoryListDto categoryListDto = new CategoryListDto();
		categoryListDto.setItems(categoryDao.getCategoryList());
		
		return categoryListDto;
	}
}
