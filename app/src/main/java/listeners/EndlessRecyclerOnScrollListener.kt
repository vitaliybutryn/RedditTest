package listeners

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {

    var isLoading = true
    var isLastPage = false
    var paginationLimit = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (!isLoading && !isLastPage) {
            if (isNeedLoad(visibleItemCount, firstVisibleItem, totalItemCount)) {
                showUpdateProgress(true)
                onLoadMore()
            }
        }
    }

    private fun isNeedLoad(visibleItemCount: Int, firstVisibleItem: Int, totalItemCount: Int): Boolean {
        return (visibleItemCount + firstVisibleItem >= totalItemCount
                && firstVisibleItem >= 0
                && totalItemCount >= paginationLimit)
    }

    abstract fun showUpdateProgress(isVisible: Boolean)

    abstract fun onLoadMore()
}