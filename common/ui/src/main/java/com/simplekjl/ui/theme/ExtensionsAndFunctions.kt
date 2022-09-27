package com.simplekjl.ui.theme

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController

@OptIn(ExperimentalComposeUiApi::class)
fun hideKeyboardAndClearFocus(
    focusRequester: FocusRequester? = null,
    focusManager: FocusManager? = null,
    keyboardController: SoftwareKeyboardController? = null
) {
    focusRequester?.freeFocus()
    focusManager?.clearFocus(force = true)
    keyboardController?.hide()
}
