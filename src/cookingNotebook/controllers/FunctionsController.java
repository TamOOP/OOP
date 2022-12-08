package cookingNotebook.controllers;

import java.sql.SQLException;
import java.util.Stack;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class FunctionsController<T> {
	public static Stack<Parent> oldRoots = new Stack<>();

	public static void pushRoot(Parent oldRoot) {
		FunctionsController.oldRoots.push(oldRoot);
	}

	public static Parent popRoot() {
		return oldRoots.pop();
	}

	public ImageView createImg(String url) {
		return new ImageView(new Image(getClass().getResource(url).toExternalForm(), 160, 90, false, false));
	}

	abstract public void check(T t) throws SQLException;
}