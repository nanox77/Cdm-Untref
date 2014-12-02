package ar.edu.untref.cdm.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import ar.edu.untref.cdm.R;
import ar.edu.untref.cdm.activity.fragment.GenericFragment;
import ar.edu.untref.cdm.activity.fragment.YearAcademicFragment;
import ar.edu.untref.cdm.activity.fragment.YearAcademicViewPagerAdapter;
import ar.edu.untref.cdm.dao.SubjectParser;
import ar.edu.untref.cdm.domain.Career;
import ar.edu.untref.cdm.domain.Subject;
import ar.edu.untref.cdm.service.SubjectService;

public class MainActivity extends ActionBarActivity {

	private SubjectService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initDataBase();
		initViewPager();
	}

	private InputStream getInputStream() {
		return getResources().openRawResource(R.raw.plan_estudio_generico);
	}

	private void initViewPager() {
		ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		YearAcademicViewPagerAdapter yearAcademicAdapter = new YearAcademicViewPagerAdapter(
				getSupportFragmentManager(), getFragments());
		viewPager.setAdapter(yearAcademicAdapter);
	}

	private List<GenericFragment> getFragments() {
		List<GenericFragment> fragments = new ArrayList<GenericFragment>();
		Integer yearAcademics = service.getYearAcademic();
		for (int i = 1; i <= yearAcademics; i++) {
			List<Subject> subjectsByYear = service.findSubjectsByYear(i);
			fragments.add(new YearAcademicFragment(subjectsByYear));
		}
		return fragments;
	}

	// TODO (NANO) Refactorizar, quitar el IF.
	// Tambien el For y el Parser de esta clase.
	// Idealmente habria que hacer un sistema de validacion.
	private void initDataBase() {
		service = new SubjectService(MainActivity.this);
		if (!service.isExist()) {
			SubjectParser parser = new SubjectParser(getInputStream());
			Career career = null;
			try {
				career = parser.parse();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			for (Subject subject : career.getStudyPlan().getSubjects()) {
				service.create(subject);
			}
		}
	}

}
