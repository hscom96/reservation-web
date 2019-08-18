package or.connect.reservationweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import or.connect.reservationweb.dao.CategoryDao;
import or.connect.reservationweb.dto.category.CategoryListDto;

@Service
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;

	public CategoryListDto getCategoryList() {

		CategoryListDto categoryListDto = new CategoryListDto();
		categoryListDto.setItems(categoryDao.getCategoryList());

		return categoryListDto;
	}
}
