package or.connect.reservationweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import or.connect.reservationweb.dao.CategoryDao;
import or.connect.reservationweb.dto.category.CategoryDto;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	public List<CategoryDto> getCategoryList() {
		return categoryDao.getCategoryList();
	}
}
