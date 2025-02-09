package com.mihaidornea.maily.utils

import com.mihaidornea.maily.shared.DomainResult
import com.mihaidornea.maily.shared.Failure
import com.mihaidornea.maily.shared.Success

class Paginator<Key, Item>(
    initialKey: Key,
    private val onRequest: suspend (nextKey: Key) -> DomainResult<List<Item>>,
    private val onNextKey: suspend () -> Key,
    private val onError: suspend () -> Unit,
    private val onSuccess: suspend (items: List<Item>, key: Key) -> Unit,
    private val onReset: suspend () -> Unit,
    private val isLoading: suspend (isLoading: Boolean) -> Unit
) {

    private var currentKey = initialKey
    private var issMakingRequest = false
    private var resetKey = initialKey

    suspend fun loadNextItems(
        hasToReset: Boolean = false
    ): DomainResult<List<Item>>? {
        if (issMakingRequest) {
            return null
        }
        issMakingRequest = true
        isLoading(true)
        if (hasToReset) {
            currentKey = resetKey
            onReset()
        }

        return onRequest(currentKey).also { result ->
            issMakingRequest = false
            isLoading(false)
            when (result) {
                is Failure -> {
                    onError()
                }

                is Success -> {
                    if (result.data.isNotEmpty()) {
                        currentKey = onNextKey()
                    }
                    onSuccess(result.data, currentKey)
                }
            }
        }
    }
}
