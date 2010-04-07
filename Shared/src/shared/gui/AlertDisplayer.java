/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.gui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/**
 *
 * @author Gautam
 */
public class AlertDisplayer {

    public static void showAlert ( String title , String message ,
            Display d , Displayable displayable)
    {
        showAlert (title, message, AlertType.INFO, 2000, d, displayable);
    }
    public static void showAlert ( String title , String message , AlertType type ,
            int timeOut , Display d , Displayable displayable )
    {
        Alert alert = new Alert ( title, message, null, type );
        alert.setTimeout(timeOut);
        d.setCurrent(alert, displayable);

    }


}
