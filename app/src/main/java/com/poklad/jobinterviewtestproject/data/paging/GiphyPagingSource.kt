package com.poklad.jobinterviewtestproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.poklad.jobinterviewtestproject.data.api.GiphyApi
import com.poklad.jobinterviewtestproject.data.model.GifItemResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GiphyPagingSource @Inject constructor(
    private val giphyApi: GiphyApi
) : PagingSource<Int, GifItemResponse>() {

    override fun getRefreshKey(state: PagingState<Int, GifItemResponse>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifItemResponse> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val offset = (page - 1) * pageSize
            val gifsList = giphyApi.getGifsList(limit = pageSize, offset = offset)

            LoadResult.Page(
                data = gifsList.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (gifsList.data.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
