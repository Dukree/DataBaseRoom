package com.max.roomdatabase.data


import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper

abstract class SwipeToDelete(var adapter: Context):ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT)


