
package prolab.pkg2.pkg1;

public class RequiredDataNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>RequiredDataNotFoundException</code>
     * without detail message.
     */
    public RequiredDataNotFoundException() {
    }

    /**
     * Constructs an instance of <code>RequiredDataNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public RequiredDataNotFoundException(String msg) {
        super(msg);
    }
}
