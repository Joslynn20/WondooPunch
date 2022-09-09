package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;

public interface OptionService {

	/**
	 * 옵션전체검색
	 * 
	 * @return List<Option>
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Option> selectAllOption() throws SQLException, NotFoundException;

	/**
	 * 옵션코드에 대한 옵션정보검색
	 * 
	 * @param optionCode
	 * @return Option
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	Option optionSelectByOptionCode(String optionCode) throws NotFoundException, SQLException;

	/**
	 * 상품코드에 대한 옵션정보 검색
	 * 
	 * @param productCode
	 * @return Option
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Option> selectOptionByProductCode(String productCode) throws SQLException, NotFoundException;

	/**
	 * 옵션추가
	 * 
	 * @param option
	 * @throws SQLException
	 * @throws AddException
	 */
	void insertOption(Option option) throws SQLException, AddException;

	/**
	 * 옵션 수정
	 * 
	 * @param option
	 * @throws SQLException
	 * @throws ModifyException
	 */
	void updateOption(Option option) throws SQLException, ModifyException;

	/**
	 * 옵션삭제
	 * 
	 * @param optionCode
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	void deleteOption(String optionCode) throws SQLException, NotFoundException;

}// OptionService end
