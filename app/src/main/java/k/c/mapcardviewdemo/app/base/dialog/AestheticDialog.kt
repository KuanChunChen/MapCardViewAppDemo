package k.c.mapcardviewdemo.app.base.dialog

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import k.c.mapcardviewdemo.R


@Keep
class AestheticDialog {

    class Builder(@NonNull private val activity: Activity,
                  @NonNull private val dialogStyle: String){

        lateinit var alertDialog: AlertDialog
        lateinit var popupWindow: PopupWindow

        private val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)

        private var title: String = "Title"
        private var message: String = "Message"
        private var imageUrl: String? = null
        private var btnConfirmText: String = "確認"
        private var btnCancelText: String = "取消"


        private var isCanceledOnTouchOutside: Boolean = true
        private var isCancelable: Boolean = true
        private var isCloseImageEnable: Boolean = true

        private var isProgressBarEnable: Boolean = true


        private var isTwoButtonMode: Boolean = false

        private lateinit var layoutView: View
        private var onClickListener: OnDialogClickListener = object : OnDialogClickListener {
            override fun onButtonClick(dialog: Builder) {
                dialog.dismiss()

            }

            override fun onCloseClick(dialog: Builder) {
                dialog.dismiss()
            }
        }


        @NonNull
        fun setTitle(@NonNull title: String): Builder {
            this.title = title
            return this
        }

        @NonNull
        fun setMessage(@NonNull message: String): Builder {
            this.message = message
            return this
        }

        @NonNull
        fun setConfirmButtonText(@NonNull btnText: String): Builder {
            this.btnConfirmText = btnText
            return this
        }


        @NonNull
        fun setCancellButtonText(@NonNull btnText: String): Builder {
            this.btnCancelText = btnText
            return this
        }


        @NonNull
        fun setOnClickListener(onDialogClickListener: OnDialogClickListener): Builder {
            this.onClickListener = onDialogClickListener
            return this
        }

        @NonNull
        fun setImage(@NonNull imageUrl: String?): Builder {
            this.imageUrl = imageUrl
            return this
        }


        @NonNull
        fun setCancelable(@NonNull isCancelable: Boolean): Builder {
            this.isCancelable = isCancelable
            return this
        }


        @NonNull
        fun setCanceledOnTouchOutside(@NonNull isCanceledOnTouchOutside: Boolean): Builder {
            this.isCanceledOnTouchOutside = isCanceledOnTouchOutside
            return this
        }

        @NonNull
        fun setCloseImageEnable(@NonNull isEnable: Boolean): Builder {
            this.isCloseImageEnable = isEnable
            return this
        }

        @NonNull
        fun setProgressBarEnable(@NonNull isEnable: Boolean): Builder {
            this.isProgressBarEnable = isEnable
            return this
        }

        @NonNull
        fun setTwoButtonModeEnable(@NonNull isEnable: Boolean): Builder {
            this.isTwoButtonMode = isEnable
            return this
        }


        @NonNull
        fun dismiss(): AestheticDialog {
            alertDialog.dismiss()
            return AestheticDialog()
        }

        private fun autoReSizeEvent(text : AppCompatTextView){

            text.viewTreeObserver.addOnGlobalLayoutListener {
                val maxLine = 5
                if (text.lineCount >= maxLine) {
                    TextViewCompat.setAutoSizeTextTypeWithDefaults(text, TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM)

                }
            }
        }


        @NonNull
        fun show(): AestheticDialog {

            when (dialogStyle) {
                DialogStyle.NOTIFICATION -> {
                    layoutView = activity.layoutInflater.inflate(R.layout.dialog_notification, null)


                    val icon: AppCompatImageView = layoutView.findViewById(R.id.image_main_subject)
                    val imgClose: AppCompatImageView = layoutView.findViewById(R.id.image_close)
                    val textTitle: AppCompatTextView = layoutView.findViewById(R.id.dialog_title)
                    val textMessage: AppCompatTextView = layoutView.findViewById(R.id.dialog_message)
                    val btnAction: AppCompatButton = layoutView.findViewById(R.id.button_action)


                    if (isTwoButtonMode){
                        imgClose.visibility = View.GONE
                        btnAction.visibility = View.GONE

                        val linearButton: LinearLayout = layoutView.findViewById(R.id.linear_two_button_view)
                        val btnClose: AppCompatButton = layoutView.findViewById(R.id.button_close)
                        val btnConfirm: AppCompatButton = layoutView.findViewById(R.id.button_confirm)

                        linearButton.visibility = View.VISIBLE

                        btnClose.setOnClickListener { onClickListener.onCloseClick(this) }
                        btnConfirm.setOnClickListener { onClickListener.onButtonClick(this) }

                        btnClose.text = btnCancelText
                        btnConfirm.text = btnConfirmText
                    }


                    var minusImageHeight = 0
                    var minusButtonHeight = 0



                    textTitle.text = title
                    textMessage.text = message
                    btnAction.text = btnConfirmText


                    autoReSizeEvent(textMessage)


                    imgClose.setOnClickListener { onClickListener.onCloseClick(this) }
                    btnAction.setOnClickListener { onClickListener.onButtonClick(this) }

                    Glide.with(activity).load(imageUrl).into(icon)


                    dialogBuilder.setView(layoutView)

                    alertDialog = dialogBuilder.create()
                    alertDialog.setCancelable(isCancelable)
                    alertDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside)
                    alertDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
                    alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    alertDialog.show()

                    if (!isCloseImageEnable) {

                        imgClose.visibility = View.GONE
                    }


                    if (imageUrl == null) {
                        icon.visibility = View.GONE
                        minusImageHeight = activity.resources.getDimensionPixelSize(R.dimen.gap_15x)

                    }

                    val height = (activity.resources.getDimensionPixelSize(R.dimen.gap_43x) - minusButtonHeight - minusImageHeight)
                    alertDialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, height)


                }
                DialogStyle.ADD_CREDIT_CARD -> {
                    layoutView = activity.layoutInflater.inflate(R.layout.dialog_add_credit_card, null)

                    val tvDialogTitle: TextView = layoutView.findViewById(R.id.credit_card_message_title)
                    val tvDialogContent: TextView = layoutView.findViewById(R.id.credit_card_message)
                    val btnOk: Button = layoutView.findViewById(R.id.credit_ok)
                    val btnCancel: Button = layoutView.findViewById(R.id.credit_cancel)
                    val cbNoMoreHint: CheckBox = layoutView.findViewById(R.id.credit_check)


                    tvDialogContent.maxLines = 5
                    tvDialogContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, activity.resources.getDimension(R.dimen.text_16))
                    tvDialogTitle.text = title
                    tvDialogContent.text = message
                    btnCancel.setOnClickListener { onClickListener.onCloseClick(this) }
                    btnOk.setOnClickListener { onClickListener.onButtonClick(this) }
                    cbNoMoreHint.setOnCheckedChangeListener { _, _ ->  }

                    dialogBuilder.setView(layoutView)

                    alertDialog = dialogBuilder.create()
                    alertDialog.setCancelable(isCancelable)
                    alertDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside)
                    alertDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
                    alertDialog.show()

                }
                DialogStyle.PROGRESS -> {
                    layoutView = activity.layoutInflater.inflate(R.layout.dialog_progress, null)

                    val progressBar: ProgressBar = layoutView.findViewById(R.id.dialog_progressBar)
                    val textTitle: AppCompatTextView = layoutView.findViewById(R.id.dialog_title)
                    val textMessage: AppCompatTextView = layoutView.findViewById(R.id.dialog_message)


                    textTitle.text = title
                    textMessage.text = message

                    autoReSizeEvent(textMessage)

                    if (!isProgressBarEnable) {
                        progressBar.visibility = View.GONE
                    }

                    dialogBuilder.setView(layoutView)

                    alertDialog = dialogBuilder.create()
                    alertDialog.setCancelable(isCancelable)
                    alertDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside)
                    alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    alertDialog.show()

                }
                DialogStyle.TOP_NOTIFICATION -> {
                    layoutView = activity.layoutInflater.inflate(R.layout.dialog_toaster, null)

                    val textTitle: AppCompatTextView = layoutView.findViewById(R.id.dialog_title)
                    val textMessage: AppCompatTextView = layoutView.findViewById(R.id.dialog_message)
                    val imgClose: AppCompatImageView = layoutView.findViewById(R.id.image_close)
                    val imgIcon: AppCompatImageView = layoutView.findViewById(R.id.dialog_icon)


//                    Glide.with(activity).load(imageUrl).into(imgIcon)



                    textTitle.text = title
                    textMessage.text = message





                    val height = activity.resources.getDimensionPixelSize(R.dimen.popup_height_toaster)


                    popupWindow = PopupWindow(layoutView)
                    popupWindow.width = WindowManager.LayoutParams.WRAP_CONTENT
                    popupWindow.height = height
                    popupWindow.isFocusable = isCancelable
                    popupWindow.isOutsideTouchable = isCanceledOnTouchOutside
                    popupWindow.animationStyle = R.style.DialogAnimation

                    popupWindow.showAtLocation(layoutView, Gravity.TOP, 0, 0)


                    imgClose.setOnClickListener { popupWindow.dismiss()}

                    Handler().postDelayed({ popupWindow.dismiss() }, 2500)
                }
            }
            return AestheticDialog()

        }

    }
}