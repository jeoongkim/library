package library.service;

import java.util.List;

import library.model.VolunteerException;
import library.model.vo.Volunteer;

public interface VolunteerService {
	public List<Volunteer> listAll() throws VolunteerException;
	public Volunteer selectVolunNo(Integer volunNo) throws VolunteerException; 
	public Volunteer selectUserNo(Integer userNo) throws VolunteerException; 
	public void insert(Volunteer volun) throws VolunteerException;
	public void delete(Integer volunNo) throws VolunteerException;
	public void update(Volunteer volun) throws VolunteerException;
}
