package com.example.base.base

import com.example.base.base.interfaces.BaseHolderModel

abstract class BasePagingViewModel<M : BaseHolderModel> : BaseViewModel() {

    protected abstract val currentList: List<M>

}