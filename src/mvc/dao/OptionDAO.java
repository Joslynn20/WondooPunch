package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public interface OptionDAO {
	/**
	 * 옵션 전체검색
	 * 
	 * @return List<Option> 옵션 리스트
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	List<Option> selectAllOption() throws SQLException, NotFoundException;

	/**
	 * 옵션코드에 대한 옵션정보검색
	 * 
	 * @param optionCode
	 * @return Option
	 * @throws SQLException
	 */
	Option selectOptionByOptionCode(String optionCode) throws SQLException;

	/**
	 * 상품코드에 대한 옵션정보 검색
	 * 
	 * @param productCode
	 * @return Option
	 * @throws SQLException
	 */
	Option selectOptionByProductCode(String productCode) throws SQLException;

	/**
	 * 옵션추가
	 * 
	 * @param option
	 * @return int
	 * @throws SQLException
	 * @throws AddException
	 */
	int insertOption(Option option) throws SQLException, AddException;

	/**
	 * 옵션 수정
	 * 
	 * @param option
	 * @return int
	 * @throws SQLException
	 */
	int updateOption(Option option) throws SQLException;

	/**
	 * 옵션삭제
	 * 
	 * @param optionCode
	 * @return int
	 * @throws SQLException
	 */
	int deleteOption(String optionCode) throws SQLException;

} // OptionDAO end
