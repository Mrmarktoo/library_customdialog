package com.marktoo.custom.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marktoo.customdialog.R;

/**
* @ClassName: CustomDialog
* @Description: 自定义提示框
* @author marktoo 
* @date 2015-10-20 上午11:47:48
*
*/ 
public class CustomDialog extends Dialog {

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	public CustomDialog(Context context) {
		super(context);
	}

	/**
	* @ClassName: Builder
	* @Description: 提示框builder
	* @author marktoo 
	* @date 2015-10-20 上午11:48:24
	*
	*/ 
	public static class Builder {

		private Context context;
		private String title;
		private Drawable icon;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private View contentView;

		private DialogInterface.OnClickListener positiveButtonClickListener,
				negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		/**
		 * Set the Dialog message from resource 设置主体显示文本资源id
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog message from String 设置主体显示文本资源
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog title from resource 设置标题栏显示文本资源id
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String 设置标题栏显示文本资源
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * @Title: setTitleIcon
		 * @Description: 设置标题栏图标资源id
		 * @param @param iconId
		 * @return Builder
		 */
		public Builder setTitleIcon(int iconId) {
			this.icon = context.getResources().getDrawable(iconId);
			return this;
		}

		/**
		 * @Title: setTitleIcon
		 * @Description: 设置标题栏图标资源
		 * @param @param iconFile
		 * @return Builder
		 */
		public Builder setTitleIcon(Drawable iconFile) {
			this.icon = iconFile;
			return this;
		}

		/**
		 * @Title: setContentView
		 * @Description: Set a custom content view for the Dialog. If a message
		 *               is set, the contentView is not added to the Dialog...
		 *               设置dialog主体自己要显示的View
		 * @param @param v
		 * @return Builder
		 */
		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * @Title: setPositiveButton
		 * @Description: Set the positive button resource and it's listener
		 *               设置积极选项的显示文本资源和监听器
		 * @param @param positiveButtonText
		 * @param @param listener
		 * @return Builder
		 */
		public Builder setPositiveButton(int positiveButtonText,
				DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * @Title: setPositiveButton
		 * @Description: Set the positive button text and it's listener
		 *               设置积极选项的显示文本和监听器
		 * @param @param positiveButtonText
		 * @param @param listener
		 * @return Builder
		 */
		public Builder setPositiveButton(String positiveButtonText,
				DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * @Title: setNegativeButton
		 * @Description: Set the negative button resource and it's listener
		 *               设置消极选项的显示文本资源和监听器
		 * @param @param negativeButtonText
		 * @param @param listener
		 * @return Builder
		 */
		public Builder setNegativeButton(int negativeButtonText,
				DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * @Title: setNegativeButton
		 * @Description: Set the negative button text and it's listener
		 *               设置消极选项的显示文本和监听器
		 * @param @param negativeButtonText
		 * @param @param listener
		 * @return Builder
		 */
		public Builder setNegativeButton(String negativeButtonText,
				DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * @Title: create
		 * @Description: Create the custom dialog 生成设定要显示dialog实例
		 * @return CustomDialog
		 */
		@SuppressLint("NewApi")
		public CustomDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final CustomDialog dialog = new CustomDialog(context,
					R.style.mydialog);
			View layout = inflater.inflate(R.layout.layout_alertdialog, null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			// set the dialog title
			if (title != null && !"".equals(title)) {
				((TextView) layout.findViewById(R.id.alertlayout_title_text))
						.setText(title);
			} else {
				layout.findViewById(R.id.alertlayout_title_text).setVisibility(
						View.GONE);
			}

			if (icon != null) {
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
					((ImageView) layout
							.findViewById(R.id.alertlayout_title_icon))
							.setBackgroundDrawable(icon);
				} else {
					((ImageView) layout
							.findViewById(R.id.alertlayout_title_icon))
							.setBackground(icon);
				}
			} else {
				layout.findViewById(R.id.alertlayout_title_icon).setVisibility(
						View.GONE);
			}

			if (icon == null && (title == null || "".equals(title))) {
				layout.findViewById(R.id.alertlayout_title).setVisibility(
						View.GONE);
			}

			// set the confirm button
			if (positiveButtonText != null) {
				((Button) layout.findViewById(R.id.alertlayout_bottom_confirm))
						.setText(positiveButtonText);
				if (positiveButtonClickListener != null) {
					((Button) layout
							.findViewById(R.id.alertlayout_bottom_confirm))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									positiveButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.alertlayout_bottom_confirm)
						.setVisibility(View.GONE);
			}

			// // set the cancel button
			if (negativeButtonText != null) {
				((Button) layout.findViewById(R.id.alertlayout_bottom_refuse))
						.setText(negativeButtonText);
				if (negativeButtonClickListener != null) {
					((Button) layout
							.findViewById(R.id.alertlayout_bottom_refuse))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.alertlayout_bottom_refuse)
						.setVisibility(View.GONE);
			}

			if ((positiveButtonText == null || "".equals(positiveButtonText))
					&& (negativeButtonText == null || ""
							.equals(negativeButtonText))) {
				layout.findViewById(R.id.alertlayout_bottom).setVisibility(
						View.GONE);
			}

			// set the content message
			if (message != null) {
				((TextView) layout
						.findViewById(R.id.tv_new_layout_dialog_contenttext))
						.setText(message);
			} else {
				layout.findViewById(R.id.tv_new_layout_dialog_contenttext)
						.setVisibility(View.GONE);
			}

			if (contentView != null) {
				((LinearLayout) layout
						.findViewById(R.id.alertlayout_contentview))
						.removeAllViews();
				((LinearLayout) layout
						.findViewById(R.id.alertlayout_contentview)).addView(
						contentView, new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
			}
			dialog.setContentView(layout);
			return dialog;
		}
	}
}