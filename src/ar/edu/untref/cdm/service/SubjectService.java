package ar.edu.untref.cdm.service;

import java.io.File;
import java.util.List;

import android.content.Context;
import ar.edu.untref.cdm.dao.SubjectDAO;
import ar.edu.untref.cdm.domain.Subject;
import static ar.edu.untref.cdm.util.DataBaseContstant.KEY_DATABASE;

public class SubjectService {

	private SubjectDAO subjectDAO;
	private Context context;

	public SubjectService(Context context) {
		this.context = context;
		subjectDAO = new SubjectDAO(context);
	}

	public Boolean isExist() {
		File database = context.getDatabasePath(KEY_DATABASE);
		return database.exists();
	}

	public void create(Subject subject) {
		subjectDAO.create(subject);
	}

	public List<Subject> findSubjectsByYear(Integer year) {
		return subjectDAO.findSubjectsByYear(year);
	}

	public Integer getYearAcademic() {
		return subjectDAO.getYearAcademic();
	}
}