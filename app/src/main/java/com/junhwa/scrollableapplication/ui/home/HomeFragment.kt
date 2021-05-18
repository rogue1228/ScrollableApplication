package com.junhwa.scrollableapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.junhwa.scrollableapplication.R
import com.junhwa.scrollableapplication.databinding.FragmentHomeBinding
import com.junhwa.scrollableapplication.ui.goods.GoodsAdapter
import com.junhwa.scrollableapplication.ui.goods.GoodsItemDecoration
import com.junhwa.scrollableapplication.ui.home.banner.BannerPagerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val bannerAdapter = BannerPagerAdapter()
    private val goodsAdapter = GoodsAdapter {

    }

    private val vm: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root: View = binding.root



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        vm.bannerData.observe(viewLifecycleOwner) {
            bannerAdapter.setBanner(it)
        }

        _binding?.swipeRefreshView?.setOnRefreshListener {
            goodsAdapter.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            goodsAdapter.loadStateFlow.collectLatest { loadState ->
                _binding?.swipeRefreshView?.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.goodsData.collectLatest {
                goodsAdapter.submitData(it)
            }
        }
    }

    private fun initViews() {
        _binding?.goodsRecyclerView?.apply {
            adapter = goodsAdapter
            layoutManager = GridLayoutManager(context, 2).apply {
                addItemDecoration(GoodsItemDecoration(context))
            }
        }

        _binding?.bannerPager?.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    vm.updateBannerPageChange(position)
                }
            })

            adapter = bannerAdapter
        }

        _binding?.lifecycleOwner = this
        _binding?.bannerListener = vm
    }
}