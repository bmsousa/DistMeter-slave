package utilities;

import org.rapidoid.commons.Str;

public class MessageSlave {
    private int msgtype;
    private String cmd;
    private int result;
    private String result_txt;

    public static final int MESSAGETOSLAVE = 1;
    public static final int MESSAGETOCOMMANDS = 2;
    public static final int MESSAGETOCOMSERVER = 3;
    public MessageSlave(int msgtype, String cmd) {
        this.msgtype = msgtype;
        this.cmd = cmd;
    }

    public MessageSlave(int msgtype, String cmd, int result, String res) {
        this.msgtype = msgtype;
        this.cmd = cmd;
        this.result=result;
        this.result_txt=res;
    }
    @Override
public String toString(){
        String res= this.msgtype+" cmd "+ this.cmd;
        return res;
}
    public int getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(int msgtype) {
        this.msgtype = msgtype;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResult_txt() {
        return result_txt;
    }

    public void setResult_txt(String result_txt) {
        this.result_txt = result_txt;
    }
}
