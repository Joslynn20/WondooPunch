package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.OptionDAO;
import mvc.dao.OptionDAOImpl;
import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;

public class OptionServiceImpl implements OptionService {

	OptionDAO optiondao = new OptionDAOImpl();

	/**
	 * 옵션 전체검색
	 * 
	 * @throws NotFoundException
	 */
	public List<Option> selectAllOption() throws SQLException, NotFoundException {
		List<Option> list = optiondao.selectAllOption();
		if (list.isEmpty() || list.size() == 0)
			throw new NotFoundException("옵션 목록이 없습니다");
		return list;
	}// optionSelect end

	/**
	 * 옵션코드에 대한 옵션정보검색
	 * 
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public Option optionSelectByOptionCode(String optionCode) throws NotFoundException, SQLException {
		Option option = optiondao.optionSelectByOptionCode(optionCode);
		if (option == null)
			throw new NotFoundException("옵션 목록이 없습니다");
		return option;

	}

	/**
	 * 상품코드에 대한 옵션정보 검색
	 * 
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public List<Option> selectOptionByProductCode(String productCode) throws SQLException, NotFoundException {
		List<Option> list = optiondao.selectOptionByProductCode(productCode);
		if (list.isEmpty() || list.size() == 0)
			throw new NotFoundException("옵션 목록이 없습니다");
		return list;
	}

	/**
	 * 옵션추가
	 * 
	 * @throws AddException
	 */
	public void insertOption(Option option) throws SQLException, AddException {
		int result = optiondao.insertOption(option);
		if (result == 0)
			throw new AddException("옵션등록 실패했습니다");

	}// optinInsert end

	/**
	 * 옵션 수정
	 * 
	 * @throws ModifyException
	 */
	public void updateOption(Option option) throws SQLException, ModifyException {
		if (optiondao.updateOption(option) == 0)
			throw new ModifyException("옵션수정 실패했습니다");
	}

	/**
	 * 옵션삭제
	 * 
	 * @throws NotFoundException
	 */
	public void deleteOption(String optionCode) throws SQLException, NotFoundException {
		int result = optiondao.deleteOption(optionCode);
		if (result == 0)
			throw new NotFoundException("옵션삭제 실패했습니다");

	}
}
