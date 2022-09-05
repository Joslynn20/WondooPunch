package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;
import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;
import mvc.service.OptionService;
import mvc.service.OptionServiceImpl;


public class OptionController {
	public static OptionService optionservice = new OptionServiceImpl();
	public static Option option;
	public static CoffeeOption coffeeoption;
	private static List<Option> list;

	/**
	 * 옵션목록
	 * @throws SQLException, NotFoundException 
	 */
	public static void optionSelect() throws SQLException, NotFoundException {
		try {
			List<Option> list = optionservice.optionSelect();
			
			System.out.println(list);
//			 EndView.printOptionList(list);
			
		} catch (NotFoundException e) {
			System.out.println("실패");
//			FailView.erroMessage(e.getMessage());
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
			e.printStackTrace();
		}		
	}	
	
	/**
	 * 옵션추가
	 */

	public static void optionInsert(Option option) {
		try {
			optionservice.optinInsert(option);
			System.out.println("옵션추가 성공");
//			SuccessView.messagePrint();
		} catch (Exception e) {
			System.out.println("옵션추가 실패");
		//	e.printStackTrace();
		}
	} // optionInsert end

	/**
	 * 옵션 수정
	 */
	public static void optionUpdate(Option option) {

		try {
			optionservice.optionUpdate(option);
			System.out.println("수정 성공");
//			SuccessView.messagePrint("수정되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정 실패");
//			FailView.errorMessage(e.getMessage());
		}
	}// optionUpdate end

	/**
	 * 옵션삭제
	 */
	public static void optionDelete(String optionCode) {
		try {
			optionservice.optionDelete(optionCode);
			System.out.println("삭제 성공");
//			SuccessView.messagePrint("수정되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("삭제 실패");
//			FailView.errorMessage(e.getMessage());
		}
	} // optionDelete
	
	/**
	 * 고객 주문에서 커피옵션추가
	 * @throws SQLException, AddException 
	 * */
	public static void orderCoffeeOption(CoffeeOption coffeeoption) throws SQLException, AddException {
		try {
		optionservice.orderCoffeeOption(coffeeoption);
		System.out.println("커피옵션추가 성공");
		}catch (AddException e) {
			System.out.println("커피옵션추가 실패");
			e.printStackTrace();
		}
	} // orderCoffeeOption end
	
	/**
	 * 고객 주문에서 디저트옵션추가
	 * */
	public static void orderDesertOption(DesertOption desertoption) throws SQLException, AddException {
		try {
		optionservice.orderDesertOption(desertoption);
		System.out.println("커피옵션추가 성공");
		}catch (AddException e) {
			System.out.println("커피옵션추가 실패");
			e.printStackTrace();
		}
	} // orderDesertOption end
	
	/**
	 *  장바구니커피옵션확인
	 * */
	
	
	
	
	
	
	

}// class end
