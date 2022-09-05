package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;
import mvc.dto.Option;
import mvc.dto.Orders;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;
import mvc.service.OptionService;
import mvc.service.OptionServiceImpl;

public class OptionController {
	public static OptionService optionservice = new OptionServiceImpl();
	public static Option option;
	public static CoffeeOption coffeeoption;
	private static List<Option> list;
	public static Orders order;

	/**
	 * 옵션목록
	 * @throws SQLException, NotFoundException 
	 */
	public static void optionSelect() throws SQLException, NotFoundException {
		try {
			List<Option> list = optionservice.optionSelect();		
			System.out.println(list);
			
		} catch (NotFoundException e) {
			
		System.out.println(e.getMessage());
		} // catch end
	} // optionSelect end
	
	/**
	 * 옵션번호에 해당하는 옵션검색
	 * @return 
	 * @throws SQLException 
	 * */
	public static void optionSelectByOptionCode(String optionCode) throws NotFoundException, SQLException {
		try {	
		Option option = optionservice.optionSelectByOptionCode(optionCode);
		System.out.println(option);
		
		}catch(NotFoundException e) {
			System.out.println(e.getMessage());
		}		
	}	
	
	/**
	 * 옵션추가
	 * @throws SQLException 
	 */

	public static void optionInsert(Option option) throws SQLException, AddException {
		try {
			optionservice.optinInsert(option);
			
		} catch (AddException e) {
			System.out.println(e.getMessage());
		
		}
	} // optionInsert end
	
	
	/**
	 * 옵션 수정
	 * @throws SQLException 
	 */
	public static void optionUpdate(Option option) throws SQLException {

		try {
			optionservice.optionUpdate(option);
			
		} catch (ModifyException e) {
			System.out.println(e.getMessage());
			
		}
	}// optionUpdate end

	/**
	 * 옵션삭제
	 */
	public static void optionDelete(String optionCode) {
		try {
			optionservice.optionDelete(optionCode);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	} // optionDelete
	
	/**
	 * 고객 주문에서 커피옵션추가
	 * @throws SQLException, AddException 
	 * */
	public static void orderCoffeeOption(CoffeeOption coffeeoption) throws SQLException, AddException {
		try {
		optionservice.orderCoffeeOption(coffeeoption);
		
		}catch (AddException e) {
			System.out.println(e.getMessage());
		}
	} // orderCoffeeOption end
	
	/**
	 * 고객 주문에서 디저트옵션추가
	 * */
	public static void orderDesertOption(DesertOption desertoption) throws SQLException, AddException {
		try {
		optionservice.orderDesertOption(desertoption);
		
		}catch (AddException e) {
			System.out.println(e.getMessage());
		}

	} // orderDesertOption end
	
}// class end
