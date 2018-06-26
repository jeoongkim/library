package library.dao;

import library.model.UsersException;
import library.model.vo.Authority;

public interface AuthorityDao 
{
	public Authority select(Integer id) throws UsersException;
}
