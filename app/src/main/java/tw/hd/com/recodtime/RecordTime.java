package tw.hd.com.recodtime;

import java.math.BigDecimal;

public class RecordTime extends  RecordData {
    int day;

    public RecordTime(int ch, int stream, int day) {
        super(ch, stream);
        this.day = day;
    }

    public RecordTime(int ch, int stream) {
        super(ch, stream);
    }

    @Override
    public String calculation() {
        double mainstream = (((stream/1024)/8)*3600)*24*day*ch;
        double secstream =(((256/1024)/8)*3600)*24*day*ch;
        mainstream = mainstream+secstream;
        double byday = mainstream/1024/1024;
        BigDecimal bigDecimal = new BigDecimal(byday).setScale(2,BigDecimal.ROUND_HALF_UP);

        return bigDecimal+"";
    }
}
