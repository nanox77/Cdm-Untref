package ar.edu.untref.cdm.activity.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import ar.edu.untref.cdm.R;
import ar.edu.untref.cdm.activity.component.SubjectListViewAdapter;
import ar.edu.untref.cdm.domain.Subject;

public class YearAcademicFragment extends Fragment {

	private List<Subject> subjects;

	public YearAcademicFragment(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_year_academic, container, false);
		ListView subjectListView = (ListView) rootView.findViewById(R.id.subjects);
		SubjectListViewAdapter adapter = new SubjectListViewAdapter(subjects);
		subjectListView.setAdapter(adapter);
		return rootView;
	}

}