package com.aripratom.footballleaguesubmission2.ui.schedule

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.activities.ScheduleSearchActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.jetbrains.anko.support.v4.startActivity

class ScheduleFragment : Fragment() {

    private var mSectionPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val view = inflater.inflate(R.layout.fragment_schedule, container,false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mSectionPagerAdapter = SectionsPagerAdapter(childFragmentManager)

        setHasOptionsMenu(true)
        content.adapter = mSectionPagerAdapter

        content.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs ))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(content))


    }

    inner class SectionsPagerAdapter(fm : FragmentManager): FragmentPagerAdapter(fm){
        override fun getItem(position: Int): Fragment {
            when (position){
                0 -> return SchedulePreviousMatchFragment()
                1 -> return ScheduleNextMatchFragment()
            }
            return SchedulePreviousMatchFragment()
        }

        override fun getCount(): Int {
            return 2
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.search_menu_schedule,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.scheduleSearch -> {
                startActivity<ScheduleSearchActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}