package ar.edu.untref.cdm.activity.fragment;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class YearAcademicViewPagerAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> fragments;

	public YearAcademicViewPagerAdapter(FragmentManager fm,
			List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		int i = position + 1;
		return i + "° año";
	}
}