package com.example.android.miwok;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CategoryPagerAdapter extends FragmentStateAdapter {

    public CategoryPagerAdapter(FragmentManager fm, Lifecycle lifecycle)
    {
        super(fm,lifecycle);
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            NumbersFragment NumberFragment = NumbersFragment.newInstance();
            return NumberFragment;
        }
        else if(position == 1) {
            ColorsFragment ColorFragment = ColorsFragment.newInstance();
            return ColorFragment;
        }
        else if(position == 2) {
            FamilyFragment page3 = FamilyFragment.newInstance();
            return page3;
        }
        else{
                PhrasesFragment PhraseFragment = PhrasesFragment.newInstance();
                return PhraseFragment;
        }
    }
}
