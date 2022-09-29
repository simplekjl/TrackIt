@file:OptIn(ExperimentalComposeUiApi::class)

package com.simplekjl.ui.theme.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
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
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    weight: Weight = Weight(0, 100L, 89.0, null),
    modalBottomSheetState: ModalBottomSheetState,
    isSheetOpened: MutableState<Boolean>,
    content: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetState = modalBottomSheetState,
        sheetContent = {
            WeightEntryContent(
                modifier = Modifier,
                title = R.string.add_new_weight_title,
                weightModel = weight
            )
        }
    ) {
        content()
    }
    // attached only if the view is visible
    BackHandler(isSheetOpened.value) {
        coroutineScope.launch {
            modalBottomSheetState.hide() // will trigger the LaunchedEffect
        }
    }

    // Take action based on hidden state
    LaunchedEffect(modalBottomSheetState.currentValue) {
        when (modalBottomSheetState.currentValue) {
            ModalBottomSheetValue.Hidden -> {
                modalBottomSheetState.hide()
                isSheetOpened.value = false
            }
            else -> {
                Log.i(TAG, "Bottom sheet ${modalBottomSheetState.currentValue} state")
            }
        }
    }
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
    saveAction: () -> Unit = {},
    saveEnabled: Boolean = true
) {
    val weight = rememberSaveable { mutableStateOf(weightModel.weight.toString()) }
    val date = rememberSaveable { mutableStateOf(LocalDate.now()) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val buttonFocusRequester = remember { FocusRequester() }
    val formatter = DateTimeFormatter.ofPattern(stringResource(R.string.date_dd_mm_yyyy_pattern))
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(stringResource(R.string.ok_label))
            negativeButton(stringResource(R.string.cancel_label))
        }
    ) {
        datepicker { result ->
            date.value = result
            buttonFocusRequester.freeFocus()
        }
    }
    Surface(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.small.copy(
            topStart = CornerSize(2.dp),
            topEnd = CornerSize(2.dp)
        )

    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
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

            // date picker
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        focusRequester.freeFocus()
                        if (it.isFocused) {
                            dialogState.show()
                        }
                        if (!it.hasFocus) {
                            buttonFocusRequester.requestFocus()
                        }
                    }
                    .testTag("dateValue")
                    .clearFocusOnKeyboardDismiss(),
                value = date.value.format(formatter),
                onValueChange = {},
                textStyle = TrackItTypography().h6.copy(textAlign = TextAlign.Center),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.primary,
                    backgroundColor = Color.Transparent,
                    cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                ),
                trailingIcon = {
                    IconButton(
                        modifier = Modifier,
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                null
                            )
                        },
                        onClick = {
                            dialogState.show()
                        }
                    )
                },
                maxLines = 1,
                singleLine = true,
                readOnly = true,
            )

            Button(
                enabled = saveEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(buttonFocusRequester)
                    .padding(bottom = 4.dp),
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
