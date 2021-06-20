package goct.query.demo.util;

import goct.query.demo.model.CntrInAndOut;
import goct.query.demo.model.Gate;

import java.util.ArrayList;
import java.util.List;

public class Data2JsonUtil {
    /**
     * @Author wmm
     * @param cntrInAndOutList2_3
     * @param cntrInAndOutList3_2
     * @return
     */
    public static String getCntrNumData (List<CntrInAndOut> cntrInAndOutList2_3, List<CntrInAndOut> cntrInAndOutList3_2){
        StringBuffer data = new StringBuffer();
        for (int i=0;i<cntrInAndOutList2_3.size();i++){
            if (i==0)
                data.append("[[\"");
            else
                data.append("[\"");
            data.append(cntrInAndOutList2_3.get(i).getWeek());
            data.append("\",");
            data.append(cntrInAndOutList2_3.get(i).getValue());
            data.append(",");
            data.append(cntrInAndOutList3_2.get(i).getValue());
            if (i==cntrInAndOutList2_3.size()-1)
                data.append("]]");
            else
                data.append("],");

        }
        return data.toString();
    }


    public static String getJson (List<Gate> gate1,List<Gate> gate2,List<Gate> gate3){
        System.out.println(gate1.size()+"2Hang"+gate2.size()+"3hang"+gate3.size());
        StringBuffer data = new StringBuffer();
        for (int i=0;i<gate1.size();i++){
            if (i==0)
                data.append("[[\"");
            else
                data.append("[\"");
            data.append(gate1.get(i).getMonthDay());
            data.append("\",");
            data.append(gate1.get(i).getValue());
            data.append(",");
            data.append(i>gate2.size()-1?"":gate2.get(i).getValue());
            data.append(",");
            data.append(i>gate3.size()-1?"":gate3.get(i).getValue());
            if (i==gate1.size()-1)
                data.append("]]");
            else
                data.append("],");

        }
        return data.toString();
    }
    //1外拖回流时间，2进出车次数量 3进出闸操作量
   public static List<Gate> getGateList(List<Gate> gates1,List<Gate> gates2,List<Gate> gates3){
       // Integer sum = 0;
        List<Gate> gateList = new ArrayList<>();
        for (int i = 0;i<gates1.size();i++){
            Gate gate = new Gate();
            gate.setMonth(gates1.get(i).getMonth());
            gate.setAvgTime(gates1.get(i).getValue());
            gate.setSumNum(gates2.get(i).getValue());
            gate.setSumThroughput(gates3.get(i).getValue());
            gateList.add(gate);

        }

       return gateList;
   }

    /**
     *
     * @param gate1  重进
     * @param gate2 重出
     * @param gate3 吉进
     * @param gate4 吉出
     * @return  一定格式
     */
    public static String getFEdata (List<Gate> gate1,List<Gate> gate2,List<Gate> gate3,List<Gate> gate4){
        System.out.println(gate1.size()+" 2Hang"+gate2.size()+" 3hang"+gate3.size()+" 4hang" +gate4.size());
        StringBuffer data = new StringBuffer();
        for (int i=0;i<gate1.size();i++){  //100
            //  Stirng a="["+gate1.get(i).getMonthDay();
            // data.append("[\""+gate1.get(i).getMonthDay()+"\","+gate1.get(i).getValue()+);
            if (i==0)
                data.append("[[\"");
            else
                data.append("[\"");
            data.append(gate1.get(i).getMonthDay());
            data.append("\",");
            data.append(gate1.get(i).getValue());
            data.append(",");
            data.append(gate2.get(i).getValue());
            data.append(",");
            data.append(gate3.get(i).getValue());
            data.append(",");
            data.append(gate4.get(i).getValue());
            if (i==gate1.size()-1)
                data.append("]]");
            else
                data.append("],");

        }
        // System.out.println("111"+data);
        return data.toString();
    }

    /**
     *  ('重进','重出','吉进','吉出')
     * @param gates1
     * @param gates2
     * @param gates3
     * @param gates4
     * @return
     */
    public static List<Gate> getFEdataByMonth(List<Gate> gates1,List<Gate> gates2,List<Gate> gates3,List<Gate> gates4){
        // Integer sum = 0;
        List<Gate> gateList = new ArrayList<>();
        for (int i = 0;i<gates1.size();i++){
            Gate gate = new Gate();
            gate.setMonth(gates1.get(i).getMonth());
            gate.setFin(gates1.get(i).getValue());
            gate.setFout(gates2.get(i).getValue());
            gate.setEin(gates3.get(i).getValue());
            gate.setEout(gates4.get(i).getValue());
            gateList.add(gate);

        }

        return gateList;
    }



}
