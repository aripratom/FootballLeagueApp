package com.aripratom.footballleaguesubmission2.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aripratom.footballleaguesubmission2.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)

        viewpagerFavorite.adapter = mSectionsPagerAdapter

        viewpagerFavorite.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabsTeam))
        tabsTeam.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpagerFavorite))
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return FavoriteScheduleFragment()
                1 -> return FavoriteTeamFragment()
            }
            return FavoriteScheduleFragment()
        }

        override fun getCount(): Int {
            return 2
        }
    }


}
