package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.UsersException;
import library.model.vo.Users;

@Repository
public class UsersDaoImpl implements UsersDao
{
	private static final String MAPPER_NS=Users.class.getName();

	@Autowired
	private SqlSession session;

	public UsersDaoImpl() {}

	@Override
	public List<Users> selectAll() throws UsersException 
	{
		List<Users> list=null;

		try {
			list=session.selectList(MAPPER_NS+".select-all-users");
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		return list;
	}

	@Override
	public Users select(Integer userNo) throws UsersException 
	{
		Users users=null;

		try {
			users=session.selectOne(MAPPER_NS+".select-users",userNo);
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		return users;
	}


	@Override
	public void insert(Users users) throws UsersException {
		try {
			session.insert(MAPPER_NS + ".insert-users", users);

		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public void delete(Users users) throws UsersException 
	{
		try {
			session.delete(MAPPER_NS+".delete-users",users);
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public void update(Users users) throws UsersException 
	{
		try {
			users=session.selectOne(MAPPER_NS+".update-users",users);
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public Users selectByEmail(String email) throws UsersException 
	{
		Users users = null;

		try {
			users = session.selectOne(MAPPER_NS + ".select-users-by-email", email);

		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}

		return users;
	}

	@Override
	public void insertAuthority(Users users) {
		try {
			session.insert(MAPPER_NS + ".insert-authority", users);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public Integer selectLastInsertId() throws UsersException {
		Integer lastInsertId = null;
		
		try {
			lastInsertId = session.selectOne(MAPPER_NS + ".select-last-insert-id");
			
		}catch(Exception e) {
			throw new UsersException(e.getMessage());
		}
		return lastInsertId;
	}

	@Override
	public String selectName(Integer userNo) throws UsersException {
		String name = null;
		
		try {
			name = session.selectOne(MAPPER_NS + ".select-usersName-users");
			
		}catch(Exception e) {
			throw new UsersException(e.getMessage());
		}
		return name;
	}

	@Override
	public void updateMember(Integer userNo) throws UsersException {
		try {
			session.selectOne(MAPPER_NS + ".update-member", userNo);
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public void updateAuthority(Users users) throws UsersException {
		try {
			session.selectOne(MAPPER_NS + ".update-authority", users);
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public List<Users> stopDateSelect() throws UsersException {
		List<Users> users = null;
		try {
			users = session.selectList(MAPPER_NS + ".select-usersStopDate-users");
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}

		return users;
	}

}
