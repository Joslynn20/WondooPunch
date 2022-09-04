package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import jdy.dto.Option;
import jdy.exception.NotFoundException;
import jdy.service.OptionService;
//import kosta.mvc.view.FailView;

public class OptionController {
	public static OptionService optionservice = new OptionService();
	public static Option option;
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
	}

}// class end
