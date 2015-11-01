# rx-alert-dialog
Helpers to create and handle Android alert dialogs in an RxJava workflow.

## Usage
To use the regular Android dialog from your `Activity`, call:

<pre>
new RxAlertDialog.Builder(this)
	.title("Title")
	.message("Some action is required")
	.positiveButton("OK")
	.negativeButton("NO")
	.neutralButton("LATER")
	.show()
	.subscribe(new Observer<AlertDialogEvent>(){
	...
	})
</pre>	
		
To use the Support dialog from your `Activity`, call:

<pre>
new RxAlertDialog<b>Support</b>.Builder(this)
	.title("Title")
	.message("Some action is required")
	.positiveButton("OK")
	.negativeButton("NO")
	.neutralButton("LATER")
	.show()
	.subscribe(new Observer<AlertDialogEvent>(){
	...
	})
</pre>

You can also call `.create()`, but you have to call `.show()` on the dialog, when it comes with the first event.

## Events
1. When you call `create()` or `show()`, you get:
	- For regular Android dialog: one `AlertDialogDialogEvent` where `getAlertDialog()` gives you the created dialog.
	- For Support dialog: one `AlertDialogSupportDialogEvent` where `getAlertDialog()` gives you the created dialog.
1. When you click a button on the dialog, you get:
	- one `AlertDialogButtonEvent` where `.getWhich()` tells you which button was pressed.
	- the `onCompleted()` signal right away.
1. When you `.dismiss()` the dialog, you get:
	- the `onCompleted()` signal right away.
1. If you `.unsubscribe()` the `Observable`, then the dialog will be dismissed.