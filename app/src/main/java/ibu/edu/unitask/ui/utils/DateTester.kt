package ibu.edu.unitask.ui.utils

import androidx.compose.ui.graphics.Color
import java.util.Date

fun DateTester(date1: Date, date2: Date): Color{
    return if(date1 > date2) Color.Black else Color.Red
}