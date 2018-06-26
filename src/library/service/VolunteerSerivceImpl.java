package library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.dao.VolunteerDao;
import library.model.VolunteerException;
import library.model.vo.Volunteer;

@Service
@Transactional
public class VolunteerSerivceImpl implements VolunteerService{
	
	public VolunteerSerivceImpl() {}
	@Autowired
	private VolunteerDao dao;

	@Override
	public List<Volunteer> listAll() throws VolunteerException {
		return dao.listAll();
	}

	@Override
	public Volunteer selectVolunNo(Integer volunNo) throws VolunteerException {
		return dao.selectVolunNo(volunNo);
	}

	@Override
	public Volunteer selectUserNo(Integer userNo) throws VolunteerException {
		return dao.selectUserNo(userNo);
	}

	@Override
	public void insert(Volunteer volun) throws VolunteerException {
		dao.insert(volun);
	}

	@Override
	public void delete(Integer volunNo) throws VolunteerException {
		dao.delete(volunNo);
	}

	@Override
	public void update(Volunteer volun) throws VolunteerException {
		dao.update(volun);
	}

}
