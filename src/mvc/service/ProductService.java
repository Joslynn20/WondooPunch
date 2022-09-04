package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;

public interface ProductService {



	List<Product> productSelectAll() throws SQLException ,NotFoundException; 

   
	
	List<Product> productSelectBycategoryName (String categoryName )throws SQLException ,NotFoundException;


	
	List<Product> productSelectBykeyword(String keyword) throws SQLException,NotFoundException;
	
   
	 Product productSelectByproductName(String productName) throws SQLException,NotFoundException;
	

	 Product productSelectByproductCode (String productCode) throws SQLException,NotFoundException;


	 public  void productInsert(Product  product) throws SQLException ,AddException ;


	 public void  productDelete(String  productCode) throws SQLException,NotFoundException;

	 public  void productUpdate( Product product ) throws SQLException ,ModifyException ; 




}
