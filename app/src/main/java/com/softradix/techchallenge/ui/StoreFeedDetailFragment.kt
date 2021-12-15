package com.softradix.techchallenge.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.softradix.techchallenge.R
import com.softradix.techchallenge.TCApplication
import com.softradix.techchallenge.ViewModelFactory
import javax.inject.Inject


class StoreFeedDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<StoreFeedViewModel>

    private val viewModel: StoreFeedViewModel by lazy {
        viewModelFactory.get<StoreFeedViewModel>(
            requireActivity()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TCApplication.getAppComponent().inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_feed_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val path = arguments?.getString("ID")?:""
        Log.e("PATH",path)
        viewModel.getFeedsDetails(path)
        viewModel.detailLiveData.observe(viewLifecycleOwner, {
            view.findViewById<TextView>(R.id.tvView).text = it.name+"\n${it.description}"
            if (it.coverImgUrl.isNotEmpty()) {
                activity?.let { it1 -> Glide.with(it1).load(it.coverImgUrl).into(view.findViewById<AppCompatImageView>(R.id.imageView)) }
            }
        })
    }
}