package com.junhwa.scrollableapplication.ui.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.junhwa.scrollableapplication.R
import com.junhwa.scrollableapplication.databinding.FragmentLikeBinding
import com.junhwa.scrollableapplication.ui.goods.GoodsItemDecoration
import com.junhwa.scrollableapplication.ui.goods.GoodsLikeAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LikeFragment : Fragment() {

    private var _binding: FragmentLikeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val goodsLikeAdapter by lazy { GoodsLikeAdapter { vm.updateGoodsLike(it)} }

    private val vm: LikeViewModel by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_like, container, false)
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
        vm.loadLikeGoods()

        vm.likeGoods.observe(viewLifecycleOwner) {
            goodsLikeAdapter.setLikeGoods(it)
        }
    }

    private fun initViews() {
        binding.likeGoodsRecyclerView.apply {
            adapter = goodsLikeAdapter
            layoutManager = GridLayoutManager(context, 2).apply {
                addItemDecoration(GoodsItemDecoration(context))
            }
        }

        binding.lifecycleOwner = this
    }
}