package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.VolunteerException;
import library.model.vo.Volunteer;

@Repository
public class VolunteerDaoImpl implements VolunteerDao{
	private static final String MAPPER_NS = Volunteer.class.getName();
	
	@Autowired
	private SqlSession session;
	public VolunteerDaoImpl() {}

	@Override
	public List<Volunteer> listAll() throws VolunteerException {
		List<Volunteer> list = null;
		try {
			list = session.selectList(MAPPER_NS + ".select-list-all");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new VolunteerException(e.getMessage());
		}
		return list;
	}

	@Override
	public Volunteer selectVolunNo(Integer volunNo) throws VolunteerException {
		Volunteer volun = new Volunteer();
		try {
			volun = session.selectOne(MAPPER_NS + ".select-list-volunNo", volunNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new VolunteerException(e.getMessage());
		}
		return volun;
	}

	@Override
	public Volunteer selectUserNo(Integer userNo) throws VolunteerException {
		Volunteer volun = new Volunteer();
		try {
			volun = session.selectOne(MAPPER_NS + ".select-userNo", userNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new VolunteerException(e.getMessage());
		}
		return volun;
	}

	@Override
	public void insert(Volunteer volun) throws VolunteerException {
		try {
			session.insert(MAPPER_NS + ".insert-volun", volun);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new VolunteerException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer volunNo) throws VolunteerException {
		try {
			session.delete(MAPPER_NS + ".delete-volun", volunNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new VolunteerException(e.getMessage());
		}
	}

	@Override
	public void update(Volunteer volun) throws VolunteerException {
		try {
			session.update(MAPPER_NS + ".update-volun", volun);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new VolunteerException(e.getMessage());
		}
	}

}
