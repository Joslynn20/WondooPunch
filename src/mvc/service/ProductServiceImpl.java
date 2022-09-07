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
	public List<Product> selectAllProduct() throws SQLException, NotFoundException {
		
		
		   List<Product> list =productDAO.selectAllProduct();
		   if(list==null||list.isEmpty()) throw new NotFoundException("상품이 존재하지 않습니다");
		
		return list;
	}

	
	@Override
	public List<Product> selectProductByCategoryName(String categoryName) throws SQLException, NotFoundException {
	

		   List<Product> list =productDAO.selectProductByCategoryName(categoryName);
		   if(list==null||list.isEmpty())  throw new NotFoundException("존재 하지 않는 카테고리 입니다");
		
		return list;
	}

	@Override
	public List<Product> selectProductByKeyword(String keyword) throws SQLException, NotFoundException {
		
		   List<Product> list =productDAO.selectProductByKeyword(keyword);
		   if(list==null||list.isEmpty()) throw new NotFoundException("입력하신 키워드 :"+keyword+"에 대한 정보가 존재 하지 않습니다");
		
		
		return list;
	}

	@Override
	public Product selectProductByProductName(String productName) throws SQLException, NotFoundException {
		 Product product  =productDAO.selectProductByProductName(productName);
		if( product ==null  ) throw new NotFoundException("입력하신 상품이름 에 대한 정보가 존재 하지 않습니다");	
		return product;
	
	}

	@Override
	public Product selectProductByProductCode(String productCode)throws SQLException, NotFoundException {
		    
		Product product   =productDAO.selectProductByProductCode(productCode);
		if( product ==null     ) throw new NotFoundException("입력하신 상품코드 에 대한 정보가 존재 하지 않습니다");
		
		return product;
	}

	@Override
	public void insertProduct(Product product) throws SQLException, AddException {
		

		   int result =productDAO.insertProduct(product);
	       if(result==0) throw new AddException("상품 등록 실패 하였습니다");   
	     
	  
	
	}

	@Override
	public void deleteProduct(String productCode) throws SQLException, NotFoundException {
	

		if(productDAO.deleteProduct(productCode)==0)
		 throw new NotFoundException("삭제 실패 했습니다");
	
	}

	@Override
	public void updateProduct(Product product) throws SQLException, ModifyException {
	
		if(productDAO.updateProduct(product)==0)
			throw new ModifyException("상품 수정 실패 하였습니다");
	
	}

}
