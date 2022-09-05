package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;
import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface OptionDAO {
	/**
	 * 옵션 전체검색
	 */
	List<Option> optionSelect() throws SQLException, NotFoundException;

	/**
	 * optionCode에 대한 정보검색
	 */
	Option optionSelectByOptionCode(String optionCode) throws SQLException;

	/**
	 * 옵션추가
	 */
	int optionInsert(Option option) throws SQLException, AddException;

	/**
	 * 옵션 수정
	 */
	int optionUpdate(Option option) throws SQLException;

	/**
	 * 옵션삭제
	 */
	int optionDelete(String optionCode) throws SQLException;

	/**
	 * 고객 주문에서 커피옵션추가
	 */
	int orderCoffeeOption(CoffeeOption coffeeoption) throws SQLException, AddException;

	/**
	 * 고객 주문에서 디저트옵션추가
	 */
	int orderDesertOption(DesertOption desertoption) throws SQLException, AddException;

} // OptionDAO end
