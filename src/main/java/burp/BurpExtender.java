package burp;

import java.awt.*;
import java.io.PrintWriter;

/**
 * @ClassName BurpExtender
 * @Author bobby
 * @Date 2019-06-12 00:30
 * @Version 1.00
 * @Description https://www.tuicool.com/articles/eU7vUjA;https://www.freebuf.com/articles/terminal/106673.html
 **/
public class BurpExtender implements IBurpExtender, IMessageEditorTabFactory {

    public PrintWriter stdout;
    public IExtensionHelpers helpers;
    private IBurpExtenderCallbacks callbacks;
    /*
     * @Author bobby
     * @Description //TODO
     * @Date 00:32 2019-06-12
     * @Param [callbacks]
     * @return void
     **/
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.stdout = new PrintWriter(callbacks.getStdout(), true);
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();
        callbacks.setExtensionName("Her0in");
        callbacks.registerMessageEditorTabFactory(this);
        stdout.println("onload extender successful");

    }

    /*
     * @Author bobby
     * @Description //TODO
     * @Date 00:32 2019-06-12
     * @Param [controller, editable]
     * @return burp.IMessageEditorTab
     **/
    public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable) {
        return new iMessageEditorTab();
    }

    class iMessageEditorTab implements IMessageEditorTab{

        private ITextEditor iTextEditor = callbacks.createTextEditor();
        /*
         * @Author bobby
         * @Description //TODO 
         * @Date 00:37 2019-06-12
         * @Param []
         * @return java.lang.String
         **/
        public String getTabCaption() {

            return "测试 MessageEditorTab";
        }
        
        /*
         * @Author bobby
         * @Description //TODO 
         * @Date 00:37 2019-06-12
         * @Param []
         * @return java.awt.Component
         **/
        public Component getUiComponent() {
            return iTextEditor.getComponent();
        }
        
        /*
         * @Author bobby
         * @Description //TODO 
         * @Date 00:37 2019-06-12
         * @Param [content, isRequest]
         * @return boolean
         **/
        public boolean isEnabled(byte[] content, boolean isRequest) {
            return true;
        }

        public void setMessage(byte[] content, boolean isRequest) {
            iTextEditor.setText(helpers.stringToBytes("test"));
        }

        /*
         * @Author bobby
         * @Description //TODO 
         * @Date 00:38 2019-06-12
         * @Param []
         * @return byte[]
         **/
        public byte[] getMessage() {
            return iTextEditor.getText();
        }
        
        /*
         * @Author bobby
         * @Description //TODO 
         * @Date 00:38 2019-06-12
         * @Param []
         * @return boolean
         **/
        public boolean isModified() {
            return true;
        }
        
        /*
         * @Author bobby
         * @Description //TODO 
         * @Date 00:37 2019-06-12
         * @Param []
         * @return byte[]
         **/
        public byte[] getSelectedData() {
            return iTextEditor.getSelectedText();
        }
    }
}
