package com.easyfilter.navigator.util;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class Log {
	private static MessageConsole console = null;
	private static MessageConsoleStream consoleStream = null;
	private static IConsoleManager consoleManager = null;
	private static final String CONSOLE_NAME = "Console";

	public static void initConsole() {
		// 新建一个MessageConsole，可以自定义Console的名称
//		MessageDialog.openInformation(null, "Title", "It's just a test"); 
		consoleManager = ConsolePlugin.getDefault().getConsoleManager();
		IConsole[] consoles = consoleManager.getConsoles();
		if (consoles.length > 0) {
			console = (MessageConsole) consoles[0];
		} else {
			console = new MessageConsole(CONSOLE_NAME, null);
			consoleManager.addConsoles(new IConsole[] { console });
		}
		consoleStream = console.newMessageStream();

		// 通过ConsolePlugin得到IConsoleManager，添加自定义的MessageConsole
		consoleManager = ConsolePlugin.getDefault().getConsoleManager();
		consoleManager.addConsoles(new IConsole[] { console });

		// 新建一个MessageConsoleStream， 作用是接收需要打印的消息
		consoleStream = console.newMessageStream();
	}

	/**
	 * 开启console， 打印相关消息
	 * 
	 * @param message
	 *            消息内容
	 */
	public static void printMessage(Object message) {
		if (message != null) {
			if (console == null) {
				initConsole();
			}
			// 显示Console视图
			consoleManager.showConsoleView(console);
			// 打印消息
			consoleStream.print(message.toString() + "\n");

		}
	}
}
