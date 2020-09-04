package k.c.mapcardviewdemo.app.base.dialog

import android.app.Activity
import android.content.Context
import k.c.mapcardviewdemo.R


class ShowDialogManager {

    companion object {

        var progressDialog: AestheticDialog.Builder? = null


        fun showProgressDialog(context: Context, title: String, message: String, isProgressBarEnable: Boolean) {

            progressDialog?.dismiss()

            progressDialog = AestheticDialog.Builder(context as Activity, DialogStyle.PROGRESS)
            progressDialog!!.setTitle(title)
                .setMessage(message)
                .setProgressBarEnable(isProgressBarEnable)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .show()
        }

        fun hideProgressDialog() {

            progressDialog?.dismiss()
        }


        fun showHintDialog(context: Context, message: String) {

            showHintDialog(context, message, false, null)

        }


        fun showHintDialog(context: Context, message: String, isCloseButtonEnable: Boolean?, onDialogClickListener: OnDialogClickListener?) {

            showHintDialog(context, message, context.getString(R.string.notification_title), isCloseButtonEnable, onDialogClickListener)
        }

        fun showHintDialog(context: Context, message: String, title: String, isCloseButtonEnable: Boolean?, onDialogClickListener: OnDialogClickListener?) {

            showHintDialog(context, message, title, isCloseButtonEnable, context.getString(R.string.confirm), onDialogClickListener)
        }
        fun showHintDialog(context: Context, message: String, title: String, isCloseButtonEnable: Boolean?, confirmButton: String, onDialogClickListener: OnDialogClickListener?) {


            val builder = AestheticDialog.Builder(context as Activity, DialogStyle.NOTIFICATION)

            if (onDialogClickListener != null) {
                builder.setOnClickListener(onDialogClickListener)
            }

            builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setConfirmButtonText(confirmButton)
                .setCanceledOnTouchOutside(false)
                .setCloseImageEnable(isCloseButtonEnable!!)
                .show()

        }

        fun showTwoButtonHintDialog(context: Context, message: String) {

            showTwoButtonHintDialog(context, message, context.getString(R.string.notification_title))
        }

        fun showTwoButtonHintDialog(context: Context, message: String, title: String) {


            showTwoButtonHintDialog(context, message, title, null)

        }

        fun showTwoButtonHintDialog(context: Context, message: String, title: String, onDialogClickListener: OnDialogClickListener?) {


            showTwoButtonHintDialog(context, message, title, context.getString(R.string.confirm), onDialogClickListener)

        }


        fun showTwoButtonHintDialog(context: Context, message: String, title: String, confirmButton: String, onDialogClickListener: OnDialogClickListener?) {

            showTwoButtonHintDialog(context, message, title, confirmButton, context.getString(R.string.cancel), onDialogClickListener)
        }


        fun showTwoButtonHintDialog(context: Context, message: String, title: String, confirmButton: String, cancelButton: String, onDialogClickListener: OnDialogClickListener?) {


            val builder = AestheticDialog.Builder(context as Activity, DialogStyle.NOTIFICATION)

            if (onDialogClickListener != null) {
                builder.setOnClickListener(onDialogClickListener)
            }

            builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setConfirmButtonText(confirmButton)
                .setCancellButtonText(cancelButton)
                .setCloseImageEnable(false)
                .setCanceledOnTouchOutside(false)
                .setTwoButtonModeEnable(true)
                .show()
        }






        fun showToasterHint(context: Context, message: String, title: String){

            val builder = AestheticDialog.Builder(context as Activity, DialogStyle.TOP_NOTIFICATION)



            builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .show()

        }






        fun showConnectErrorDialog(context: Context?, title: String, message: String, onDialogClickListener: OnDialogClickListener?) {

            if (context == null) {
                return
            }

            val builder = AestheticDialog.Builder((context as Activity?)!!, DialogStyle.NOTIFICATION)

            if (onDialogClickListener != null) {
                builder.setOnClickListener(onDialogClickListener)
            }


            builder.setTitle(title).setMessage(message).setCloseImageEnable(false).setCancelable(false).setConfirmButtonText(context.getString(
                R.string.close)).show()


        }

    }
}