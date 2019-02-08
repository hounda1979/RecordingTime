package tw.hd.com.recodtime;

import java.math.BigDecimal;

public class RecordDay extends RecordData {
    int day;
    public RecordDay(int ch, int stream) {
        super(ch, stream);
    }

    public RecordDay(int ch, int stream, int day) {
        super(ch, stream);
        this.day = day;
    }

    @Override
    public String calculation() {
        double mainstream = (((stream/1024.0)/8.0)*3600)*24*day*ch;
        double secstream =(((256/1024.0)/8.0)*3600)*24*day*ch;
        double t = (mainstream+secstream)/0.9;
        mainstream = mainstream+t;

        double xhdd = mainstream/1024.0/1024.0;
        BigDecimal bigDecimal = new BigDecimal(xhdd).setScale(1,BigDecimal.ROUND_HALF_UP);
        return bigDecimal+"";
    }
}
