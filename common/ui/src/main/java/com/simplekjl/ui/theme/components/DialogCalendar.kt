package com.simplekjl.ui.theme.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.simplekjl.ui.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate

@Composable
fun CalendarDialog(
    dialogState: MaterialDialogState,
    dateListener: (LocalDate) -> Unit
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(stringResource(R.string.ok_label))
            negativeButton(stringResource(R.string.cancel_label))
        }
    ) {
        datepicker { date ->
            dateListener(date)
        }
    }
}
