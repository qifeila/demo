package goct.query.demo.model;

import lombok.Data;

@Data
public class Edi {
    private String sztp;
    private String ptnrCode;
    private String bookQty;
    private String pod;
    private String fpod;
    private String oldVslName;
    private String outVoy;

    private String vvd;
    private String callSign;
    private String soNo;
//吉出edi信息
    private String iso_code;
    private String opr;
    private String cancel_status;


}
