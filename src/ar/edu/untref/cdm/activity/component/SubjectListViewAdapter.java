package ar.edu.untref.cdm.activity.component;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ar.edu.untref.cdm.R;
import ar.edu.untref.cdm.domain.Subject;

public class SubjectListViewAdapter extends BaseAdapter {

	private List<Subject> subjects;

	public SubjectListViewAdapter(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public int getCount() {
		return subjects.size();
	}

	@Override
	public Object getItem(int position) {
		return subjects.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) parent.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_subject, parent, false);
		}
		Subject subject = subjects.get(position);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		name.setText(subject.getName());
		return convertView;
	}

}
