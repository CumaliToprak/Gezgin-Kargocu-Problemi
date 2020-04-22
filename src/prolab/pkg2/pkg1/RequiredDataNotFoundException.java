/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab.pkg2.pkg1;

/**
 *
 * @author cumali_toprak
 */
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
