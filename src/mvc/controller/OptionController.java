package mvc.controller;

import java.sql.SQLException;
import java.util.List;

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
	private static List<Option> list;
	public static Orders order;

	/**
	 * 옵션전체검색
	 * 
	 * @throws SQLException, NotFoundException
	 */
	public static void selectAllOption() throws SQLException, NotFoundException {
		try {
			List<Option> list = optionservice.selectAllOption();
			System.out.println(list);

		} catch (NotFoundException e) {

			System.out.println(e.getMessage());
		} // catch end
	} // optionSelect end

	/**
	 * 옵션코드에 대한 옵션정보검색
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static void optionSelectByOptionCode(String optionCode) throws NotFoundException, SQLException {
		try {
			List<Option> list = optionservice.optionSelectByOptionCode(optionCode);
			System.out.println(list);

		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 상품코드에 대한 옵션정보 검색
	 * 
	 * @throws SQLException
	 */
	public static void selectOptionByProductCode(String productCode) throws SQLException, NotFoundException {
		try {
			List<Option> list = optionservice.selectOptionByProductCode(productCode);
			System.out.println(list);

		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 옵션추가
	 * 
	 * @throws SQLException
	 */

	public static void insertOption(Option option) throws SQLException, AddException {
		try {
			optionservice.insertOption(option);

		} catch (AddException e) {
			System.out.println(e.getMessage());

		}
	} // optionInsert end

	/**
	 * 옵션 수정
	 * 
	 * @throws SQLException
	 */
	public static void updateOption(Option option) throws SQLException {

		try {
			optionservice.updateOption(option);

		} catch (ModifyException e) {
			System.out.println(e.getMessage());

		}
	}// optionUpdate end

	/**
	 * 옵션삭제
	 * 
	 * @throws NotFoundException
	 */
	public static void deleteOption(String optionCode) throws SQLException, NotFoundException {
		try {
			optionservice.deleteOption(optionCode);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	} // optionDelete

}// class end
