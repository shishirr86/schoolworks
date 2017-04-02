
/*
 * Created a new class to provide useful functionalities
 * for the LinkedStack class. This class provides -
 * 
 * 1. Search by the content
 * 2. Search by character if the content is a member of Character
 * 3. Search by node location
 * 4. Reverse the stack
 * 5. Determine length of the stack
 * 6. Locate position of a node by its index
 * 7. Print the stack (calls toString() of its content)
 * 
 */

public class SearchableLinkedStack<T> extends LinkedStack<T> {

	// this class uses an additional pointer to make life easy :)
	protected LLNode<T> current;

	public SearchableLinkedStack() {
		super();
		current = super.top;
	}

	/**
	 * @param element
	 *            an element of the Linked List node (determined at runtime)
	 * @return the index of the element if found, otherwise returns -1. Throws
	 *         StackUnderflowException in case of empty stack.
	 */
	public int search(T element) {
		if (element instanceof Character)
			return this.search((char) element);
		else {
			int index = -1;
			current = super.top;

			if (current != null) {
				while (current != null) {
					index++;
					if (current.getInfo().equals(element))
						return index;
				}
			}
			else
				throw new StackUnderflowException("Search attempted on an empty stack.");
			
			return -1;
		}
	}

	/**
	 * @param ch
	 *            a char element of the Linked List node when used at runtime
	 * @return the index of the char element if found, otherwise returns -1.
	 * @throws StackUnderflowException
	 *             in case of an empty stack.
	 */
	public int search(char ch) {
		int index = -1;
		current = super.top;

		if (current != null) {
			while (current != null) {
				index++;
				if (current.getInfo() instanceof MyMazeNode<?>) {

					MyMazeNode<?> m = (MyMazeNode<?>) current.getInfo();
					if (m.getVal() instanceof Character) {
						if ((char) m.getVal() == ch) {
							return index;
						} else
							current = current.getLink();
					}
				}
			}
		} else
			throw new StackUnderflowException("Search attempted on an empty stack.");
		
		return index;
	}

	/**
	 * @param row
	 *            int value of the row
	 * @param col
	 *            int value of the column
	 * @throws StackUnderflowException
	 *             in case of an empty stack.
	 * 
	 * @return the index of the char element if found, otherwise returns -1. *
	 */
	public int search(int row, int col) {
		int index = -1;
		current = super.top;

		if (current != null) {
			while (current != null) {
				index++;
				if (current.getInfo() instanceof MyMazeNode<?>) {

					MyMazeNode<?> m = (MyMazeNode<?>) current.getInfo();
					if ((m.getRow() == row) && (m.getCol() == col))
						return index;
					else
						current = current.getLink();
				}
			}
		} else
			throw new StackUnderflowException("Search attempted on an empty stack.");
		
		return index;
	}

	/**
	 * Reverses the SearchableLinkedStack stack
	 */
	public void reverse() {
		
		LinkedStack<T> temp = new LinkedStack<T>();
		
		if (!isEmpty()) {
			while (!isEmpty()) {
				temp.push(super.top());
				super.pop();
			}
			super.top = temp.top;
			current = super.top;
			temp.top = null;
		} else
			throw new StackUnderflowException("Reverse attempted on an empty stack.");
	}

	/**
	 * @return the int value of stack size
	 */
	public int length() {
		int length = 0;
		current = super.top;

		if (current != null) {
			while (current != null) {
				length++;
				current = current.getLink();
			}
		}
		current = super.top;
		return length;
	}

	/**
	 * 
	 * @param index
	 *            the location of the stack where value will be retrieved from
	 * @return the LinkedList node at the chosen location
	 */
	public T locator(int index) {
		current = super.top;

		if (index < 0)
			throw new StackUnderflowException("Index " + index + " lower than possible range!");
		else if (index > this.length())
			throw new StackOverflowException("Index " + index + " higher than actual range!");
		else {

			if (current != null) {
				for (int i = 0; i < index; i++) {
					current = current.getLink();
				}
				return current.getInfo();
			} else
				throw new StackUnderflowException("Stack appears to be empty!");
		}
	}

	/**
	 * Prints the SearchableLinkedList class data in string. Actual
	 * implementation depends on the value type specified at the runtime.
	 */
	public String toString() {
		String s = "";

		current = super.top;
		if (current != null) {
			while (current != null) {

				s = s + current.getInfo().toString();
				current = current.getLink();
			}
			return s;

		} else
			throw new StackUnderflowException("Stack empty. Nothing to print!");
	}
}
