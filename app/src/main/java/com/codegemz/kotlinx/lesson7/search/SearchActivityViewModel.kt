package com.codegemz.kotlinx.lesson7.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.codegemz.kotlinx.lesson7.App
import com.codegemz.kotlinx.lesson7.net.response.SearchItem
import com.codegemz.kotlinx.lesson7.repository.SearchProvider

class SearchActivityViewModel : ViewModel() {
    private val dataBase = App.dataBase
    private val searchRepository = App.searchRepository
    internal val searchResponse = MutableLiveData<List<SearchItem>>()

    fun searchRequest(searchString: String) {
        val searchEntities = dataBase.searchDao().getByQueryString(searchString)
        val searchItems = SearchProvider.fromSearchEntities(searchEntities)
        if (!searchItems.isEmpty()) {
            searchResponse.postValue(searchItems)
            return
        }

        val querySearch = searchRepository.querySearch(searchString)
            .subscribe(
                { result ->
                    searchResponse.postValue(result.query.search)
                    val searchItems = result.query.search
                    val searchEntities = SearchProvider.fromSearchItems(searchItems, searchString)
                    searchResponse.postValue(searchItems)
                    dataBase.searchDao().saveAll(searchEntities)
                },
                { error ->
                    Log.e(SearchActivityViewModel::class.qualifiedName, error.message)
                }
            )
    }
}