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
import mvc.view.EndView;
import mvc.view.FailView;

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
			EndView.printSelectAllOption(list);
		} catch (NotFoundException e) {

			FailView.errorMessage(e.getMessage());
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
			Option option = optionservice.optionSelectByOptionCode(optionCode);
			EndView.printOptionSelectByOptionCode(option);

		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
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
			EndView.printSelectOptionByProductCode(list);

		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
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
			EndView.printInSert();

		} catch (AddException e) {
			FailView.errorMessage(e.getMessage());

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
			FailView.errorMessage(e.getMessage());

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
			EndView.printDelete();

		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	} // optionDelete

}// class end
