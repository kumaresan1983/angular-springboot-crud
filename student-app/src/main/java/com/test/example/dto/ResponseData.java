/*
 * ©Copyright Dagang NeXchange Berhad. 
 * All Rights Reserved
 */
package com.test.example.dto;

import com.test.example.utils.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseData implements Serializable {

    @JsonProperty("MsgCode")
    private String msgCode;

    @JsonProperty("MsgDesc")
    private String msgDesc;

    @JsonProperty("Remarks")
    private String remarks;

    @JsonProperty("Data")
    private Object data;

    public ResponseData() {
        this.msgCode = Constants.FAIL_CODE;
        this.msgDesc = Constants.FAIL_STR;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" + "msgCode=" + msgCode + ", msgDesc=" + msgDesc + ", remarks=" + remarks + '}';
    }

}
