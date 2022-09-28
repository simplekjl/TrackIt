package com.simplekjl.ui.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplekjl.domain.model.Weight
import com.simplekjl.ui.R
import com.simplekjl.ui.theme.base.MaterialTheme
import com.simplekjl.ui.theme.base.TrackItTheme
import com.simplekjl.ui.theme.base.TrackItTypography
import com.simplekjl.ui.theme.clearFocusOnKeyboardDismiss

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomSheet() {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var isSheetOpened by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.Transparent,
        sheetState = modalBottomSheetState,
        sheetContent = {
//            content {
//                // Action passed for clicking close button in the content
//                coroutineScope.launch {
//                    modalBottomSheetState.hide() // will trigger the LaunchedEffect
//                }
//            }
        }
    ) {}

//    BackHandler {
//        coroutineScope.launch {
//            modalBottomSheetState.hide() // will trigger the LaunchedEffect
//        }
//    }
//
//    // Take action based on hidden state
//    LaunchedEffect(modalBottomSheetState.currentValue) {
//        when (modalBottomSheetState.currentValue) {
//            ModalBottomSheetValue.Hidden -> {
//                when {
//                    isSheetOpened -> parent.removeView(composeView)
//                    else -> {
//                        isSheetOpened = true
//                        modalBottomSheetState.show()
//                    }
//                }
//            }
//            else -> {
//                Log.i(TAG, "Bottom sheet ${modalBottomSheetState.currentValue} state")
//            }
//        }
//    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun WeightContentPreview() {
    TrackItTheme {
        WeightEntryContent(
            modifier = Modifier,
            R.string.add_new_weight_title,
            Weight(0, System.currentTimeMillis(), 74.0, null)
        )
    }
}

// further reading https://developersbreach.com/modal-bottom-sheet-jetpack-compose-android/#1-creating-a-modal-bottom-sheet

@ExperimentalComposeUiApi
@Composable
fun WeightEntryContent(
    modifier: Modifier,
    @StringRes title: Int,
    weightModel: Weight,
    saveAction: () -> Unit = {}
) {
    val weight = rememberSaveable { mutableStateOf(weightModel.weight.toString()) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    Surface(
        modifier = Modifier.height(300.dp),
        color = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.small.copy(
            topStart = CornerSize(2.dp),
            topEnd = CornerSize(2.dp)
        )

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp, end = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = title),
                style = TrackItTypography().headline
            )
            Divider(
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colors.secondary
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)
                    .focusRequester(focusRequester)
                    .testTag("weightValue")
                    .clearFocusOnKeyboardDismiss(),
                value = weight.value,
                onValueChange = {
                    weight.value = it
                },
                textStyle = TrackItTypography().h6.copy(textAlign = TextAlign.Center),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.primary,
                    backgroundColor = Color.Transparent,
                    cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                ),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusRequester.freeFocus()
                    keyboardController?.hide()
                }),
            )
            Button(
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.surface,
                    disabledBackgroundColor = MaterialTheme.colors.secondary,
                    disabledContentColor = MaterialTheme.colors.onSecondary
                ),
                onClick = saveAction
            ) {
                Text(text = stringResource(R.string.save_title).uppercase())
            }
        }
    }
}
