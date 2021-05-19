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
import com.junhwa.scrollableapplication.ui.getDisplayWidth
import com.junhwa.scrollableapplication.ui.goods.GoodsPagingAdapter
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
    private val goodsAdapter by lazy { GoodsPagingAdapter { vm.updateGoodsLike(it)} }

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
        vm.dispose()
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            goodsAdapter.loadStateFlow.collectLatest { loadState ->
                binding.swipeRefreshView.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.goodsData.collectLatest {
                goodsAdapter.submitData(it)
            }
        }

        vm.bannerData.observe(viewLifecycleOwner) {
            bannerAdapter.setBanner(it)
        }

        vm.loadBanner()
    }

    private fun initViews() {
        binding.swipeRefreshView.setOnRefreshListener {
            goodsAdapter.refresh()
        }

        binding.goodsRecyclerView.apply {
            adapter = goodsAdapter
            layoutManager = GridLayoutManager(context, 2).apply {
                addItemDecoration(GoodsItemDecoration(context))
            }
        }

        binding.bannerPager.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    vm.updateBannerPageChange(position)
                }
            })

            layoutParams.height = ((activity?.getDisplayWidth() ?: 0) * 0.73).toInt()
            adapter = bannerAdapter
        }

        binding.lifecycleOwner = this
        binding.bannerListener = vm
    }
}