package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Option;

public interface OptionDAO {
	/**
	 * 옵션 전체검색
	 */
	List<Option> optionSelect() throws SQLException;

	/**
	 * optionCode에 대한 정보검색
	 */
	Option optionSelectByOptionCode(String optionCode) throws SQLException;

	/**
	 * 옵션추가
	 */
	int optionInsert(Option option) throws SQLException;

	/**
	 * 옵션 수정
	 */
	int optionUpdate(Option option) throws SQLException;

	/**
	 * 옵션삭제
	 */
	int optionDelete(String optionCode) throws SQLException;

	
	
} // OptionDAO end
