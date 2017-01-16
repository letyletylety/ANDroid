package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by lety02 on 2017. 1. 15..
 */

public class CrimePagerActivity extends FragmentActivity {

    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    // CrimePagerActivity 를 실행하는 인텐트를 생성하고 반환
    public static Intent newIntent(Context packageContext, UUID crimeId)
    {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();

        // FSPA 를 생성하려면 FM 가 필요하다.
        // FSPA는 VP 와 FM 의 소통을 관리하는 중개자
        // 반환하는 프래그먼트를 우리 액티비티에 추가한다.
        // 프래그먼트의 뷰가 올바르게 위치할 수 있도록 VP 가 식별하는것을 도와준다

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            // FragmentStatePagerAdapter : 액티비티가 아닌 프래그먼트를 사용하는 PagerAdapter를 구현한 것
            // 프래그먼트의 상태도 저장하고 복원할 수 있다

            // 프래그먼트를 반환
            // 반환된 프래그먼트를 처리하려면 그것을 우리 액티비티에 추가할 수 있어야한다
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for(int i = 0; i < mCrimes.size() ; i++)
        {
            if(mCrimes.get(i).getId().equals(crimeId))
            {
                //  VP 의 현재 항목을 선택된 범죄의 인덱스로 설정
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
