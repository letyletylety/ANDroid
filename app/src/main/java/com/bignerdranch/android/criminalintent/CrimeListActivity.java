package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by lety02 on 2017. 1. 12..
 */

public class CrimeListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment()
    {
        return new CrimeListFragment();
    }
}
