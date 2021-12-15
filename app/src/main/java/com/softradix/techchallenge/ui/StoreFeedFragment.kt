package com.softradix.techchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.softradix.techchallenge.Constants
import com.softradix.techchallenge.R
import com.softradix.techchallenge.TCApplication
import com.softradix.techchallenge.ViewModelFactory
import com.softradix.techchallenge.network.model.StoreResponse
import javax.inject.Inject

/**
 * Displays the list of Stores with its title, description and the cover image to the user.
 */
class StoreFeedFragment : Fragment() {
    companion object {
        const val TAG = "StoreFeedFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<StoreFeedViewModel>

    private val viewModel: StoreFeedViewModel by lazy {
        viewModelFactory.get<StoreFeedViewModel>(
            requireActivity()
        )
    }

    private val onClick={item:StoreResponse->

        val storeFeedFragment = StoreFeedDetailFragment()
        storeFeedFragment.arguments = Bundle().apply {
            putString("ID",item.id)
        }

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.container, storeFeedFragment,
                "StoreFeedDetailFragment"
            )?.addToBackStack(null)?.commit()

        Unit
    }
    private val storeFeedAdapter: StoreFeedAdapter by lazy {
        StoreFeedAdapter(onClick)
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        TCApplication.getAppComponent().inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store_feed, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipe_container)
        // Enable if Swipe-To-Refresh functionality will be needed
        swipeRefreshLayout.isEnabled = false

        recyclerView = view.findViewById(R.id.stores_view)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = storeFeedAdapter
        }


        viewModel.getFeeds(Constants.DEFAULT_LATITUDE, Constants.DEFAULT_LONGITUDE)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.feedLiveData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                storeFeedAdapter.addData(it)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, {
            swipeRefreshLayout.isRefreshing = it
        })
    }
}
