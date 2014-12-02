package ar.edu.untref.cdm.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ar.edu.untref.cdm.domain.Career;
import ar.edu.untref.cdm.domain.StudyPlan;
import ar.edu.untref.cdm.domain.Subject;

public class SubjectParser {

	private String json;

	public SubjectParser(InputStream inputStream) {
		json = getJson(inputStream);
	}

	public Career parse() throws JSONException {
		JSONObject jObject = new JSONObject(json);
		JSONObject jCareer = jObject.getJSONObject("career");

		Career career = parseCareer(jCareer);

		JSONObject jStudyPlan = jCareer.getJSONObject("studyPlan");

		StudyPlan studyPlan = parseStudyPlan(jStudyPlan);

		JSONArray jSubjects = jStudyPlan.getJSONArray("subjects");
		List<Subject> subjects = parseSubjects(jSubjects);

		career.setStudyPlans(studyPlan);
		studyPlan.setSubjects(subjects);

		return career;
	}

	private StudyPlan parseStudyPlan(JSONObject jStudyPlan)
			throws JSONException {
		StudyPlan studyPlan = new StudyPlan();
		studyPlan.setName(jStudyPlan.getString("name"));
		studyPlan.setYear(jStudyPlan.getInt("year"));
		return studyPlan;
	}

	private Career parseCareer(JSONObject jCareer) throws JSONException {
		Career career = new Career();
		career.setName(jCareer.getString("name"));
		return career;
	}

	private List<Subject> parseSubjects(JSONArray jSubjects)
			throws JSONException {
		List<Subject> subjects = new ArrayList<Subject>();
		Subject subject = null;
		for (int i = 0; i < jSubjects.length(); i++) {
			subject = new Subject();
			JSONObject jSubject = jSubjects.getJSONObject(i);
			String name = jSubject.getString("name");
			Integer code = jSubject.getInt("code");
			Integer quarter = jSubject.getInt("quarter");
			Integer year = jSubject.getInt("year");
			subject.setName(name);
			subject.setCode(code);
			subject.setQuarter(quarter);
			subject.setYear(year);
			subjects.add(subject);
		}
		return subjects;
	}

	// TODO (NANO) Tratar excepciones correctamente y refactorizar.
	private String getJson(InputStream inputStream) {
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return writer.toString();
	}
}