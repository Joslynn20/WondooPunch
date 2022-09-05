package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.ProductDAO;
import mvc.dao.ProductDAOImpl;
import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;


public class ProductServiceImpl implements ProductService {
 
  	private ProductDAO productDAO  =  new ProductDAOImpl();
   
 	
	
	
   	
	
	@Override
	public List<Product> productSelectAll() throws SQLException, NotFoundException {
		
		
		   List<Product> list =productDAO.productSelectAll();
		   if(list==null||list.isEmpty()) throw new NotFoundException("상품이 존재하지 않습니다");
		
		return list;
	}

	
	@Override
	public List<Product> productSelectBycategoryName(String categoryName) throws SQLException, NotFoundException {
	

		   List<Product> list =productDAO.productSelectBycategoryName(categoryName);
		   if(list==null||list.isEmpty())  throw new NotFoundException("존재 하지 않는 카테고리 입니다");
		
		return list;
	}

	@Override
	public List<Product> productSelectBykeyword(String keyword) throws SQLException, NotFoundException {
		
		   List<Product> list =productDAO.productSelectBykeyword(keyword);
		   if(list==null||list.isEmpty()) throw new NotFoundException("입력하신 키워드 :"+keyword+"에 대한 정보가 존재 하지 않습니다");
		
		
		return list;
	}

	@Override
	public Product productSelectByproductName(String productName) throws SQLException, NotFoundException {
		 Product product  =productDAO.productSelectByproductName(productName);
		if( product ==null  ) throw new NotFoundException("입력하신 상품이름 에 대한 정보가 존재 하지 않습니다");	
		return product;
	
	}

	@Override
	public Product productSelectByproductCode(String productCode) throws SQLException, NotFoundException {
		    
		Product product   =productDAO.productSelectByproductCode(productCode);
		if( product ==null     ) throw new NotFoundException("입력하신 상품코드 에 대한 정보가 존재 하지 않습니다");
		
		return product;
	}

	@Override
	public void productInsert(Product product) throws SQLException, AddException {
		

		   int result =productDAO.productInsert(product);
	       if(result==0) throw new AddException("상품 등록 실패 하였습니다");   
	     
	  
	
	}

	@Override
	public void productDelete(String productCode) throws SQLException, NotFoundException {
	

		if(productDAO.productDelete(productCode)==0)
		 throw new NotFoundException("삭제 실패 했습니다");
	
	}

	@Override
	public void productUpdate(Product product) throws SQLException, ModifyException {
	
		if(productDAO.productUpdate(product)==0)
			throw new ModifyException("상품 수정 실패 하였습니다");
	
	}

}
