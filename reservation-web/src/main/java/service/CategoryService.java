package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CategoryDao;
import dto.category.CategoriesDto;

@Service
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;
	
	public CategoriesDto getCategoryList(){
		return categoryDao.getCategoryList();
	}
}
