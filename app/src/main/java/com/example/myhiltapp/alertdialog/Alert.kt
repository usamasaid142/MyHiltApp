package com.example.runningapp.alertdialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object Alert {


    fun showMessage(
        context: Context,
        message: Int,
        title: Int,
        b: Boolean,
        ok: Int,
        ok1: DialogInterface.OnClickListener,
        param: Int,
        b1: DialogInterface.OnClickListener,
        param1: Boolean
    ):AlertDialog
    {
        val builder= AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle(title)
        return builder.show()
    }

    fun showMessage(context:Context,message:Int,title:Int,iscancelable:Boolean,postivemessage:Int,onpositiveclick:DialogInterface.OnClickListener):AlertDialog
    {
        val builder= AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setPositiveButton(postivemessage,onpositiveclick)
        builder.setCancelable(iscancelable)
       return builder.show()

    }

    fun showMessage(icon:Int,context:Context,message:Int,title:Int,postivemessage:Int,onpositiveclick:DialogInterface.OnClickListener
                    ,negativemessage:Int,negaitiveclick:DialogInterface.OnClickListener,iscancelable:Boolean):AlertDialog
    {
        val builder= AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setIcon(icon)
        builder.setPositiveButton(postivemessage,onpositiveclick)
        builder.setNegativeButton(negativemessage,negaitiveclick)
        builder.setCancelable(iscancelable)
        return builder.show()

    }

}