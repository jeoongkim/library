package library.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.UsersException;
import library.model.vo.Authority;
@Repository
public class AuthorityDaoImpl implements AuthorityDao
{
	private static final String MAPPER_NS=Authority.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public AuthorityDaoImpl() {}
	
	@Override
	public Authority select(Integer id) throws UsersException 
	{
		Authority authority = null;
		
		try {
			authority = session.selectOne(MAPPER_NS + ".select-authority", id);
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return authority;
	}

}
