package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.OptionDAO;
import mvc.dao.OptionDAOImpl;
import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;

public class OptionService {
	OptionDAO optiondao = new OptionDAOImpl();

	/**
	 * 옵션 전체검색
	 * 
	 * @throws NotFoundException
	 */
	public List<Option> optionSelect() throws SQLException, NotFoundException {
		List<Option> list = optiondao.optionSelect();
		if (list.size() == 0)
			throw new NotFoundException();
		return list;
	}// optionSelect end

	/**
	 * 옵션번호에 해당하는 옵션검색
	 * 
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public Option optionSelectByOptionCode(String optionCode) throws NotFoundException, SQLException {
		Option option = optiondao.optionSelectByOptionCode(optionCode);
		if (option == null)
			throw new NotFoundException();
		return option;

	}

	/**
	 * 옵션추가
	 * 
	 * @throws AddException
	 */
	public void optinInsert(Option option) throws SQLException, AddException {
		int result = optiondao.optionInsert(option);
		if (result == 0)
			throw new AddException();

	}// optinInsert end

	/**
	 * 옵션 수정
	 * 
	 * @throws ModifyException
	 */
	public void optionUpdate(Option option) throws SQLException, ModifyException {
		if (optiondao.optionUpdate(option) == 0)
			throw new ModifyException();
	}

	/**
	 * 옵션삭제
	 */
	public void optionDelete(String optionCode) throws SQLException {
		int result = optiondao.optionDelete(optionCode);
		if (result == 0)
			throw new SQLException();

	}

}// OptionService end
