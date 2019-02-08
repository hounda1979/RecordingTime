package tw.hd.com.recodtime;

import java.math.BigDecimal;

public class RecordHdd extends RecordData {
    int hdd;
    public RecordHdd(int ch, int stream) {
        super(ch, stream);
    }

    public RecordHdd(int ch, int stream, int hdd) {
        super(ch, stream);
        this.hdd = hdd;
    }
    public RecordHdd(int[] hddarray){
        super(hddarray[2],hddarray[1]);
        this.hdd = hddarray[0];

    }

    @Override
    public String calculation() {
        System.out.println("hdd :"+ hdd+"\t"+ "ch :"+ch+"\t"+"steam :"+stream);
        double mainstream = ((((((stream/1024.0)/8.0)*3600.0)*24)*ch)/1024.0)/1024.0;
        double secstream =((((((256/1024.0)/8)*3600.0)*24)*ch)/1024.0)/1024.0;
        double t = (mainstream+secstream)/0.9;
        mainstream = mainstream+t;
        double byhdd = hdd/mainstream;
        BigDecimal bigDecimal = new BigDecimal(byhdd).setScale(1,BigDecimal.ROUND_HALF_UP);

        return bigDecimal+"";
    }
}
