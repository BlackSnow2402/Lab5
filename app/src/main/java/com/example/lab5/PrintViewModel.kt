package com.example.lab5

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.saveable

class PrintViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var pagesCount by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
        private set

    fun update(newMessage: TextFieldValue) {
        pagesCount = newMessage
    }

    var currentCost: Int = 0
        private set

    fun updateCost(newCost: Int) {
        currentCost = newCost
    }

}