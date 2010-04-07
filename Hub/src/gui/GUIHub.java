/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import hub.BTHub;
import javax.bluetooth.LocalDevice;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Gautam
 */
public class GUIHub extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private List list;
    private List logList;
    private Command exitCommand;
    private Command viewLogCommand;
    private Command backCommand;
    //</editor-fold>//GEN-END:|fields|0|
    private BTHub btHub;
    //</editor-fold>

    /**
     * The GUI constructor.
     */
    public GUIHub() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initializeuser code here
        logList = new List("list1", Choice.IMPLICIT);
        logList.addCommand(getBackCommand());
        logList.setCommandListener(this);
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getList());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == list) {//GEN-BEGIN:|7-commandAction|1|16-preAction
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|1|16-preAction
                // write pre-action user code here
                listAction();//GEN-LINE:|7-commandAction|2|16-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|3|21-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|4|21-postAction
                // write post-actio               n user code here
                destroyApp(false);
                notifyDestroyed();

            } else if (command == viewLogCommand) {//GEN-LINE:|7-commandAction|5|23-preAction
                // write pre-action user code here
                switchDisplayable(null, getLogList());//GEN-LINE:|7-commandAction|6|23-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|25-preAction
        } else if (displayable == logList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|7|25-preAction
                // write pre-action user code here
                logListAction();//GEN-LINE:|7-commandAction|8|25-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|9|29-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|10|29-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|7-postCommandAction
        }//GEN-END:|7-commandAction|11|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|12|
    //</editor-fold>//GEN-END:|7-commandAction|12|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: list ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of list component.
     * @return the initialized component instance
     */
    public List getList() {
        if (list == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            list = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|14-getter|1|14-postInit
            list.append("Hub has been started", null);
            list.addCommand(getExitCommand());
            list.addCommand(getViewLogCommand());
            list.setCommandListener(this);
            list.setSelectedFlags(new boolean[] { false });//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
            try{
            list.append("BTAddress is "+LocalDevice.getLocalDevice().getBluetoothAddress(), null);
            }
            catch(Exception e){

            }


        }//GEN-BEGIN:|14-getter|2|
        return list;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listAction ">//GEN-BEGIN:|14-action|0|14-preAction
    /**
     * Performs an action assigned to the selected list element in the list component.
     */
    public void listAction() {//GEN-END:|14-action|0|14-preAction
        // enter pre-action user code here
        String __selectedString = getList().getString(getList().getSelectedIndex());//GEN-BEGIN:|14-action|1|19-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Hub has been started")) {//GEN-END:|14-action|1|19-preAction
                // write pre-action user code here
//GEN-LINE:|14-action|2|19-postAction
                // write post-action user code here
            }//GEN-BEGIN:|14-action|3|14-postAction
        }//GEN-END:|14-action|3|14-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|14-action|4|
    //</editor-fold>//GEN-END:|14-action|4|



    //<editor-fold defaultstate="collapsed" desc=" Generated Method: logListAction ">//GEN-BEGIN:|24-action|0|24-preAction
    /**
     * Performs an action assigned to the selected list element in the logList component.
     */
    public void logListAction() {//GEN-END:|24-action|0|24-preAction
        // enter pre-action user code here
        String __selectedString = getLogList().getString(getLogList().getSelectedIndex());//GEN-LINE:|24-action|1|24-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|24-action|2|
    //</editor-fold>//GEN-END:|24-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|20-getter|0|20-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|20-getter|0|20-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|20-getter|1|20-postInit
            // write post-init user code here
        }//GEN-BEGIN:|20-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|20-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: viewLogCommand ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of viewLogCommand component.
     * @return the initialized component instance
     */
    public Command getViewLogCommand() {
        if (viewLogCommand == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            viewLogCommand = new Command("View Log", Command.OK, 0);//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return viewLogCommand;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|28-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: logList ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of logList component.
     * @return the initialized component instance
     */
    public List getLogList() {
        if (logList == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            logList = new List("list1", Choice.IMPLICIT);//GEN-BEGIN:|24-getter|1|24-postInit
            logList.addCommand(getBackCommand());
            logList.setCommandListener(this);//GEN-END:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return logList;
    }
    //</editor-fold>//GEN-END:|24-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
            btHub = new BTHub(this);
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

}
